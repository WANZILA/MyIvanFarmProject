package ug.ac.myivanfarm.data.room

import android.content.Context
import androidx.room.Room

class DBbuilder {
    fun initializeDB(context: Context): AppDatabase{
        val builder = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ivanfarm"
        )
            .fallbackToDestructiveMigration()
        return builder.build()
    }
}