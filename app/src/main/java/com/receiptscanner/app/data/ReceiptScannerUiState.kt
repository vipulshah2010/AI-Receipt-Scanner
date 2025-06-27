package com.receiptscanner.app.data

import android.graphics.Bitmap

data class ReceiptScannerUiState(
    val isScanning: Boolean = false,
    val capturedImage: Bitmap? = null,
    val extractedItems: List<ReceiptItem> = emptyList(),
    val total: Double? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)