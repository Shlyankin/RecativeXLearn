package com.heads.thinking.reactivexlearn.test

import android.content.Context
import android.os.SystemClock



class RestClient(private val mathContext: Context) {
    fun getFavoriteBooks(): List<String> {
        SystemClock.sleep(4000)// "Simulate" the delay of network.
        return createBooks()
    }

    fun getFavoriteBooksWithException(): List<String> {
        SystemClock.sleep(3500)// "Simulate" the delay of network.
        throw RuntimeException("Failed to load")
    }

    private fun createBooks(): List<String> {
        val books = arrayListOf<String>(
            "Lord of the Rings",
            "The dark elf",
            "Eclipse Introduction",
            "History book",
            "Der kleine Prinz",
            "7 habits of highly effective people",
            "Other book 1", "Other book 2", "Other book 3", "Other book 4", "Other book 5", "Other book 6")
        return books
    }

}