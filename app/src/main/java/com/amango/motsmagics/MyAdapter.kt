package com.amango.motsmagics

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.item_mot_magic.*
import kotlinx.android.synthetic.main.item_mot_magic.view.*

class MyAdapter(
    var mContext : Context,
    var ressource : Int,
    var values : ArrayList<Data>
)  : ArrayAdapter<Data>(mContext,ressource,values) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var data = values[position]
        var v = LayoutInflater.from(mContext).inflate(ressource, parent, false)

        //push data into in
        v.editText_i_1.setText(data.stringLettre_1)
        v.editText_i_2.setText(data.stringLettre_2)
        v.editText_i_3.setText(data.stringLettre_3)
        v.editText_i_4.setText(data.stringLettre_4)
        v.editText_i_5.setText(data.stringLettre_5)

        //Focus
        v.editText_i_1.requestFocus()
        v.editText_i_1.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                v.editText_i_2.requestFocus()
            }

        })
        v.editText_i_2.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                v.editText_i_3.requestFocus()
            }

        })
        v.editText_i_3.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                v.editText_i_4.requestFocus()
            }

        })
        v.editText_i_4.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                v.editText_i_5.requestFocus()
            }

        })
        v.editText_i_5.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                v.editText_i_1.requestFocus()
            }

        })

        return v
    }
}