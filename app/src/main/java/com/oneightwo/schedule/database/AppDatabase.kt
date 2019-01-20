package com.oneightwo.schedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.database.cabinet.CabinetDao
import com.oneightwo.schedule.database.subject.Subject
import com.oneightwo.schedule.database.subject.SubjectDao
import com.oneightwo.schedule.database.teacher.Teacher
import com.oneightwo.schedule.database.teacher.TeacherDao
import com.oneightwo.schedule.database.time.Time
import com.oneightwo.schedule.database.time.TimeDao

@Database(
    entities = [Subject::class, Time::class, Teacher::class, Cabinet::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun subjectDao(): SubjectDao
    abstract fun timeDao(): TimeDao
    abstract fun teacherDao(): TeacherDao
    abstract fun cabinetDao(): CabinetDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            "data.db"
                        ).allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return instance!!
        }
    }
}