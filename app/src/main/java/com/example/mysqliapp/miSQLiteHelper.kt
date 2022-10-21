package com.example.mysqliapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class miSQLiteHelper(context: Context) : SQLiteOpenHelper(
    context,  "amigos.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE amigos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, email TEXT)"
        db!!.execSQL(ordenCreacion)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val borrado = "DROP TABLE IF EXISTS amigos"
        db!!.execSQL(borrado)
        onCreate(db)
    }
    fun addFriend(nombre: String, email: String){
        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("email", email)
        val db = this.writableDatabase
        db.insert("amigos", null, datos)
        db.close()
    }

    fun delete() {
        val sqlQuery = "delete from amigos"
        val db = this.writableDatabase
        db.execSQL(sqlQuery)
    }
}