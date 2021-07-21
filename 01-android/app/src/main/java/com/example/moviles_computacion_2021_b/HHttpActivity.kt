package com.example.moviles_computacion_2021_b

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class HHttpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hhttp)
        metodoGET()
        metodoPOST()
    }

    fun metodoGET(){
        "https://jsonplaceholder.typicode.com/posts/1"
            .httpGet()
            .responseString{ req, res, result ->
                when (result){
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon","Error ${error}")
                    }
                    is Result.Success -> {
                        val getString = result.get()
                        Log.i("http-klaxon","${getString}")

                        //"https://jsonplaceholder.typicode.com/posts/1" llega 1
                        //"https://jsonplaceholder.typicode.com/posts" llega muchos

                        val post = Klaxon()
                            .parse<IPostHTTP>(getString)
                        //.parseArray<IPostHTTP>(getString)
                        Log.i("http-klaxon","${post?.body}")
                    }
                }
            }
    }

    private fun metodoPOST() {
        val parametros: List<Pair<String, *>> = listOf(
            "tittle" to "Titulo Moviles",
            "body" to "descripcion moviles",
            "userId" to 1
        )

        "https://jsonplaceholder.typicode.com/posts"
            .httpPost(parametros)
            .responseString { req, res, result ->
                when (result) {
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error ${error}")
                    }
                    is Result.Success -> {
                        val postString = result.get()
                        Log.i("http-klaxon", "${postString}")

                        val post = Klaxon()
                            .parse<IPostHTTP>(postString)
                        Log.i("http-klaxon","${post?.title}")
                    }
                }
            }
    }

}