package com.example.sell_it
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val URI = CartProvider.CONTENT_URI
        val rowsDeleted = contentResolver.delete(URI, null, null)


        val toggle = findViewById<View>(R.id.toggle1) as ToggleButton

        val appleacts: ImageView = findViewById(R.id.apple)
        val samsungacts: ImageView = findViewById(R.id.samsung)
        val huaweiacts: ImageView = findViewById(R.id.huawei)

        toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                startService(Intent(this, NewService::class.java))
            } else {
                stopService(Intent(this, NewService::class.java))
            }
        }

        appleacts.setOnClickListener {

            val intent = Intent(this, AppleActivity::class.java)

            startActivity(intent)
        }
        samsungacts.setOnClickListener {

            val intent = Intent(this, SamasungAct::class.java)

            startActivity(intent)
        }
        huaweiacts.setOnClickListener {

            val intent = Intent(this, HuaweiAct::class.java)

            startActivity(intent)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.app_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 -> {

                val intent = Intent(this, cart::class.java)

                startActivity(intent)
            }

        }
        return true;
    }




}