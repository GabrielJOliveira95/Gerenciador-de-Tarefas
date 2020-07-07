package com.cursoandroid.oliveiragabriel.gerenciadordetarefas.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.R
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.bancodedados.DBHelper
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.bancodedados.TarefaDAO
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.model.TarefaModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_adicionar_tarefas.*

class AdicionarTarefasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_tarefas)

        supportActionBar?.title = getString(R.string.adcionar_tarefa)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        floatingActionButtonCheck.setOnClickListener {
            validarCampo(it)
        }


    }

    fun validarCampo(view: View) {

        if (txt_input_tarefas.text.toString().isEmpty()) {
            txt_text_layout.isErrorEnabled = true
            txt_text_layout.error = "Digite uma tarefa"
        } else {
            txt_text_layout.isErrorEnabled = false
            val tarefaModel = TarefaModel()
            tarefaModel.tarefaModel = txt_input_tarefas.text.toString()
            val tarefaDAO = TarefaDAO(applicationContext)
            tarefaDAO.inserir(tarefaModel)

            val ta = DBHelper(applicationContext)
            ta.readableDatabase.rawQuery("SELECT * FROM ${DBHelper.TABELA_TAREFA}", null)

            Snackbar.make(view, "TAREFA SALVA", Snackbar.LENGTH_LONG).show()

            finish()
        }

    }


}

