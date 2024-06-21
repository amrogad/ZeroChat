package model

data class ChatMessage(
    val id: String = "",
    val userId: String = "",
    val message: String = "",
    val timestamp: Long = 0L
)
