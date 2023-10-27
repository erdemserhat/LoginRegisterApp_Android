package com.erdemserhat.loginregisterapp_android

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class LocalDatabase(val context: Context) {
    private val DATABASE_NAME:String ="ApplicationDatabase"
    private val TABLE_NAME:String="user_table"
    private val COLUMN_USERNAME:String="username"
    private val COLUMN_EMAIL:String ="email"
    private val COLUMN_PASSWORD:String="password"
    lateinit var database: SQLiteDatabase




    fun getLocalDatabase(context:Context){
        database= context.openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null)
        val createTableQuery="CREATE TABLE IF NOT EXISTS $TABLE_NAME (username VARCHAR,email VARCHAR,password VARCHAR)"
        database.execSQL(createTableQuery)
    }

    fun insertUser(user:User){
        getLocalDatabase(context)
        val(username,email,password)=user
        database.execSQL("INSERT INTO $TABLE_NAME (username,email,password) VALUES ('$username','$email','$password') ")
        database.close()
    }

    fun getUsers():List<User>{
        getLocalDatabase(context)
        var user_list:MutableList<User> = mutableListOf()
        val cursor: Cursor =database.rawQuery("SELECT * FROM $TABLE_NAME",null)
        val usernameIx = cursor.getColumnIndex(COLUMN_USERNAME)
        val emailIx = cursor.getColumnIndex(COLUMN_EMAIL)
        val password = cursor.getColumnIndex(COLUMN_PASSWORD)
        while(cursor.moveToNext()){
            user_list.add(User(cursor.getString(usernameIx),cursor.getString(emailIx),cursor.getString(password)))

        }
        return user_list

    }

    fun login(user:User):MutableList<Object>{
        val (username, password) = user
        val answerList:MutableList<Object> = mutableListOf()
        val selectionArgs = arrayOf(username, password)
        getLocalDatabase(context)
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_USERNAME=? AND $COLUMN_PASSWORD=?" // WHERE koşulu doğru biçimde düzenlendi
        val cursor: Cursor = database.rawQuery(query, selectionArgs)
        val isUserValid = cursor.moveToNext()





    }






}