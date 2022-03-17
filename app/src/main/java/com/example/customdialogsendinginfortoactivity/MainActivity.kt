package com.example.customdialogsendinginfortoactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), ExampleDialog.ExampleDialogListener {
    private lateinit var textViewUserName : TextView
    private lateinit var textViewPassword : TextView
    private lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewUserName = findViewById(R.id.textview_username) as TextView
        textViewPassword = findViewById(R.id.textview_password) as TextView
        button = findViewById(R.id.button) as Button

        button.setOnClickListener(View.OnClickListener {
            openDialog()
        })
    }

    private fun openDialog() {
        var dialog = ExampleDialog()
        dialog.show(supportFragmentManager, "example dialog")
    }

    override fun applyTexts(username: String, password: String) {
        //여기서는 안에 들어갔던곳에서 값을 받아서 오는거지..그 값을 사용한다.
        textViewUserName.setText(username)
        textViewPassword.setText(password)
    }
}