package com.example.mysqliapp

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class    MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Creamos la clase de SQLiteHelper
        val amigosDBHelper: miSQLiteHelper = miSQLiteHelper(this)

        // relacionar variables con componentes del diseño
        val btnInsert: Button = findViewById(R.id.btnInsert)
        val btnQuery: Button = findViewById(R.id.btnQuery)
        val txtName: EditText = findViewById<EditText>(R.id.txtName)
        val txtEmail: EditText = findViewById(R.id.txtEmail)
        val txtDeploy: TextView = findViewById(R.id.txtDeploy)

        // Boton eliminar
        val btnDelete: Button = findViewById(R.id.btnDelete)

        btnInsert.setOnClickListener {
            if(txtName.text.isNotBlank() && txtEmail.text.isNotBlank()){
                amigosDBHelper.addFriend(txtName.text.toString(), txtEmail.text.toString())
                txtName.text.clear()
                txtEmail.text.clear()
                Toast.makeText(this, "Registro Insertado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Registro No Insertado", Toast.LENGTH_LONG).show()
            }
        }

        btnQuery.setOnClickListener {
            txtDeploy.text = ""
            val selectQuery = "SELECT * FROM amigos"
            val db: SQLiteDatabase = amigosDBHelper.readableDatabase
            val cursor: Cursor?
            cursor = db.rawQuery(selectQuery, null)

            if(cursor.moveToFirst()){
                do {
                    var id = cursor.getInt(0).toString() + ": "
                    var name = cursor.getString(1).toString() + ", "
                    var email = cursor.getString(2).toString() + "\n "
                    txtDeploy.append(id + name + email)
                } while (cursor.moveToNext())
            }
        }

        btnDelete.setOnClickListener {
            amigosDBHelper.delete()
        }
    }
}