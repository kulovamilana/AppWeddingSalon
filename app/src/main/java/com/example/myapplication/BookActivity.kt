package com.example.myapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.util.Locale

class BookActivity : AppCompatActivity() {
    private lateinit var bookButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        // Инициализация кнопки
        bookButton = findViewById(R.id.bookButton)

        // Установка обработчика нажатия на кнопку
        bookButton.setOnClickListener {
            // Открываем диалог выбора даты и времени
            showDateTimePickerDialog()
        }
    }

    private fun showDateTimePickerDialog() {
        // Используем Calendar для установки начальной даты и времени
        val calendar = Calendar.getInstance()

        // Создаем DatePickerDialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                // Устанавливаем выбранную дату в Calendar
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                // После выбора даты открываем TimePickerDialog
                showTimePickerDialog(calendar)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Показываем DatePickerDialog
        datePickerDialog.show()
    }

    private fun showTimePickerDialog(calendar: Calendar) {
        // Создаем TimePickerDialog
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                // Устанавливаем выбранное время в Calendar
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)

                // Форматируем дату и время
                val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                val selectedDateTime = dateFormat.format(calendar.time)

                // Действия с выбранной датой и временем (например, отображение в Toast)
                Toast.makeText(this, "Выбрано: $selectedDateTime", Toast.LENGTH_SHORT).show()
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )

        // Показываем TimePickerDialog
        timePickerDialog.show()
    }
}