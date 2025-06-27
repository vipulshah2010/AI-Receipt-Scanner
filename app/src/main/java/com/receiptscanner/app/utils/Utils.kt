package com.receiptscanner.app.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector
import java.io.ByteArrayOutputStream

fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
    return stream.toByteArray()
}

fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}

val CameraAlt: ImageVector
    get() {
        if (_cameraAlt != null) {
            return _cameraAlt!!
        }
        _cameraAlt = materialIcon(name = "Default.CameraAlt") {
            materialPath {
                moveTo(12.0f, 12.0f)
                moveToRelative(-3.2f, 0.0f)
                arcToRelative(3.2f, 3.2f, 0.0f, true, true, 6.4f, 0.0f)
                arcToRelative(3.2f, 3.2f, 0.0f, true, true, -6.4f, 0.0f)

                moveTo(9.0f, 2.0f)
                lineTo(7.17f, 4.0f)
                horizontalLineTo(4.0f)
                curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f)
                verticalLineToRelative(12.0f)
                curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f)
                horizontalLineToRelative(16.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                verticalLineTo(6.0f)
                curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f)
                horizontalLineToRelative(-3.17f)
                lineTo(15.0f, 2.0f)
                horizontalLineTo(9.0f)
                close()
            }
        }
        return _cameraAlt!!
    }

val Receipt: ImageVector
    get() {
        if (_receipt != null) {
            return _receipt!!
        }
        _receipt = materialIcon(name = "Default.Receipt") {
            materialPath {
                moveTo(18.0f, 17.0f)
                horizontalLineTo(6.0f)
                verticalLineTo(3.0f)
                horizontalLineTo(18.0f)
                verticalLineTo(17.0f)
                close()

                moveTo(18.0f, 1.0f)
                horizontalLineTo(6.0f)
                curveTo(4.9f, 1.0f, 4.0f, 1.9f, 4.0f, 3.0f)
                verticalLineTo(17.0f)
                curveTo(4.0f, 18.1f, 4.9f, 19.0f, 6.0f, 19.0f)
                horizontalLineTo(18.0f)
                curveTo(19.1f, 19.0f, 20.0f, 18.1f, 20.0f, 17.0f)
                verticalLineTo(3.0f)
                curveTo(20.0f, 1.9f, 19.1f, 1.0f, 18.0f, 1.0f)
                close()

                // Receipt lines
                moveTo(7.0f, 5.0f)
                horizontalLineTo(17.0f)
                verticalLineTo(7.0f)
                horizontalLineTo(7.0f)
                verticalLineTo(5.0f)
                close()

                moveTo(7.0f, 8.0f)
                horizontalLineTo(17.0f)
                verticalLineTo(10.0f)
                horizontalLineTo(7.0f)
                verticalLineTo(8.0f)
                close()

                moveTo(7.0f, 11.0f)
                horizontalLineTo(17.0f)
                verticalLineTo(13.0f)
                horizontalLineTo(7.0f)
                verticalLineTo(11.0f)
                close()

                moveTo(7.0f, 14.0f)
                horizontalLineTo(14.0f)
                verticalLineTo(16.0f)
                horizontalLineTo(7.0f)
                verticalLineTo(14.0f)
                close()

                // Bottom zigzag pattern for receipt tear
                moveTo(4.0f, 19.0f)
                lineTo(5.0f, 20.0f)
                lineTo(6.0f, 19.0f)
                lineTo(7.0f, 20.0f)
                lineTo(8.0f, 19.0f)
                lineTo(9.0f, 20.0f)
                lineTo(10.0f, 19.0f)
                lineTo(11.0f, 20.0f)
                lineTo(12.0f, 19.0f)
                lineTo(13.0f, 20.0f)
                lineTo(14.0f, 19.0f)
                lineTo(15.0f, 20.0f)
                lineTo(16.0f, 19.0f)
                lineTo(17.0f, 20.0f)
                lineTo(18.0f, 19.0f)
                lineTo(19.0f, 20.0f)
                lineTo(20.0f, 19.0f)
                verticalLineTo(23.0f)
                horizontalLineTo(4.0f)
                verticalLineTo(19.0f)
                close()
            }
        }
        return _receipt!!
    }

private var _receipt: ImageVector? = null
private var _cameraAlt: ImageVector? = null