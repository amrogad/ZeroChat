import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viewmodel.AuthViewModel
import viewmodel.ChatViewModel


@Composable
fun ChatScreen() {
    val authViewModel = AuthViewModel()
    val chatViewModel = ChatViewModel()
    val messages by chatViewModel.messages.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(messages) { message ->
                Text(text = "${message.userId}: ${message.message}")
            }
        }
        var input by remember { mutableStateOf("") }
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Message") }
        )
        Button(
            onClick = {
                chatViewModel.sendMessage(input, authViewModel.auth.currentUser?.uid ?: "")
                input = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send")
        }
    }
}
