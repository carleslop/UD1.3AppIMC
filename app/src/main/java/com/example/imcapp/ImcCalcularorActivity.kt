package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat


class ImcCalcularorActivity : AppCompatActivity() {

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private var isMaleSelected: Boolean = true
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvWeight: TextView
    private lateinit var tvAge: TextView
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnAddWeight: FloatingActionButton
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnAddAge: FloatingActionButton
    private lateinit var btnCalcular: AppCompatButton
    private var currentAge = 26
    private var currentWeight = 60
    private var resultado: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calcularor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
        initUI()
    }

    private fun initUI() {
        setGenderColor()
        setWeight(null)
        setAge(null)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            isMaleSelected = true
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            isMaleSelected = false
            setGenderColor()
        }
        btnAddWeight.setOnClickListener {
            setWeight(true)
        }
        btnSubtractWeight.setOnClickListener {
            setWeight(false)
        }
        btnAddAge.setOnClickListener {
            setAge(true)
        }
        btnSubtractAge.setOnClickListener {
            setAge(false)
        }

        btnCalcular.setOnClickListener {
            calculateIMC()
            navigateToResult(resultado)
        }

        rsHeight.addOnChangeListener { _, value, _ ->
            //tvHeight.text = value.toString()
            tvHeight.text = DecimalFormat("#.##").format(value) + " cm"
        }
    }

    private fun navigateToResult(resultado: Double) {
        val intent = Intent(this, ImcResultActivity::class.java)
        intent.putExtra("resultadoIMC", resultado)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val alturaEnMetros = tvHeight.text.toString().replace(" cm", "").toDouble() / 100
        resultado = currentWeight / (alturaEnMetros * alturaEnMetros)
        return resultado
    }

    private fun setWeight(add: Boolean?) {
        if (add == true) {
            currentWeight++
        } else  if (add == false){
            if (currentWeight > 0) currentWeight--
        }
        tvWeight.text = "$currentWeight"
    }

    private fun setAge(add: Boolean?) {
        if (add == true) {
            currentAge++
        } else if (add == false){
            if (currentAge > 0) currentAge--
        }
        tvAge.text = "$currentAge"
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
    }

    private fun getBackgroundColor(isComponentSelected: Boolean): Int {
        val colorReference = if (isComponentSelected) {
            R.color.bg_comp_sel
        } else {
            R.color.bg_comp
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        tvAge = findViewById(R.id.tvAge)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnAddWeight = findViewById(R.id.btnAddWeight)
        tvAge = findViewById(R.id.tvAge)
        btnAddAge = findViewById(R.id.btnAddAge)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnCalcular = findViewById(R.id.btnCalcular)
    }


}