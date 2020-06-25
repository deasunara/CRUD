package com.deasunara.crud

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    internal var dbHelper = DatabaseHelper(this)

    fun showToast(text: String){
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_LONG).show()
    }

    fun showDialog(title : String,Massage : String) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Massage)
        builder.show()
    }

    fun clearEditTexts(){
        txtNama.setText("")
        txtNim.setText("")
        txtFakultas.setText("")
        txtProdi.setText("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleInserts()
        handleUpdates()
        handledeletes()
        handleViewing()
    }

    fun  handleInserts () {
        btnInsert.setOnClickListener {
            try {
                dbHelper.inserData(txtNama.text.toString(),txtNim.text.toString()
                        ,txtFakultas.text.toString(),txtProdi.text.toString())
                clearEditTexts()
            }catch (e: Exception){
                e.printStackTrace()
                showToast(e.message.toString())

            }
        }

    }

    fun handleUpdates(){
        btnUpdate.setOnClickListener {
            try {
              val isUpdate = dbHelper.updateData(txtNama.text.toString(),txtNim.text.toString()
                      ,txtFakultas.text.toString(),txtProdi.text.toString())
                if (isUpdate == true)
                    showToast("data selesai di update")
                else
                    showToast("data tidak diupdate")
            }catch (e: Exception){
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }


    fun handledeletes(){
        btnDelete.setOnClickListener {
            try {
                dbHelper.inserData(txtNama.text.toString(),txtNim.text.toString()
                        ,txtFakultas.text.toString(),txtProdi.text.toString())
                clearEditTexts()
            }catch (e: Exception){
            e. printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    fun handleViewing(){
        btnView.setOnClickListener{
         val res = dbHelper.allData
            if (res.count == 0) {
                showDialog("error", "No Data Found")
                return@setOnClickListener
            }

            val  buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.append("Nama :" + res.getString(0) + "\n")
                buffer.append("Nim :" + res.getString(1) + "\n")
                buffer.append("Fakultas :" + res.getString(2) + "\n")
                buffer.append("Prodi :" + res.getString(3) + "\n")
            }
            showDialog("Data Listing", buffer.toString())
        }
    }
}
