package com.cursoandroid.oliveiragabriel.gerenciadordetarefas.bancodedados

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(
    context: Context?,
    NOME_DB: String = "BANCO_DE_DADOS",
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = 1
) : SQLiteOpenHelper(context, NOME_DB, factory, version) {


    companion object {
        val TABELA_TAREFA = "tabela_tarefas"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        try {

            val tabelaSql =
                "CREATE TABLE IF NOT EXISTS $TABELA_TAREFA(id INTEGER PRIMARY KEY AUTOINCREMENT, tarefas TEXT NOT NULL)"
            db?.execSQL(tabelaSql)
            Log.i("Sucesso", "Sucesso AO CRIAR TABELA")


        } catch (e: Exception) {
            Log.i("ERRO", "ERRO AO CRIAR TABELA " + e.message)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }


}
