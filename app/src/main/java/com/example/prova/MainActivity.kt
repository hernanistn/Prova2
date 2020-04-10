package com.example.prova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.w3c.dom.Text
import java.lang.Exception
import java.math.BigDecimal
import java.math.BigInteger

class MainActivity : AppCompatActivity() {

    private lateinit var seekBar : SeekBar
    private lateinit var edtValor : EditText
    private lateinit var txtValorSeekBar : TextView
    private lateinit var btnValorJurosPadrao : Button
    private lateinit var btnValorTotalPadrao : Button
    private lateinit var btnValorTotalSK : Button
    private lateinit var btnValorJurosSK : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar = findViewById<SeekBar>(R.id.seekBar)
        txtValorSeekBar = findViewById<TextView>(R.id.txtValorSeekBar)


        edtValor = findViewById(R.id.edtValor)
        btnValorJurosPadrao = findViewById<Button>(R.id.btnValorJurosPadrao)
        btnValorTotalPadrao = findViewById<Button>(R.id.btnValorTotalPadrao)
        btnValorJurosSK = findViewById<Button>(R.id.btnValorJurosSK)
        btnValorTotalSK = findViewById<Button>(R.id.btnValorTotalSK)
        txtValorSeekBar.text = seekBar.progress.toString()
        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //ao mudar valor do seekbar, atualizar valor do juros
                txtValorSeekBar.text = ""+progress+"%"
                calcularValores()

            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        txtValorSeekBar.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //ao alterar valor, atualizar valor do juros
                calcularValores()

            }

        })







    }
    fun calcularValores() {
        try {
            btnValorJurosPadrao.text = "%.4s".format("${(edtValor.text.toString().toFloat() * 15) / 100}")
            btnValorTotalPadrao.text = "%.5s".format("${btnValorJurosPadrao.text.toString().toFloat() + edtValor.text.toString().toFloat()}")
            btnValorJurosSK.text = "%.4s".format("${(edtValor.text.toString().toFloat() * seekBar.progress.toString().toFloat()) / 100}")
            btnValorTotalSK.text = "%.5s".format("${btnValorJurosSK.text.toString().toFloat() + edtValor.text.toString().toFloat()}")
        } catch (e: Exception) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
        }
    }

}






