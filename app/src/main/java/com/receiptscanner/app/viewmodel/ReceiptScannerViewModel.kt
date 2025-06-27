package com.receiptscanner.app.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.receiptscanner.app.BuildConfig
import com.receiptscanner.app.data.ReceiptItem
import com.receiptscanner.app.data.ReceiptScannerUiState
import com.receiptscanner.app.utils.bitmapToByteArray
import com.receiptscanner.app.utils.byteArrayToBitmap
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class ReceiptResponse(
    val items: List<ReceiptItemResponse>,
    val total: Double?
)

@Serializable
data class ReceiptItemResponse(
    val description: String,
    val price: Double?
)

class ReceiptScannerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ReceiptScannerUiState())
    val uiState: StateFlow<ReceiptScannerUiState> = _uiState.asStateFlow()

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    fun startScan(hasCameraPermission: Boolean, onRequestPermission: () -> Unit) {
        if (hasCameraPermission) {
            _uiState.value = _uiState.value.copy(
                isScanning = true,
                error = null
            )
        } else {
            onRequestPermission()
        }
    }

    fun stopScanning() {
        _uiState.value = _uiState.value.copy(isScanning = false)
    }

    fun resetAndStartScan(hasCameraPermission: Boolean, onRequestPermission: () -> Unit) {
        _uiState.value = ReceiptScannerUiState()
        startScan(hasCameraPermission, onRequestPermission)
    }

    fun processReceipt(bitmap: Bitmap) {
        _uiState.value = _uiState.value.copy(
            isScanning = false,
            capturedImage = bitmap,
            isLoading = true,
            error = null
        )

        viewModelScope.launch {
            try {
                val inputContent = content {
                    image(byteArrayToBitmap(bitmapToByteArray(bitmap)))
                    text(
                        """
                        You are an expert receipt reader. Analyze this image of a supermarket receipt.
                        Extract all line items with their description and price. Also, find the total amount.
                        Return the data as a single JSON object with the following structure:
                        { "items": [{ "description": "string", "price": number }], "total": number }
                        - If you cannot determine a price for an item, set it to null.
                        - If you cannot find a total, set it to null.
                        - Do not include taxes or other fees in the item list, only in the total if it's explicitly stated as 'TOTAL'.
                        - Clean up item descriptions to be clear and concise.
                    """.trimIndent()
                    )
                }

                val response = generativeModel.generateContent(inputContent)
                val responseText = response.text ?: throw Exception("No response from AI")

                // Clean the response text to extract JSON
                val jsonText = responseText
                    .removePrefix("```json")
                    .removeSuffix("```")
                    .trim()

                val json = Json { ignoreUnknownKeys = true }
                val receiptResponse = json.decodeFromString<ReceiptResponse>(jsonText)

                val items = receiptResponse.items.map {
                    ReceiptItem(it.description, it.price)
                }

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    extractedItems = items,
                    total = receiptResponse.total
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = "Could not analyze the receipt. Please try a clearer picture."
                )
            }
        }
    }
}