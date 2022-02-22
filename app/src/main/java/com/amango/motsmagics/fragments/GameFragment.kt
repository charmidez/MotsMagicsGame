package com.amango.motsmagics.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.amango.motsmagics.Data
import com.amango.motsmagics.DataWords
import com.amango.motsmagics.MyAdapter
import com.amango.motsmagics.R
import kotlinx.android.synthetic.main.fragment_game.view.*
import kotlinx.android.synthetic.main.item_mot_magic.view.*


class GameFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var firstLettre  = ""
        var secondLettre  = ""
        var threeLettre  = ""
        var fourLettre  = ""
        var fiveLettre  =""

        var allWordToFind : ArrayList<String> = arrayListOf("")
        var getWord  = ""
        var wordToFind  = ""

        val v = inflater.inflate(R.layout.fragment_game, container, false)
        var myAdapter : MyAdapter
        var itemLigneSelected : Int
        var itemWordSelected : Int
        var itemList_data : ArrayList<Data>
        var wrong : Int = R.drawable.edittext_wrong_letter
        var correct : Int = R.drawable.edittext_correct_letter

        itemList_data = ArrayList(arrayListOf(Data("","","","","")))
        myAdapter = MyAdapter(v.context, R.layout.item_mot_magic, itemList_data)
        itemLigneSelected = 0
        itemWordSelected = 0
        allWordToFind = arrayListOf("")

        var getWordToFindFromFireBase = fun(){
            allWordToFind = arrayListOf("kokou", "kodjo", "voffi")
            allWordToFind.add(0,"kokot")
            //allWordToFind = DataWords().allWord
            wordToFind = allWordToFind[itemWordSelected]
        }
        getWordToFindFromFireBase()

        var getValueEditText = fun (){
            firstLettre = v.listView_game[itemLigneSelected].editText_i_1.text.toString()
            secondLettre = v.listView_game[itemLigneSelected].editText_i_2.text.toString()
            threeLettre = v.listView_game[itemLigneSelected].editText_i_3.text.toString()
            fourLettre = v.listView_game[itemLigneSelected].editText_i_4.text.toString()
            fiveLettre = v.listView_game[itemLigneSelected].editText_i_5.text.toString()

            getWord = firstLettre + secondLettre + threeLettre + fourLettre + fiveLettre
        }

        var holderAndSaveText = fun (){
            itemList_data.set(itemLigneSelected,Data(firstLettre,secondLettre,threeLettre,fourLettre,fiveLettre))
            itemList_data.add(Data("","","","",""))
            v.listView_game.adapter = myAdapter
            itemLigneSelected++
        }

        var changeEditTextColor = fun(edittext : EditText){
            // Wifi code canal box 9720372497
            for (i in 0.. wordToFind.length -1){
                if (wordToFind[i] == getWord[i]){
                    //le carré devient vert
                    edittext.setBackgroundResource(correct)
                } else if (wordToFind[i] != getWord[i]) {
                    for (j in 0 .. wordToFind.length -1){
                        if(wordToFind[i]==getWord[j]){
                            //Le carré devient rouge
                            //edittext.setBackgroundResource(wrong)
                        }
                    }
                }
            }
        }

        var chargeEditTextAnimation = fun(){
            v.listView_game[itemLigneSelected].editText_i_1.animate().apply {
                duration = 700
                rotationXBy(360f)
            }.withEndAction{
                changeEditTextColor(v.listView_game[itemLigneSelected].editText_i_1)
                v.listView_game[itemLigneSelected].editText_i_2.animate().apply {
                    duration = 700
                    rotationXBy(360f)
                }.withEndAction{
                    changeEditTextColor(v.listView_game[itemLigneSelected].editText_i_2)
                    v.listView_game[itemLigneSelected].editText_i_3.animate().apply {
                        duration = 700
                        rotationXBy(360f)
                    }.withEndAction {
                        changeEditTextColor(v.listView_game[itemLigneSelected].editText_i_3)
                        v.listView_game[itemLigneSelected].editText_i_4.animate().apply {
                            duration = 700
                            rotationXBy(360f)
                        }.withEndAction {
                            changeEditTextColor(v.listView_game[itemLigneSelected].editText_i_4)
                            v.listView_game[itemLigneSelected].editText_i_5.animate().apply {
                                duration = 700
                                rotationXBy(360f)
                            }.withEndAction{
                                changeEditTextColor(v.listView_game[itemLigneSelected].editText_i_5)
                                holderAndSaveText()
                            }
                        }
                    }
                }
            }
        }

        var compareWord = fun (){
             if (getWord != wordToFind) {
                 var result = true
                for (mot in 0 .. allWordToFind.size-1){
                    when(getWord == allWordToFind[mot]){
                        true -> {
                            result = true
                            break
                        }
                        false -> {
                            result = false
                        }
                    }
                }
                 if (result.not()){
                     v.listView_game[itemLigneSelected].editText_i_1.setText("")
                     v.listView_game[itemLigneSelected].editText_i_2.setText("")
                     v.listView_game[itemLigneSelected].editText_i_3.setText("")
                     v.listView_game[itemLigneSelected].editText_i_4.setText("")
                     v.listView_game[itemLigneSelected].editText_i_5.setText("")
                     Toast.makeText(v.context,"Veuillez saisir un mot correct",Toast.LENGTH_LONG).show()
                 } else {
                     chargeEditTextAnimation()
                 }
            } else {
                 //chargeEditTextAnimation()
                 //nouvelle fonction pour dire congrat
                 itemWordSelected++
            }
        }

        myAdapter = MyAdapter(v.context, R.layout.item_mot_magic, itemList_data)
        v.listView_game.adapter = myAdapter

        v.button_submit.setOnClickListener{
            getValueEditText()
            Log.i("getWord","$getWord")
            Log.i("wordToFind","$wordToFind")
            //Log.i("getWordTab","${getWordTab[1]}")
            //Log.i("wordToFindTab","${wordToFindTab[1]}")
            if (firstLettre.isEmpty() || secondLettre.isEmpty() || threeLettre.isEmpty() || fourLettre.isEmpty() || fiveLettre.isEmpty()) {
                Toast.makeText(v.context,"Veuillez proposer un mot",Toast.LENGTH_LONG).show()
            } else{
                compareWord()
            }
        }
        return v
    }
}