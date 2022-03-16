package com.example.memegenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
private const val TAG ="M"
class MainActivity : AppCompatActivity() {
    var currUrl: String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        loadMeme()
        btnNext.setOnClickListener{
            loadMeme()
        }
        btnShare.setOnClickListener{
            share()
        }

    }
    fun share(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type ="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"See this is so Funny!!!! ${currUrl}")
        val chooser = Intent.createChooser(intent,"share this meme")
        startActivity(chooser)

    }

    private fun loadMeme() {
        val queue = Volley.newRequestQueue(this)
        currUrl = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, currUrl, null,
            Response.Listener { response ->
                    val url = response.getString("url")
                Glide.with(this).load(url).into(ivMeme)
            },
            Response.ErrorListener { error ->
                Log.d(TAG,"error is ${error}")
            }
        )

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }


    }