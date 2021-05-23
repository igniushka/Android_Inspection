package db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import db.entity.*

private const val DB_NAME = "inspection_db"

@Database(
    entities = [Answer::class, AnswerData::class, Inspection::class, InspectionData::class, Question::class, QuestionData::class, QuestionInspectionDataRelationship::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getDAO(): DAO
}

object DatabaseManager {
    private var database: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, DB_NAME
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            database as AppDatabase
        } else {
            database as AppDatabase
        }
    }


}
