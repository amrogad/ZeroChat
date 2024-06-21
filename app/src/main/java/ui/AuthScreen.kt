package ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viewmodel.AuthViewModel


@Composable
fun AuthScreen(onAuthSuccess: () -> Unit) {
    val authViewModel: AuthViewModel = AuthViewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSignUp by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }

    if (authViewModel.isLoggedIn()) {
        onAuthSuccess()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (isSignUp) {
                    authViewModel.signUp(email, password) { success ->
                        if (success) {
                            onAuthSuccess()
                        } else {
                            isError = true
                        }
                    }
                } else {
                    authViewModel.signIn(email, password) { success ->
                        if (success) {
                            onAuthSuccess()
                        } else {
                            isError = true
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isSignUp) "Sign Up" else "Sign In")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { isSignUp = !isSignUp }) {
            Text(if (isSignUp) "Already have an account? Sign In" else "Don't have an account? Sign Up")
        }
        if (isError) {
            Text("Authentication failed", color = MaterialTheme.colorScheme.error)
        }
    }
}
