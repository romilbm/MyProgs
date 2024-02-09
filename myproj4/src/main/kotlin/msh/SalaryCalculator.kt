package msh

import com.lowagie.text.Chunk
import com.lowagie.text.Document
import com.lowagie.text.Element
import com.lowagie.text.Font
import com.lowagie.text.PageSize
import com.lowagie.text.Paragraph
import com.lowagie.text.Phrase
import com.lowagie.text.pdf.PdfContentByte.ALIGN_CENTER
import com.lowagie.text.pdf.PdfPCell
import com.lowagie.text.pdf.PdfPTable
import com.lowagie.text.pdf.PdfWriter
import java.awt.Color
import java.io.File
import java.io.FileOutputStream
import java.time.Month
import java.time.YearMonth
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class SalaryCalculator {

    fun printDetailAttendanceRecord(
        fileName: String,
        month: Int,
        year: Int,
        path: String,
    ) {
        val empRecordsFromRawText = getEmployeeRecordByDays(
            fileName,
            month,
            year
        )

        val recordsToPrint = getAttendanceDetailsToPrint(
            empRecordsFromRawText.toSortedMap(),
            YearMonth.of(year, month).lengthOfMonth(),
        )

        makePdfFile(
            recordsToPrint,
            month,
            year,
            YearMonth.of(year, month).lengthOfMonth(),
            path
        )
    }

    // refer: https://betterprogramming.pub/creating-pdf-files-with-kotlin-and-openpdf-af0e0700e2b7
    private fun makePdfFile(
        recordsToPrint: List<List<String>>,
        month: Int,
        year: Int,
        daysInMonth: Int,
        path: String,
    ) {
        val pdfOutputFile = FileOutputStream("$path/attendance-detail-${Month.of(month).name}-$year-V$VERSION.pdf")
        val myPDFDoc = Document(
            PageSize.A4.rotate(),
            5f,   // left
            5f,   // right
            20f,  // top
            20f
        )

        val title = "Attendance Record For ${Month.of(month).name} $year"

        val pdfWriter = PdfWriter.getInstance(myPDFDoc, pdfOutputFile)

        // Y = W_time * X
        // Y + dayInMonth*X = 100
        // W_times * X + dayInMonth*X = 100
        // (W_times + daysInMonth)X = 100
        // X = 100/(W_times+daysInMonth)
        val wTimes = 3f
        val x = 100f/(wTimes+daysInMonth)
        val y = wTimes*x

        val widths = FloatArray(daysInMonth+1)
        widths[0] = y
        (1..daysInMonth).forEach { widths[it] = x }

        val datatable = PdfPTable(daysInMonth+1).apply {
            setWidths(widths)
        }

        val tableHeaderFont = Font(Font.COURIER, 8f, Font.BOLD, Color.WHITE)
        recordsToPrint[0].forEach { headerCell ->
            datatable.addCell(PdfPCell(Phrase(headerCell, tableHeaderFont)).apply {
                    backgroundColor = Color.BLACK
                    borderColorLeft = Color.WHITE
                    borderColorRight = Color.WHITE
                    horizontalAlignment = ALIGN_CENTER
                    verticalAlignment = ALIGN_CENTER
                })
        }

        val rowFont = Font(Font.COURIER, 5f, Font.NORMAL, Color.BLACK)
        recordsToPrint.drop(1).forEach { record ->
            record.forEach { cellData ->
                datatable.addCell(PdfPCell(Phrase(cellData, rowFont)).apply {
                    backgroundColor = if (cellData == "A") Color.LIGHT_GRAY else Color.WHITE
                    borderColor = Color.BLACK
                    horizontalAlignment = ALIGN_CENTER
                    verticalAlignment = ALIGN_CENTER
                })
            }
        }


        myPDFDoc.apply {
            addTitle("MSH Employee Attendance Record for ${Month.of(month).name} $year")
            addSubject("This is a the daily attendance record of employees of MSH for ${Month.of(month).name} $year")
            addCreator("Romil Mamaniya")
            addAuthor("Gracy Mamaniya")

            open()

            add(
                Paragraph(title, Font(Font.COURIER, 20f, Font.BOLD, Color.BLACK)).apply {
                    alignment = Element.ALIGN_CENTER
                }
            )
            add(Paragraph(Chunk.NEWLINE))
            add(datatable)
            close()
        }

        pdfWriter.close()
    }

    private fun getEmployeeRecordByDays(
        fileName: String,
        month: Int,
        year: Int,
    ): Map<Int, Pair<Map<Int, List<LineRecord>>, Map<Int, List<LineRecord>>>> {
        val lineRecords = getContentsOfFile(fileName, month, year)
        val empRecords = lineRecords.groupBy { it.empNo }
        return empRecords.keys.associateWith {
            Pair(
                empRecords[it]!!
                    .filter { record -> record.timeType == TimeType.IN }
                    .groupBy { record -> record.timeRecorded.dayOfMonth },
                empRecords[it]!!
                    .filter { record -> record.timeType == TimeType.OUT }
                    .groupBy { record -> record.timeRecorded.dayOfMonth }
            )
        }

//        return empRecords.keys.associateWith { empRecords[it]!!.groupBy { record -> record.timeRecorded.dayOfMonth } }
    }

    private fun getAttendanceDetailsToPrint(
        empRecords: Map<Int, Pair<Map<Int, List<LineRecord>>, Map<Int, List<LineRecord>>>>,
        daysInMonth: Int,
    ): List<List<String>> {
        val rows = mutableListOf<List<String>>()

        // Header Row
        val headerRow = mutableListOf<String>()

        headerRow.add(EMPLOYEE_COLUMN)
        (1..daysInMonth).forEach {
            headerRow.add(it.toString())
        }
        rows.add(headerRow.toList())

        empRecords.entries.forEach {
            // Second line
            // Empty name field
            val row2 = mutableListOf<String>()
            row2.add(EMPTY_CELL_DATA)
            // Out times if they exist
            (1..daysInMonth).forEach { dayOfTheMonth ->
                val timings = it.value.second[dayOfTheMonth]
                if (timings != null) row2.add(timings[0].timeRecorded.format(dateTimeWriteFormatter))
                else row2.add(BLANK_CELL)
            }
            // End second line

            val row1 = mutableListOf<String>()
            // First Line
            // Emp no/name
            var empNameNo = empMap[it.key]
            if (empNameNo.isNullOrBlank()) empNameNo = "$UNKNOWN_EMPLOYEE (${it.key})"

            row1.add(empNameNo)
            // In timings
            (1..daysInMonth).forEach { dayOfTheMonth ->
                val timings = it.value.first[dayOfTheMonth]
                if (timings != null) row1.add(timings[0].timeRecorded.format(dateTimeWriteFormatter))
                else {
                    if (row2[dayOfTheMonth] == BLANK_CELL) row1.add(ABSENT)
                    else row1.add(BLANK_CELL)
                }
            }
            // End first line

            rows.add(row1.toList())
            rows.add(row2.toList())
        }
        return rows
    }

    private fun getContentsOfFile(
        fileName: String,
        month: Int,
        year: Int,
    ) : List<LineRecord> {
        val allLines:MutableList<LineRecord> = mutableListOf()
        File(fileName).forEachLine {
            if (invalidStartCharacters.contains(it.first())) return@forEachLine
            val split = it.split(RAW_FILE_SEPARATOR)
            val time = ZonedDateTime.from(dateTimeReadFormatter.parse(split[DATE_TIME_COL_NUM]))
            if (month != time.monthValue || year != time.year) return@forEachLine
            val empNo = Integer.parseInt(split[EMPLOYEE_ID_COL_NUM])
            val timeType = if (split[IN_OUT_COL_NUM] == IN_COL_RAW_DATA) TimeType.OUT else TimeType.IN
            allLines.add(LineRecord(empNo, time, timeType))
        }
        return allLines.toList()
    }

    companion object {
        val dateTimeReadFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss")
            .withZone(ZoneId.of("Asia/Kolkata"))
        val dateTimeWriteFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
            .withZone(ZoneId.of("Asia/Kolkata"))

        val empMap = mapOf(
            2 to "Vandana Khare",
            3 to "Mini Wilson",
            4 to "Milan Naik",
            5 to "Kalpana Mamaniya",
            6 to "Meena Walekar",
            7 to "",
            8 to "",
            9 to "Shalini Matthews",
            10 to "Vandita Bharadwaj",
            11 to "Priyanka Chavan",
            12 to "Annamma Samuel",
            13 to "",
            14 to "",
            15 to "Kalavati Adam",
            16 to "Supriya Padave",
            17 to "Sridevi Ravindran",
            18 to "",
            19 to "",
            20 to "",
            21 to "Aliyemma Phillip",
            22 to "Anita Bhoia",
        )

        const val EMPLOYEE_COLUMN = "Employee"
        const val UNKNOWN_EMPLOYEE = "Unknown"
        const val ABSENT = "A"
        const val EMPTY_CELL_DATA = " "
        const val RAW_FILE_SEPARATOR = "\t"
        const val DATE_TIME_COL_NUM = 9
        const val EMPLOYEE_ID_COL_NUM = 2
        const val IN_OUT_COL_NUM = 6
        const val IN_COL_RAW_DATA = "E"
        const val VERSION = 2
        const val BLANK_CELL = " "
        val invalidStartCharacters = setOf('#', 'N')
    }
}

fun main(vararg args: String) {
    val sc = SalaryCalculator()
    val fileName = "/Users/romil/Downloads/megha hospital.TXT"
    val month = 12
    val year = 2023
    sc.printDetailAttendanceRecord(
        fileName,
        month,
        year,
        "/Users/romil/Downloads"
    )
}

data class LineRecord(
    val empNo: Int,
    val timeRecorded: ZonedDateTime,
    val timeType: TimeType,
)

enum class TimeType {
    IN,
    OUT
}