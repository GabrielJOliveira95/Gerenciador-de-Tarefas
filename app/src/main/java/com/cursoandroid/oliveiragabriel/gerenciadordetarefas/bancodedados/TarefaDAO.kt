package com.cursoandroid.oliveiragabriel.gerenciadordetarefas.bancodedados

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.bancodedados.DBHelper.Companion.TABELA_TAREFA
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.model.TarefaModel

class TarefaDAO(context: Context) : InterfaceDAO {


    var dbHelper = DBHelper(context)
    val escritaDB = dbHelper.writableDatabase
    val leituraDB = dbHelper.readableDatabase
    val contetValue = ContentValues()


    override fun inserir(tarefa: TarefaModel): Boolean {

        contetValue.put("tarefas", tarefa.tarefaModel)
        try {
            escritaDB.insert(TABELA_TAREFA, null, contetValue)


            Log.i("Sucesso", "Sucesso ao inserir dados na tabela")
            val ccc: Cursor =
                dbHelper.readableDatabase.rawQuery("SELECT * FROM $TABELA_TAREFA", null)
            val id = ccc.getColumnIndex("id")
            val nome = ccc.getColumnIndex("tarefas")
            ccc.moveToFirst()


        } catch (e: Exception) {
            Log.i("Erro", "Erro ao inserir dados na tabela" + e.message)
            return false
        }
        return true
    }

    override fun deletar(tarefa: TarefaModel): Boolean {
        return true
    }

    override fun alterar(tarefa: TarefaModel): Boolean {
        return true
    }

    override fun consultar(): MutableList<TarefaModel> {
        val mutableListTarefas: MutableList<TarefaModel> = mutableListOf()

        try {
            val sql = "SELECT * FROM $TABELA_TAREFA"
            val cursor: Cursor
            cursor = leituraDB.rawQuery(sql, null)
            val id = cursor.getColumnIndex("id")
            val nome = cursor.getColumnIndex("tarefas")
            cursor.moveToFirst()

            while (true) {
                Log.i("TESTE", "ID " + cursor.getString(id) + " NOME ${cursor.getString(nome)}")
                val model = TarefaModel()

                model.id = cursor.getInt(id)
                model.tarefaModel = cursor.getString(nome)
                mutableListTarefas.add(model)
                cursor.moveToNext()
            }

        } catch (e: Exception) {
            Log.i("Erro ao consultar", "Erro " + e.printStackTrace())
        }

        return mutableListTarefas
    }


}