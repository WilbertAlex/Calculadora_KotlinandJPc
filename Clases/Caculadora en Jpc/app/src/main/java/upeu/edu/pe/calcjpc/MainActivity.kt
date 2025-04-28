package upeu.edu.pe.calcjpc

import kotlin.math.pow
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import upeu.edu.pe.calcjpc.ui.theme.CalcjpcTheme
import upeu.edu.pe.calcjpc.ui.theme.Purple200
import upeu.edu.pe.calcjpc.ui.theme.textColor


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalcUPeU()
            /*CalcjpcTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android Dpm",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }*/
        }

    }


}
fun isNumeric(toCheck: String): Boolean {
    val regex = "-?[0-9]+(\\.[0-9]+)?".toRegex()
    return toCheck.matches(regex)
}

//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorTextField(
    textState: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .background(Purple200)
            .wrapContentSize(Alignment.BottomEnd)
            .fillMaxSize()
    ) {
        TextField(
            value = textState,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f)
                .wrapContentSize(Alignment.BottomEnd)
                .fillMaxSize(),
            textStyle = TextStyle(fontSize = 36.sp, textAlign = TextAlign.End, color = textColor),
            maxLines = 2,
            readOnly = true
        )
    }
}

@Composable
fun CalculatorFirstRow(
    textState: String,
    isNewOp: Boolean,
    onValueChange: (String) -> Unit,
    onIsNewOpChange: (Boolean) -> Unit,
    onOpChange: (String) -> Unit,
    onOldValueChange: (String) -> Unit,
    modifier: Modifier,
    op:String,
    oldTextState: String,
    data: List<String>
) {
    Row (modifier = modifier.fillMaxSize()) {
        var listB = data
        listB.forEach {
            ButtonX(
                modifier = modifier,
                valuex = it,
                onValueChange = onValueChange,
                onIsNewOpChange = onIsNewOpChange,
                textState = textState, isNewOp = isNewOp,
                onOpChange = onOpChange,
                onOldValueChange = onOldValueChange,
                op = op,
                oldTextState = oldTextState
            )
        }
    }
}


@Composable()
fun ButtonX(
    modifier: Modifier,
    valuex: String,
    onValueChange: (String) -> Unit,
    onIsNewOpChange: (Boolean) -> Unit,
    textState: String,
    isNewOp : Boolean,
    onOpChange: (String) -> Unit,
    onOldValueChange: (String) -> Unit,
    oldTextState: String,
    op:String
) {
    Column(modifier = modifier.wrapContentSize(Alignment.Center)) {
        Box(
            modifier = modifier
                .weight(1f)
                .background(Color(0xFF00BCD4))
                .border(width = .5.dp, Color(0xFF2C2F32))
                .clickable (
                    enabled = true,
                    onClick = {
                        if (isNumeric(valuex)) {
                            var valor = textState
                            if (isNewOp) {
                                valor = ""
                                onValueChange.invoke(valor)
                            }
                            onIsNewOpChange.invoke(false)
                            valor += valuex
                            onValueChange.invoke(valor)
                        }
                        if (valuex.equals("+") || valuex.equals("-") || valuex.equals("*") || valuex.equals(
                                "/"
                            ) || valuex.equals("%")
                        ) {
                            onOpChange.invoke(valuex)
                            onOldValueChange.invoke(textState)
                            onIsNewOpChange.invoke(true)
                        }
                        if (valuex.equals("AC")) {
                            onValueChange.invoke("0")
                            onIsNewOpChange.invoke(true)
                        }
                        if (valuex.equals(".")) {
                            var dot = textState
                            if (isNewOp) {
                                dot = ""
                                onValueChange.invoke(dot)
                            }
                            onIsNewOpChange.invoke(false)
                            if (!dot.contains(".")) {
                                dot += "."
                                onValueChange.invoke(dot)
                            }
                        }
                        if (valuex.equals("=")) {
                            if (oldTextState.isNotEmpty() && textState.isNotEmpty()) {
                                try {
                                    val num1 = oldTextState.toDouble()
                                    val num2 = textState.toDouble()
                                    var finalNumber = 0.0
                                    when (op) {
                                        "*" -> finalNumber = num1 * num2
                                        "/" -> finalNumber = num1 / num2
                                        "+" -> finalNumber = num1 + num2
                                        "-" -> finalNumber = num1 - num2
                                        "^" -> finalNumber = num1.pow(num2)
                                    }
                                    onValueChange.invoke(finalNumber.toString())
                                    onIsNewOpChange.invoke(true)
                                } catch (e: NumberFormatException) {
                                    onValueChange.invoke("Error")
                                }
                            }
                        }

                        //Raiz cuadrada
                        if (valuex.equals("√", true)) {
                            val result = calculateSquareRoot(textState)
                            onValueChange.invoke(result)
                            onIsNewOpChange.invoke(true)
                        }
                        // 1/*
                        if (valuex.equals("1/x", true)) {
                            val result = calculateInverse(textState)
                            onValueChange.invoke(result)
                            onIsNewOpChange.invoke(true)
                        }
                        // Pi
                        if (valuex.equals("π", true)) {
                            onValueChange.invoke(kotlin.math.PI.toString())
                            onIsNewOpChange.invoke(true)
                        }
                        //Potencia
                        if (valuex.equals("^", true)) {
                            onOpChange.invoke(valuex)
                            onOldValueChange.invoke(textState)
                            onIsNewOpChange.invoke(true)
                        }

                    }
                )
        ) {
            Text(
                text = valuex,
                style = TextStyle(
                    fontSize = 24.sp,
                    textAlign = TextAlign.End,
                    color = textColor,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun CalcUPeU() {
    CalcjpcTheme() {
        Column {
            var op by remember { mutableStateOf("") }
            var isNewOp by remember { mutableStateOf(true) }
            var oldTextState: String by remember { mutableStateOf("") }
            var textState: String by remember { mutableStateOf("0") }
            CalculatorTextField(
                textState = textState,
                modifier = Modifier.height(100.dp),
                onValueChange = { textState = it }
            )
            Column(modifier = Modifier.fillMaxSize()) {
                // Boton de raiz
                var listA = listOf("AC", ".", "%", "/", "√")

                //Boton de potencia
                var listB = listOf("7", "8", "9", "*", "^")

                // Boton de (1/x)
                var listC = listOf("4", "5", "6", "+", "1/x")

                // botón de pi
                var listD = listOf("1", "2", "3", "-", "π")

                var listE = listOf("0", "=")
                var listaCompleta = listOf<List<String>>(listA, listB, listC, listD, listE)
                listaCompleta.forEach {
                    CalculatorFirstRow(
                        isNewOp = isNewOp,
                        textState = textState,
                        onValueChange = { textState = it },
                        onIsNewOpChange = { isNewOp = it },
                        onOpChange = { op = it },
                        onOldValueChange = { oldTextState = it },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        op = op,
                        oldTextState = oldTextState,
                        data = it
                    )
                }
            }
        }
    }
}

// Función para manejar la raíz cuadrada
fun calculateSquareRoot(value: String): String {
    return try {
        val num = value.toDouble()
        if (num >= 0) {
            kotlin.math.sqrt(num).toString()
        } else {
            "Error"
        }
    } catch (e: NumberFormatException) {
        "Error"
    }
}

fun calculateInverse(value: String): String {
    return try {
        val num = value.toDouble()
        if (num != 0.0) {
            (1 / num).toString()
        } else {
            "Error"
        }
    } catch (e: NumberFormatException) {
        "Error"
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalcjpcTheme {
        Greeting("Android")
    }
}