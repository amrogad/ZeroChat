package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
     val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signIn(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    onResult(task.isSuccessful)
                }
        }
    }

    fun signUp(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    onResult(task.isSuccessful)
                }
        }
    }

    fun signOut() {
        auth.signOut()
    }

    fun isLoggedIn() = auth.currentUser != null
}
