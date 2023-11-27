package com.obsessionmediagroup.muslimobsession

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    @RequiresApi(Build.VERSION_CODES.O)
    @Suppress("DEPRECATED")
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webView)

        WindowInsetsCompat.Type.navigationBars()

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.webViewClient = MyWebViewClient()

        // this will load the url of the website
        webView.loadUrl(URL)

        // this will enable the javascript settings, it can also allow xss vulnerabilities
        webView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        webView.settings.setSupportZoom(true)
    }

    // if you press Back button this code will work
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (webView.canGoBack())
            webView.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()
    }

    inner class MyWebViewClient : WebViewClient() {
        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            url: String
        ): Boolean {
            if (URLUtil.isNetworkUrl(url)) {
                return false
            } else if (url.startsWith("whatsapp:")) {
                try {
                    val sendIntent = Intent().apply {
                        this.action = Intent.ACTION_SEND
                        this.putExtra(Intent.EXTRA_TEXT, url)
                        this.type = "text/plain"
                    }
                    startActivity(sendIntent)
                    Toast.makeText(this@MainActivity, "Mengirim berita", Toast.LENGTH_SHORT).show()
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        this@MainActivity,
                        "Whatsapp belum terpasang",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return true
            } else if (url.startsWith("mailto:")) {
                try {
                    val sendMailIntent = Intent().apply {
                        this.action = Intent.ACTION_SEND
                        this.putExtra(Intent.EXTRA_EMAIL, url)
                        this.type = "text/plain"
                    }
                    startActivity(sendMailIntent)
                    Toast.makeText(this@MainActivity, "Mengirim berita", Toast.LENGTH_SHORT).show()
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(this@MainActivity, "Gagal mengirim email", Toast.LENGTH_SHORT)
                        .show()
                }
                return true
            }
            return false
        }
    }

    companion object {
        private const val URL = "https://www.muslimobsession.com/"
    }
}