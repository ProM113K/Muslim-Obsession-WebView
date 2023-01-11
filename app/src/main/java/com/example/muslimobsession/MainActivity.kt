package com.example.muslimobsession

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WindowInsetsCompat.Type.navigationBars()

        val webView:WebView = findViewById(R.id.webView)
        webView.loadUrl("https://www.muslimobsession.com/")
    }
}