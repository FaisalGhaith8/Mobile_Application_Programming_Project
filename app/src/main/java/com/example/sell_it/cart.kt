package com.example.sell_it

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.lang.reflect.Array.newInstance


class cart : AppCompatActivity() {
    @SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val capacities = arrayOf(128,256);
        var pos = 0;

        val t: String = "128"
        val t2: Int = t.toInt();

//        val temp = cart_frag("t","1","1",capacities,pos)



        val c : Array<cart_frag?> = Array(4) { null }

        val URI = CartProvider.CONTENT_URI
        val projection = arrayOf(CartProvider.PHONE_NAME, CartProvider.PRICE,CartProvider.CAP)
        val sortOrder = "order_id ASC LIMIT " + cart_count + " OFFSET 0"
        val cursor = contentResolver.query(URI, projection, null, null, sortOrder)

        var tt = 0;

        if (cursor != null && cursor.moveToFirst()) {
            var i=0
            do {
                val phoneName = cursor.getString(cursor.getColumnIndex(CartProvider.PHONE_NAME))
                val price = cursor.getString(cursor.getColumnIndex(CartProvider.PRICE))
                val capacity = cursor.getString(cursor.getColumnIndex(CartProvider.CAP))
                if (capacity.toInt()==256) pos = 1

                tt += price.toInt()
                c[i] = cart_frag(phoneName,price,phoneName.lowercase().replace(" ",""),capacities,pos,this,i)


                i++

            } while (cursor.moveToNext())
        }

        cursor?.close()

        supportFragmentManager.beginTransaction().apply {
            c[0]?.let { replace(R.id.c1, it) }
                           commit()
        }
        supportFragmentManager.beginTransaction().apply {
            c[1]?.let { replace(R.id.c2, it) }
            commit()
        }
        supportFragmentManager.beginTransaction().apply {
            c[2]?.let { replace(R.id.c3, it) }
            commit()
        }
        supportFragmentManager.beginTransaction().apply {
            c[3]?.let { replace(R.id.c4, it) }
            commit()
        }

        val total : TextView = findViewById(R.id.total)
        total.text = total.text.toString() + tt.toString() + " JOD"



    }
}