package com.deasunara.crud

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class  DatabaseHelper(context : Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME (" +
                "$COL_1 INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_2 TEXT NOT NULL," +
                "$COL_3 TEXT NOT NULL," +
                "$COL_4 TEXT NOT NULL," +
                "$COL_5 TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXITS" + TABLE_NAME)
        onCreate(db)

    }

    fun inserData(name: String, nim: String, fakultas: String, prodi: String) {

        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_2, name)
        contentValues.put(COL_3, nim)
        contentValues.put(COL_4, fakultas)
        contentValues.put(COL_5, prodi)

        db.insert(TABLE_NAME, null, contentValues)
    }

    fun updateData(id: String, name: String, nim: String, fakultas: String, prodi: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_1, id)
        contentValues.put(COL_2, name)
        contentValues.put(COL_3, nim)
        contentValues.put(COL_4, fakultas)
        contentValues.put(COL_5, prodi)
        db.update(TABLE_NAME, ContentValues(), "ID = ?", arrayOf(id))
        return true
    }

    fun deleteData(id : String) : Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "ID = ?", arrayOf(id))
    }
    val allData : Cursor
        get() {
            val db = this.writableDatabase
            val res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
            return  res
        }

    companion object {
        val DATABASE_NAME = "mahasiswa.db"
        val TABLE_NAME = "data_mahasiswa"
        val COL_1 = "ID"
        val COL_2 = "nama"
        val COL_3 = "nim"
        val COL_4 = "fakultas"
        val COL_5 = "prodi"
    }

}
