package com.example.articlesproject.main.data.storage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.io.InputStream

class CompressPicture {

    // Function to compress an image from Uri
    fun compressImage(context: Context, imageUri: Uri, maxWidth: Int, maxHeight: Int, quality: Int): ByteArray {
        val imageStream: InputStream? = context.contentResolver.openInputStream(imageUri)

        // Load the image from the given Uri
        val bitmap = BitmapFactory.decodeStream(imageStream)

        // Calculate the new dimensions while maintaining the aspect ratio
        var width = bitmap.width
        var height = bitmap.height
        val aspectRatio: Float = width.toFloat() / height.toFloat()

        if (width > maxWidth || height > maxHeight) {
            if (aspectRatio > 1) {
                width = maxWidth
                height = (width / aspectRatio).toInt()
            } else {
                height = maxHeight
                width = (height * aspectRatio).toInt()
            }
        }

        // Resize the image
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)
//
//        // Rotate the image if necessary (optional)
//        val rotation = getImageOrientation(context, imageUri)
//        val matrix = Matrix()
//        matrix.postRotate(rotation.toFloat())
//        val rotatedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.width, scaledBitmap.height, matrix, true)

        // Compress the image to the desired quality and format
        val outputStream = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)

        return outputStream.toByteArray()
    }

    // Function to get the orientation of the image from Uri
    fun getImageOrientation(context: Context, imageUri: Uri): Int {
        val exif = android.media.ExifInterface(context.contentResolver.openInputStream(imageUri)!!)
        val orientation = exif.getAttributeInt(android.media.ExifInterface.TAG_ORIENTATION, android.media.ExifInterface.ORIENTATION_UNDEFINED)

        return when (orientation) {
            android.media.ExifInterface.ORIENTATION_ROTATE_90 -> 90
            android.media.ExifInterface.ORIENTATION_ROTATE_180 -> 180
            android.media.ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }
    }

    // Usage example:
//    val imageUri: Uri = // Your image Uri
//    val maxWidth = 800 // Set your desired maximum width
//    val maxHeight = 800 // Set your desired maximum height
//    val quality = 85 // Set your desired compression quality (0-100)
//
//    val compressedImageBytes = compressImage(context, imageUri, maxWidth, maxHeight, quality)





//    // Function to compress an image
//    fun compressImage(imagePath: String, maxWidth: Int, maxHeight: Int, quality: Int): ByteArray {
//        // Load the image from the given path
//        val bitmap = BitmapFactory.decodeFile(imagePath)
//
//        // Calculate the new dimensions while maintaining the aspect ratio
//        var width = bitmap.width
//        var height = bitmap.height
//        val aspectRatio: Float = width.toFloat() / height.toFloat()
//
//        if (width > maxWidth || height > maxHeight) {
//            if (aspectRatio > 1) {
//                width = maxWidth
//                height = (width / aspectRatio).toInt()
//            } else {
//                height = maxHeight
//                width = (height * aspectRatio).toInt()
//            }
//        }
//
//        // Resize the image
//        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)
//
//        // Rotate the image if necessary (optional)
//        val matrix = Matrix()
//        matrix.postRotate(getImageOrientation(imagePath).toFloat())
//        val rotatedBitmap = Bitmap.createBitmap(
//            scaledBitmap,
//            0,
//            0,
//            scaledBitmap.width,
//            scaledBitmap.height,
//            matrix,
//            true
//        )
//
//        // Compress the image to the desired quality and format
//        val outputStream = ByteArrayOutputStream()
//        rotatedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
//
//        return outputStream.toByteArray()
//    }
//
//    // Function to get the orientation of the image
//    fun getImageOrientation(imagePath: String): Int {
//        val exif = android.media.ExifInterface(imagePath)
//        val orientation = exif.getAttributeInt(
//            android.media.ExifInterface.TAG_ORIENTATION,
//            android.media.ExifInterface.ORIENTATION_UNDEFINED
//        )
//
//        return when (orientation) {
//            android.media.ExifInterface.ORIENTATION_ROTATE_90 -> 90
//            android.media.ExifInterface.ORIENTATION_ROTATE_180 -> 180
//            android.media.ExifInterface.ORIENTATION_ROTATE_270 -> 270
//            else -> 0
//        }
//    }

    // Usage example:
//    val imagePath = "path_to_your_image.jpg"
//    val maxWidth = 800 // Set your desired maximum width
//    val maxHeight = 800 // Set your desired maximum height
//    val quality = 85 // Set your desired compression quality (0-100)
//
//    val compressedImageBytes = compressImage(imagePath, maxWidth, maxHeight, quality)

}