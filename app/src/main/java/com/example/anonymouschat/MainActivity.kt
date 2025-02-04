package com.example.anonymouschat

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.anonymouschat.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val firestore = FirebaseFirestore.getInstance()
    private val messagesCollection = firestore.collection("messages")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ View Binding 적용
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ UI 요소 바인딩
        val messageEditText = binding.messageEditText
        val sendButton = binding.sendButton
        val messageListView = binding.messageListView

        sendButton.setOnClickListener {
            sendMessage(messageEditText.text.toString())
        }

        loadMessages()
    }

    private fun sendMessage(message: String) {
        if (message.isNotEmpty()) {
            val messageData = hashMapOf(
                "message" to message,
                "timestamp" to System.currentTimeMillis()
            )
            messagesCollection.add(messageData)
        }
    }

    private fun loadMessages() {
        messagesCollection
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    e.printStackTrace()
                    return@addSnapshotListener
                }

                val messages = snapshots?.documents?.map { it.getString("message") ?: "" } ?: listOf()
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, messages)
                binding.messageListView.adapter = adapter
            }
    }
}
