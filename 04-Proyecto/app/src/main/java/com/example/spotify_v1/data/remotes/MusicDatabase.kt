package com.example.spotify_v1.data.remotes

import com.example.spotify_v1.data.entities.Song
import com.example.spotify_v1.data.other.Constants.SONG_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class MusicDatabase {

    private val firestore = FirebaseFirestore.getInstance()
    private val songColletion = firestore.collection(SONG_COLLECTION)

    suspend fun getAllSongs(): List<Song> {
        return try {
            songColletion.get().await().toObjects(Song::class.java)
        } catch (e: Exception){
            emptyList()
        }
    }

}