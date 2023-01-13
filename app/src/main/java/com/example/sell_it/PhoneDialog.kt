package com.example.sell_it

import android.annotation.SuppressLint
import android.content.ContentValues
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import android.content.ContentResolver
import android.provider.SyncStateContract.Helpers.insert
import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.widget.EditText


import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

var cart_count: Int = 1

class PhoneDialog(
    val name: String, val disp_size: String,
    val capacity: Array<Int>, val price: Int, val img_name: String, val actt: AppCompatActivity): DialogFragment(R.layout.device_fragment) {
    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cancelBT : Button = view.findViewById(R.id.back);
        val add_cartBT : Button = view.findViewById(R.id.cart);


        val nameTV: TextView = view.findViewById(R.id.tv1)
        val disp_sizeTV: TextView = view.findViewById(R.id.tv2)
        val capacityTV: TextView = view.findViewById(R.id.tv3)

        var chosen_cap : String = ""
        val cap_spinner = view.findViewById<Spinner>(R.id.cap_Spinner)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, capacity)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cap_spinner.adapter = adapter
        cap_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                chosen_cap = capacity[p2].toString()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }}




        val priceTV: TextView = view.findViewById(R.id.tv4)

        val img: ImageView = view.findViewById(R.id.imageView)

        nameTV.text = "Device name: " + name
        disp_sizeTV.text = "Display size: " + disp_size

        capacityTV.text = "Available Capacity: "


        priceTV.text = "Price: " + price.toString() + "JOD"

        val drawableId = resources.getIdentifier(img_name, "drawable", "com.example.sell_it")
        val imageDRAWABLE = resources.getDrawable(drawableId, null)
        img.setImageDrawable(imageDRAWABLE)

        cancelBT.setOnClickListener{
            dismiss()
        }

        add_cartBT.setOnClickListener{

            if(cart_count<5){
            val values = ContentValues()
            values.put(CartProvider.PHONE_NAME, name)
            values.put(CartProvider.PRICE, price.toString())
            values.put(CartProvider.CAP, chosen_cap)
            val uri = actt.contentResolver.insert(CartProvider.CONTENT_URI, values)
             Toast.makeText(activity, name+" added to your cart", Toast.LENGTH_SHORT).show()
                cart_count++
            }
            else{
                Toast.makeText(activity, "Cart is full!", Toast.LENGTH_SHORT).show()

            }
            dismiss()
        }


    }


//    fun onClickAddName(view: View?) {
//        val values = ContentValues()
//        values.put(
//            CartProvider.PHONE_NAME,
//            name
//        )
//        values.put(
//            CartProvider.PRICE,
//            price
//        )
//
//        val uri = actt.contentResolver.insert(
//            CartProvider.CONTENT_URI, values
//        )
//        Toast.makeText(actt.baseContext, uri.toString(), Toast.LENGTH_LONG).show()
//    }

}

