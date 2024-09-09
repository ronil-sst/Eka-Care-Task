package com.eka.care.task.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.eka.care.task.room.models.EntryModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

enum class StatusResult {
    Added,
    Updated,
    Deleted
}

fun View.hideKeyBoard() {
    try {
        val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.longToastShow(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun View.visible(value: Boolean = true) {
    if (value) {
        if (this.visibility != View.VISIBLE)
            this.visibility = View.VISIBLE
    } else {
        if (this.visibility == View.VISIBLE)
            this.visibility = View.GONE
    }
}

@SuppressLint("SimpleDateFormat")
fun currentTime(): String {
    val sdf = SimpleDateFormat("hh:mm aa")
    return sdf.format(Date())
}

@SuppressLint("SimpleDateFormat")
fun currentDate(): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    return sdf.format(Date())

}

@SuppressLint("SimpleDateFormat")
fun getMinDate(stringDate:String): Date? {
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    try {
        return sdf.parse(stringDate)
    } catch (e: ParseException) {
        e.printStackTrace()
        return Date()
    }
}

@SuppressLint("SetTextI18n")
fun Context.openCalender(minDate: Date?, maxDate: Date?, selectedDate: (Int, Int, Int) -> Unit) {
    val cal = Calendar.getInstance()
    val year = cal[Calendar.YEAR]
    val month = cal[Calendar.MONTH]
    val day = cal[Calendar.DAY_OF_MONTH]

    val dialog = DatePickerDialog(
        this,
        { _, _, _, dayOfMonth ->
            selectedDate(dayOfMonth, month + 1, year)
        },
        year, month, day
    )

    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    minDate?.time?.let {
        dialog.datePicker.minDate = it
    }
    maxDate?.time?.let {
        dialog.datePicker.maxDate = it
    }
    dialog.show()

}


@SuppressLint("SimpleDateFormat")
fun formatDateForUi(providedDate: String): String {
    val inputFormat = SimpleDateFormat("dd-MM-yyyy")
    val outputFormat = SimpleDateFormat("dd MMM yyyy")

    val outputDateStr = try {
        val date = inputFormat.parse(providedDate)
        date?.let { outputFormat.format(it) } ?: providedDate
    } catch (e: Exception) {
        e.printStackTrace()
        providedDate
    }
    return outputDateStr
}

@SuppressLint("SimpleDateFormat")
fun formatDateForFilters(providedDate: String): String {
    val inputFormat = SimpleDateFormat("dd-MM-yyyy")
    val sdf = SimpleDateFormat("dd-MM-yyyy")

    val outputDateStr = try {
        val date = inputFormat.parse(providedDate)
        date?.let { sdf.format(it) } ?: providedDate
    } catch (e: Exception) {
        e.printStackTrace()
        providedDate
    }
    return outputDateStr
}





interface TaskMenuOption {
    fun onEdit(task: EntryModel)
    fun changeStatus(task: EntryModel)
    fun deleteTask(task: EntryModel)
}
