package pe.edu.upeu.proyecto_turismo.ui.presentation.screen.login

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.proyecto_turismo.modelo.UsuarioDto
import pe.edu.upeu.proyecto_turismo.ui.presentation.componentes.ErrorImageAuth
import pe.edu.upeu.proyecto_turismo.ui.presentation.componentes.ImageLogin
import pe.edu.upeu.proyecto_turismo.ui.presentation.componentes.ProgressBarLoading
import pe.edu.upeu.proyecto_turismo.ui.presentation.componentes.form.EmailTextField
import pe.edu.upeu.proyecto_turismo.ui.presentation.componentes.form.PasswordTextField


import pe.edu.upeu.proyecto_turismo.ui.utils.ComposeReal
import pe.edu.upeu.proyecto_turismo.ui.utils.TokenUtils
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.observeAsState(false)
    val isLogin by viewModel.islogin.observeAsState(false)
    val isError by viewModel.isError.observeAsState(false)
    val loginResul by viewModel.listUser.observeAsState()
    val errorMessage by viewModel.errorMessage.observeAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Espaciado superior para centrar la primera parte
        Spacer(modifier = Modifier.height(80.dp))

        // Título de Turismo Capachica
        Text(
            text = "Turismo Capachica",
            fontSize = 32.sp,
            color = Color.White
        )

        // Imagen de Login
        Spacer(modifier = Modifier.height(16.dp))
        ImageLogin()

        // Iniciar Sesión
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Iniciar Sesión",
            fontSize = 28.sp,
            color = Color.White
        )

        // Más espaciado para separar los campos
        Spacer(modifier = Modifier.height(40.dp))

        // Formulario de inicio de sesión
        BuildEasyForms { easyForm ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                EmailTextField(easyForms = easyForm, text ="","E-Mail:", "U")
                Spacer(modifier = Modifier.height(12.dp))
                PasswordTextField(easyForms = easyForm, text ="", label ="Password:" )
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        val dataForm = easyForm.formData()
                        val user = UsuarioDto(
                            (dataForm.get(0) as EasyFormsResult.StringResult).value,
                            (dataForm.get(1) as EasyFormsResult.StringResult).value
                        )
                        viewModel.loginSys(user)
                        scope.launch {
                            delay(3600)
                            if (isLogin && loginResul != null) {
                                Log.i("TOKENV", TokenUtils.TOKEN_CONTENT)
                                Toast.makeText(context, "Bienvenido ${TokenUtils.USER_LOGIN}", Toast.LENGTH_SHORT).show()
                                navigateToHome.invoke()
                            } else {
                                Log.v("ERRORX", "Error logeo")
                                Toast.makeText(context, "Error al conectar", Toast.LENGTH_LONG).show()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0288D1), // Azul más claro para botón
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(16.dp) // Bordes redondeados
                ) {
                    Text(text = "Log In", fontSize = 18.sp)
                }

                ComposeReal.COMPOSE_TOP.invoke()
            }
        }

        // Mostrar errores y loading
        ErrorImageAuth(isImageValidate = isError)
        ProgressBarLoading(isLoading = isLoading)
    }

    // Mostrar Snackbar manualmente
    SnackbarHost(
        hostState = snackbarHostState,
        modifier = Modifier.wrapContentHeight(Alignment.Bottom).padding(16.dp),
    )

    // Mostrar el snackbar cuando haya mensaje de error
    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearErrorMessage()
        }
    }
}