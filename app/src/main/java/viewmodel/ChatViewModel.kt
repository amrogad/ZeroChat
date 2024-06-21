package viewmodel
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import model.ChatMessage

class ChatViewModel : ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    init {
        fetchMessages()
    }

    private fun fetchMessages() {
        firestore.collection("messages")
            .orderBy("timestamp")
            .addSnapshotListener { snapshot, _ ->
                snapshot?.let {
                    val msgs = it.toObjects(ChatMessage::class.java)
                    _messages.value = msgs
                }
            }
    }

    fun sendMessage(message: String, userId: String) {
        val newMessage = ChatMessage(
            userId = userId,
            message = message,
            timestamp = System.currentTimeMillis()
        )
        firestore.collection("messages").add(newMessage)
    }
}
