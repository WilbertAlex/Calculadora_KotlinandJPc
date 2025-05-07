package pe.edu.upeu.sysventasjpc.ui.presentation.screens.marca


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.gson.Gson
import pe.edu.upeu.sysventasjpc.modelo.Marca
import pe.edu.upeu.sysventasjpc.ui.navigation.Destinations
import pe.edu.upeu.sysventasjpc.ui.presentation.components.Spacer
import pe.edu.upeu.sysventasjpc.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.sysventasjpc.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.sysventasjpc.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.sysventasjpc.ui.presentation.components.form.NameTextField
@Composable
fun MarcaForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: MarcaFormViewModel= hiltViewModel()
) {
    val marca by viewModel.marca.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    var marcaD: Marca
    if (text!="0"){
        marcaD = Gson().fromJson(text, Marca::class.java)
        LaunchedEffect(Unit) {
            viewModel.getMarca(marcaD.idMarca)
        }
        marca?.let {
            marcaD=it
            Log.i("DMPX","Producto: ${marcaD.toString()}")
        }
    }else{
        marcaD= Marca(0,"")
    }
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment =
            Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    formulario(
        marcaD.idMarca!!,
        darkMode,
        navController,
        marcaD,
        viewModel
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter",
    "MissingPermission",
    "CoroutineCreationDuringComposition"
)
@Composable
fun formulario(id:Long,
               darkMode: MutableState<Boolean>,
               navController: NavHostController,
               marca: Marca,
               viewModel: MarcaFormViewModel
){
    val marcx= Marca(0,"")
    Scaffold(modifier = Modifier.padding(top = 80.dp, start = 16.dp, end
    = 16.dp, bottom =
        32.dp)){
        BuildEasyForms { easyForm ->
            Column(modifier =
                Modifier.verticalScroll(rememberScrollState())) {
                NameTextField(easyForms = easyForm,
                    text=marca?.nombre!!,"Nomb. Producto:", MyFormKeys.NAME )
                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar",
                        id){val lista=easyForm.formData()
                        marcx.nombre=(lista.get(0) as
                                EasyFormsResult.StringResult).value
                        if (id==0.toLong()){
                            viewModel.addMarca(marcx)
                        }else{
                            marcx.idMarca=id
                            viewModel.editMarca(marcx)
                        }

                        navController.navigate(Destinations.MarcaMainSC.route)
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){

                        navController.navigate(Destinations.MarcaMainSC.route)
                    }
                }
            }
        }
    }
}