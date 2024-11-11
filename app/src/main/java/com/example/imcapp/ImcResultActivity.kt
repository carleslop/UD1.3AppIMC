package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class ImcResultActivity : AppCompatActivity() {
    private lateinit var tv_category: TextView
    private lateinit var tvNumber: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnCalcular: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.imc_result)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
        val resultadoIMC: Double = intent.extras!!.getDouble("resultadoIMC")
        tvNumber.text = DecimalFormat("#.##").format(resultadoIMC)
        compCorporal(resultadoIMC)
    }

    private fun compCorporal(resultadoIMC: Double) {
        if (resultadoIMC < 18.5) {
            tv_category.text = getString(R.string.tituloInferior)
            tvDescription.text = getString(R.string.textoInferior)
        } else if (resultadoIMC >= 18.5 && 24.9 > resultadoIMC) {
            tv_category.text = getString(R.string.tituloNormal)
            tvDescription.text = getString(R.string.textoNormal)
        } else if (resultadoIMC >= 24.9 && 29.9 > resultadoIMC) {
            tv_category.text = getString(R.string.tituloSuperior)
            tvDescription.text = getString(R.string.textoSuperior)
        } else {
            tv_category.text = getString(R.string.tituloObesidad)
            tvDescription.text = getString(R.string.textoObesidad)
        }
    }


    private fun initListeners() {
        btnCalcular.setOnClickListener {
            val intent = Intent(this, ImcCalcularorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initComponents() {
        tv_category = findViewById(R.id.tv_category)
        tvNumber = findViewById(R.id.tvNumber)
        tvDescription = findViewById(R.id.tvDescription)
        btnCalcular = findViewById(R.id.btnCalcular)
    }
}