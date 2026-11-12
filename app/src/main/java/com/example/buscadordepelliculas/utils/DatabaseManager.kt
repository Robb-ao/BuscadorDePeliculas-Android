package com.example.buscadordepelliculas.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.buscadordepelliculas.data.Movie

class DatabaseManager(context: Context) : SQLiteOpenHelper(context, "movies.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = """
            CREATE TABLE movies (
                imdbID TEXT PRIMARY KEY,
                title TEXT,
                year TEXT,
                type TEXT,
                poster TEXT
            )
        """.trimIndent()
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS movies")
        onCreate(db)
    }
}