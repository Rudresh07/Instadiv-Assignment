package com.rudy.instadiv.viewmodel

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class TagRepository {
    private val database = FirebaseDatabase.getInstance().getReference("tags")

    suspend fun fetchTags(): List<String> {
        return try {
            // Fetch data from the "tags" node in the Realtime Database
            val snapshot = database.get().await()
            val tags = snapshot.children.map { it.getValue(String::class.java) ?: "" }

            // Log the fetched tags
            Log.d("TagRepository", "Fetched tags: $tags")

            tags
        } catch (e: Exception) {
            Log.e("TagRepository", "Error fetching tags", e)
            emptyList()
        }
    }
}

