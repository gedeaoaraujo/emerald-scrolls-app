package com.emeraldscrolls.emeraldscrollsapp.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime


object PdfCreator {

    private const val PADDING = 40f
    private const val PAGE_WIDTH = 600
    private const val PAGE_HEIGHT = 800

    fun generatePDF(context: Context, scroll: ScrollModel?): Boolean {
        if (canReadAndWrite(context).not())
            requestPermission(context as Activity)

        val pdfDocument = PdfDocument()
        val dateTimeFile = LocalDateTime.now().dateTimeFile()
        val fileName = "Scroll-$dateTimeFile.pdf"

        val pageInfo = PdfDocument.PageInfo
            .Builder(PAGE_WIDTH, PAGE_HEIGHT, 1)
            .create()
        val page = pdfDocument.startPage(pageInfo)

        val paint = Paint().also {
            it.textSize = 20f
            it.typeface = Typeface.DEFAULT_BOLD
        }

        var rowCounter = 1
        val rowPosition = PADDING
        val canvas: Canvas = page.canvas

        scroll?.run {
            val date = date?.dateTimeFmt().orEmpty()
            canvas.drawText(title, PADDING, rowPosition.plus(rowCounter * PADDING), paint)

            paint.typeface = Typeface.DEFAULT
            canvas.drawText(date, PADDING, rowPosition.plus(++rowCounter * PADDING), paint)
            drawText(text, canvas, paint, rowPosition.plus(++rowCounter * PADDING))
        }

        pdfDocument.finishPage(page)
        val file = File(Environment.getExternalStorageDirectory(), fileName)

        val isCreated = try {
            pdfDocument.writeTo(FileOutputStream(file))
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }

        pdfDocument.close()
        return isCreated
    }

    private fun drawText(text: String, canvas: Canvas, paint: Paint, col: Float) {
        var colSize = col
        val rowSize = PADDING
        var line = StringBuilder()
        val width = canvas.width - 100f
        val words = text.split(" ")

        words.forEach { word ->
            val testLine = "$line$word "
            val lineWidth = paint.measureText(testLine)

            if (lineWidth > width) {
                canvas.drawText("$line", rowSize, colSize, paint)
                line = StringBuilder("$word ")
                colSize += paint.textSize
            } else {
                line = StringBuilder(testLine)
            }
        }

        if (line.isNotEmpty()) {
            canvas.drawText("$line", rowSize, colSize, paint)
        }
    }

    private fun canReadAndWrite(context: Context): Boolean {
        val writeStoragePermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val readStoragePermission = ContextCompat.checkSelfPermission(
            context, Manifest.permission.READ_EXTERNAL_STORAGE
        )

        return writeStoragePermission == PackageManager.PERMISSION_GRANTED
            && readStoragePermission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity, arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), 101
        )
    }

}