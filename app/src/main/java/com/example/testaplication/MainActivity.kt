package com.example.testaplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView_main.layoutManager = LinearLayoutManager(this)


        fetchJson()
    }

    fun fetchJson() {
        println("Attempting to Fetch JSON")

        val url = "http://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=colombia&api_key=829751643419a7128b7ada50de590067&format=json"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()?.replace('#','u')

                println(body)

                val gson = GsonBuilder().create()

                val topArtist = gson.fromJson(body, topartist::class.java)
                println("artistas->")
                println(gson.toJson(topArtist.topartists))
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(topArtist.topartists)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed to execute request")
                println(e)
            }
        })
    }
}





