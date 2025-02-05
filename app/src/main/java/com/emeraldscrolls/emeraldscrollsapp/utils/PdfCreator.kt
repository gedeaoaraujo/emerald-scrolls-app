package com.emeraldscrolls.emeraldscrollsapp.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.os.Environment
import android.text.TextPaint
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.emeraldscrolls.emeraldscrollsapp.model.ScrollModel
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDateTime


object PdfCreator {

    private const val PADDING = 40f
    private const val PAGE_WIDTH = 595
    private const val PAGE_HEIGHT = 842

    fun generatePDF(context: Context, scroll: ScrollModel?): Boolean {
        if (canReadAndWrite(context).not())
            requestPermission(context as Activity)

        val pdfDocument = PdfDocument()
        val dateTimeFile = LocalDateTime.now().dateTimeFile()
        val fileName = "Scroll-$dateTimeFile.pdf"

        scroll?.run { drawPage(date, title, text, pdfDocument) }
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

    private fun drawPage(
        date: LocalDateTime?,
        title: String,
        text: String,
        pdfDocument: PdfDocument
    ) {
        val lastPaint = TextPaint().also {
            it.textSize = 20f
            it.typeface = Typeface.DEFAULT_BOLD
        }

        var pageNumber = 1
        val words = text.split(" ")

        var info = PageInfo
            .Builder(PAGE_WIDTH, PAGE_HEIGHT, pageNumber)
            .create()
        var page = pdfDocument.startPage(info)

        var canvas = page.canvas
        var rowPosition = PADDING
        val width = canvas.width - 80f
        canvas.drawText(title, PADDING, rowPosition, lastPaint)

        lastPaint.typeface = Typeface.DEFAULT
        rowPosition += lastPaint.textSize * 1.5f
        val dateTime = date?.dateTimeFmt().orEmpty()
        canvas.drawText(dateTime, PADDING, rowPosition, lastPaint)

        var tempLine = String()
        val lines = mutableListOf<String>()
        words.forEach { word ->
            var newLine = "$tempLine$word "

            if (newLine.contains("\n\n")){
                val split = newLine.split("\n\n")
                newLine = "${split[0]}\n "
                lines.add(newLine)
                newLine = "${split[1]} "
            }

            val lineWidth = lastPaint.measureText(newLine)
            if (lineWidth <= width) {
                tempLine = newLine
            } else {
                lines.add(newLine)
                tempLine = ""
            }
        }

        rowPosition += lastPaint.textSize / 2
        lines.forEachIndexed { index, line ->
            val heightTest = PAGE_HEIGHT - PADDING - rowPosition

            if (index != 0 && heightTest <= 0){
                pdfDocument.finishPage(page)
                info = PageInfo.Builder(
                    PAGE_WIDTH, PAGE_HEIGHT, ++pageNumber
                ).create()
                page = pdfDocument.startPage(info)
                canvas = page.canvas
                rowPosition = PADDING
            }

            if (line.contains("\n")){
                rowPosition += lastPaint.textSize
                canvas.drawText(line, PADDING, rowPosition, lastPaint)
                rowPosition += lastPaint.textSize
            } else {
                rowPosition += lastPaint.textSize
                canvas.drawText(line, PADDING, rowPosition, lastPaint)
            }

            if (index == lines.lastIndex) {
                pdfDocument.finishPage(page)
            }
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