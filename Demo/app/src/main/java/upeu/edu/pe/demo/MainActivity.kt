package upeu.edu.pe.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var piTargetFirstField = true
    lateinit var txtVal1:EditText
    lateinit var txtVal2:EditText
    lateinit var resultadoTV:TextView
    var resultado:String=""



    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setContentView(R.layout.calcu)

        txtVal1=findViewById(R.id.txtnumb1)
        txtVal2=findViewById(R.id.txtnumb2)
        resultadoTV=findViewById(R.id.tvResultado)

        var buttons= arrayOf(R.id.btnSumar, R.id.btnRestar, R.id.btnDividir, R.id.btnMultiplicar, R.id.btnRaiz, R.id.btnPi, R.id.btnUnoSobreX, R.id.btnPotencia)


        for (b in buttons){
            var button=findViewById<Button>(b)
            button.setOnClickListener { operar(button) }
        }
    }
    fun operar(view: View){
        when(view.id){
            R.id.btnSumar->{
                resultado= (txtVal1.text.toString().toInt()+txtVal2.text.toString().toInt()).toString()
                resultadoTV.text=resultado
            }
            R.id.btnRestar->{
                resultado= (txtVal1.text.toString().toInt()-txtVal2.text.toString().toInt()).toString()
                resultadoTV.text=resultado
            }
            R.id.btnMultiplicar->{
                resultado= (txtVal1.text.toString().toInt()*txtVal2.text.toString().toInt()).toString()
                resultadoTV.text=resultado
            }
            R.id.btnDividir->{
                resultado= (txtVal1.text.toString().toInt()/txtVal2.text.toString().toInt()).toString()
                resultadoTV.text=resultado
            }
            R.id.btnRaiz->{
                resultado = Math.sqrt(txtVal1.text.toString().toDouble()).toString()
                resultadoTV.text = resultado
            }
            R.id.btnPotencia->{
                val base = txtVal1.text.toString().toInt()
                val exponente = txtVal2.text.toString().toInt()
                // Calculate power
                resultado = Math.pow(base.toDouble(), exponente.toDouble()).toInt().toString()
                resultadoTV.text = resultado
            }
            R.id.btnUnoSobreX->{
                val numero = txtVal1.text.toString().toDouble()
                // Calculate 1 divided by the number
                if (numero != 0.0) {
                    resultado = (1.0 / numero).toString()
                    resultadoTV.text = resultado
                } else {
                    resultadoTV.text = "Error: División por cero"
                }
            }
            R.id.btnPi->{
                if (piTargetFirstField) {
                    txtVal1.setText(Math.PI.toString())
                    resultadoTV.text = Math.PI.toString()
                } else {
                    txtVal2.setText(Math.PI.toString())
                    resultadoTV.text = Math.PI.toString()
                }
                piTargetFirstField = !piTargetFirstField
            }


        }


        }}
