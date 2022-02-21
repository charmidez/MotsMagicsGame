package com.amango.motsmagics.fragments

import android.os.Bundle
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
    private lateinit var firstLettre : String
    private lateinit var secondLettre : String
    private lateinit var threeLettre : String
    private lateinit var fourLettre : String
    private lateinit var fiveLettre : String

    private lateinit var allWordToFind : ArrayList<String>
    private lateinit  var getWord : String
    private lateinit var wordToFind : String

    fun decomposeStep(word : String ) : ArrayList<Char>{
        var wordTab = ArrayList<Char>()
        for (i in 0..word.length  - 1 ) {
            wordTab.add(word[i])
        }
        return wordTab
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
            allWordToFind = DataWords().allWord
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

        var getWordTab = decomposeStep(getWord)
        var wordToFindTab = decomposeStep(wordToFind)

        var changeEditTextColor = fun( i : Int, edittext : EditText){
            // Wifi code canal box 9720372497
            for (i in 0.. wordToFindTab.size -1){
                if (wordToFindTab[i] == getWordTab[i]){
                    edittext.setBackgroundResource(correct)
                } else if (wordToFindTab[i] != getWordTab[i]) {
                    for (j in 0 .. wordToFindTab.size -1){
                        if(wordToFindTab[i]==getWordTab[j]){
                            edittext.setBackgroundResource(wrong)
                        }
                    }
                }
            }
            if (getWordTab[i] == wordToFindTab[i]){
                edittext.setBackgroundResource(correct)
            } else {
                edittext.setBackgroundResource(wrong)
            }
        }

        var chargeEditTextAnimation = fun(){
            v.listView_game[itemLigneSelected].editText_i_1.animate().apply {
                duration = 700
                rotationXBy(360f)
            }.withEndAction{
                changeEditTextColor(0,v.listView_game[itemLigneSelected].editText_i_1)
                v.listView_game[itemLigneSelected].editText_i_2.animate().apply {
                    duration = 700
                    rotationXBy(360f)
                }.withEndAction{
                    changeEditTextColor(0,v.listView_game[itemLigneSelected].editText_i_2)
                    v.listView_game[itemLigneSelected].editText_i_3.animate().apply {
                        duration = 700
                        rotationXBy(360f)
                    }.withEndAction {
                        changeEditTextColor(0,v.listView_game[itemLigneSelected].editText_i_3)
                        v.listView_game[itemLigneSelected].editText_i_4.animate().apply {
                            duration = 700
                            rotationXBy(360f)
                        }.withEndAction {
                            changeEditTextColor(0,v.listView_game[itemLigneSelected].editText_i_4)
                            v.listView_game[itemLigneSelected].editText_i_5.animate().apply {
                                duration = 700
                                rotationXBy(360f)
                            }.withEndAction{
                                changeEditTextColor(0,v.listView_game[itemLigneSelected].editText_i_5)
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
                 chargeEditTextAnimation()
                 //nouvelle fonction pour dire congrat
                 itemWordSelected++
            }
        }

        myAdapter = MyAdapter(v.context, R.layout.item_mot_magic, itemList_data)
        v.listView_game.adapter = myAdapter

        v.button_submit.setOnClickListener{
            getValueEditText()
            if (firstLettre.isEmpty() || secondLettre.isEmpty() || threeLettre.isEmpty() || fourLettre.isEmpty() || fiveLettre.isEmpty()) {
                Toast.makeText(v.context,"Veuillez proposer un mot",Toast.LENGTH_LONG).show()
            } else{
                compareWord()
            }
        }
        return v
    }
}