package com.example.sell_it

import android.content.ContentUris
import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.content.ContentResolver
import android.graphics.Paint.Cap
import android.net.Uri
import android.provider.SyncStateContract.Helpers.update
import androidx.appcompat.app.AppCompatActivity


class cart_frag(n: String, p: String, img_n: String,capacity: Array<Int>,pos:Int,actt: AppCompatActivity, f: Int) : Fragment() {

    private lateinit var rootView: View
    private lateinit var nameTV: TextView
    private lateinit var priceTV: TextView
    private lateinit var img_nameTV: ImageView
    val name = n
    val price = p
    val img_name = img_n
    val capacity = capacity
    val pos = pos
    val actt = actt
    val f = f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.cart_option, container, false)

        // Initialize views
        nameTV = rootView.findViewById(R.id.name) as TextView
        priceTV = rootView.findViewById(R.id.price) as TextView
        img_nameTV = rootView.findViewById(R.id.img) as ImageView

        // Set text of TextView
        nameTV.text = name
        priceTV.text = price



        val drawableId = resources.getIdentifier(img_name, "drawable", "com.example.sell_it")
        val imageDRAWABLE = resources.getDrawable(drawableId, null)
        img_nameTV.setImageDrawable(imageDRAWABLE)

//        var chosen_cap : String = ""
//        val cap_spinner = view.findViewById<Spinner>(R.id.cap_Spinner)
//        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, capacity)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        cap_spinner.adapter = adapter



        var cap_spinner = rootView.findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, capacity)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        cap_spinner.adapter = adapter
        cap_spinner.setSelection(pos)
        var newC: String = ""
        cap_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                newC = capacity[p2].toString()

                val uri = ContentUris.withAppendedId(CartProvider.CONTENT_URI, id.toLong())
                val values = ContentValues()
                values.put(CartProvider.PHONE_NAME, name)
                values.put(CartProvider.PRICE, price)
                values.put(CartProvider.CAP, newC)
//                val count = actt.contentResolver.update(uri, values, null, null)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }}

        val del_button : Button = rootView.findViewById(R.id.delete)
        del_button.setOnClickListener(){
            img_nameTV.visibility = View.GONE
            cap_spinner.visibility = View.GONE
            nameTV.visibility = View.GONE
            priceTV.visibility = View.GONE
            del_button.visibility = View.GONE
            Toast.makeText(activity, name+" deleted from cart", Toast.LENGTH_SHORT).show()
            cart_count--


            val URI = CartProvider.CONTENT_URI
            val where = CartProvider.ORDER_ID + " = " + (f+1).toString()
            val selectionArgs = null
            val rowsDeleted = actt.contentResolver.delete(URI, where, selectionArgs)


        }


        return rootView
    }

}