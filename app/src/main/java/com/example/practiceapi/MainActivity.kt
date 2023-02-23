package com.example.practiceapi

import android.content.Intent
import android.graphics.drawable.Drawable
import android.hardware.biometrics.BiometricManager.Strings
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.DrawableUtils
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {
    lateinit var txt: TextView
    lateinit var img: ImageView
lateinit var prog: ProgressBar
    var imageUrl: String?=null
    var url="https://api.ebird.org/v2/data/obs/{{regionCode}}/recent\n"
//    var url = "https://meme-api.com/gimme"
//    val url = "https://meme-api.herokuapp.com/gimme"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//first project
//        txt=findViewById(R.id.txt_id)
//        val queue= Volley.newRequestQueue(this)
////        var url="http://www.google.com"
//        var url="https://api.twitter.com/2/tweets/search/recent?q=%23hashtag"
////       var url ="https://weatherbit-v1-mashape.p.rapidapi.com/forecast/3hourly"
////        var k="https://national-weather-service.p.rapidapi.com/zones/%7Btype%7D/%7BzoneId%7D/forecast"
//
//        val StringRequest= StringRequest(
//            Request.Method.GET, url, Response.Listener<String> { response ->
//
//                txt.text = "Response is: ${response.substring(0, 500)}"
//            },
//            Response.ErrorListener { txt.text = "That didn't work!" })
//        queue.add(StringRequest)
//    }
//}



//second project
//
        img = findViewById(R.id.img_id)
//        var url = "https://meme-api.com/gimme"
//                val url = "https://meme-api.herokuapp.com/gimme"
//        img.setOnClickListener() {
//            getmemu()
//        }
//    }
//    private fun getmemu(){
//    val queue=Volley.newRequestQueue(this)
//    var stringRequest=JsonObjectRequest(
//        Request.Method.GET,url,null,{
//            Glide.with(this).load(it.get(imageUrl).toString()).into(img);
//        },
//        {
//            Log.d("Error",it.toString())})
//        queue.add(stringRequest)
//        }
//fun share(view: View) {
//    val intent = Intent(Intent.ACTION_SEND)
//    intent.type="text/plain"
//    startActivity(intent)
//}
//}



        //third project
prog= findViewById(R.id.pro_id)
//        val imageUrl:String?=null

        loadMemu()
//        val url = "https://meme-api.herokuapp.com/gimme"
    }
    private fun loadMemu() {
        prog.visibility=View.VISIBLE

        val queue = Volley.newRequestQueue(this)
       val jsonObjectRequest=JsonObjectRequest(
//                           Glide.with(this).load(it.get(imageUrl).toString()).into(img);
               Request.Method.GET,url,null,{
               imageUrl=it.get("url").toString()
//               Glide.with(this).load(imageUrl).into(img);

               Glide.with(this).load(it.get("url").toString()).listener(object:RequestListener<Drawable>{
                   override fun onLoadFailed(
                       e: GlideException?,
                       model: Any?,
                       target: Target<Drawable>?,
                       isFirstResource: Boolean
                   ): Boolean {
                       prog.visibility=View.GONE
                       return false
                   }

                   override fun onResourceReady(
                       resource: Drawable?,
                       model: Any?,
                       target: Target<Drawable>?,
                       dataSource: DataSource?,
                       isFirstResource: Boolean
                   ): Boolean {
                       prog.visibility=View.GONE
                       return false
                   }
           }).into(img)
           },
           {
               Log.d("Error kalyani",it.toString())})
               queue.add(jsonObjectRequest)
    }

    fun share(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,"hey guy see this gift for you $imageUrl")
        var chooser=Intent.createChooser(intent,"check this image")
        startActivity(intent)
    }

    fun nextMenu(view: View) {
        loadMemu()
    }
}
