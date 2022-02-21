package com.amango.motsmagics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.fragment.app.Fragment

import com.amango.motsmagics.fragments.GameFragment
import com.amango.motsmagics.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_mot_magic.*

class MainActivity : AppCompatActivity() {
    private val gameFragment = GameFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(gameFragment, R.string.menu_item_1)
        bottomnavigationview.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_item_1_id -> replaceFragment(gameFragment, R.string.menu_item_1)
                R.id.menu_item_2_id -> replaceFragment(settingsFragment, R.string.menu_item_2)
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun replaceFragment(fragment : Fragment, fragmentTitle : Int){
        if (fragment !=null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.framelayout_fragment_container, fragment)
            //textView_title_fragment.setText(title_fragment)
            textView_title_appbar.setText(fragmentTitle)
            transaction.commit()
        }
    }

    //Function to change editText Focus
    private fun focusToAnotherView(){
        this.editText_i_1.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (editText_i_1.text.toString().length == 1) {
                    editText_i_2.requestFocus()
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun afterTextChanged(s: Editable) {

            }
        })
    }
}