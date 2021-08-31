package com.example.countryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        textView = findViewById<TextView>(R.id.tv_pl_names)
//        var names = listOf("Java","Kotlin","Ruby"," JS","C","C++","Lisp","adsasd",
//        "asdasd","gdsgds","sdgfh")
//
//        textView.setText("")
//        for (elem in names){
//            textView.append("$elem \n")
//        }
    }
}