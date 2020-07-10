package com.cursoandroid.oliveiragabriel.gerenciadordetarefas.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.R
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.bancodedados.TarefaDAO
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.model.TarefaModel
import kotlinx.android.synthetic.main.activity_adicionar_tarefas.*

class AdicionarTarefasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_tarefas)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        //bloco para recuperar tarefa enviada da MainActivity
        val tarefaModel = TarefaModel()
        tarefaModel.tarefaModel =
            (intent.getSerializableExtra("editar_tarefa") as TarefaModel?)?.tarefaModel
        tarefaModel.id = (intent.getSerializableExtra("editar_tarefa") as TarefaModel?)?.id

        //Alterar o nome da toolbar
        if (tarefaModel.tarefaModel != null) {
            txt_text_layout.hint = getString(R.string.editar_tarefa)
            supportActionBar?.title = getString(R.string.editar_tarefa)
            txt_input_tarefas.setText(tarefaModel.tarefaModel)
        } else {
            txt_text_layout.hint = getString(R.string.adicionar_uma_nova_tarefa)
            supportActionBar?.title = getString(R.string.adicionar_uma_nova_tarefa)
        }

        floatingActionButtonCheck.setOnClickListener {
            if (tarefaModel.tarefaModel != null) {
                editarTarefa(tarefaModel)
            } else {
                inserirTarefa()
            }
        }


    }


    fun inserirTarefa() {

        if (validarCampo()) {

            val tarefaModel = TarefaModel()
            tarefaModel.tarefaModel = txt_input_tarefas.text.toString()
            val tarefaDAO = TarefaDAO(applicationContext)
            tarefaDAO.inserir(tarefaModel)
            Toast.makeText(applicationContext, "Tarefa Adicionada", Toast.LENGTH_LONG).show()
            finish()
        } else {
            validarCampo()
        }
    }

    fun validarCampo(): Boolean {
        var check = true

        if (txt_input_tarefas.text.toString().isEmpty()) {
            txt_text_layout.isErrorEnabled = true
            txt_text_layout.error = "Digite uma tarefa"
            check = false
        } else {
            txt_text_layout.isErrorEnabled = false
        }


        return check
    }

    fun editarTarefa(tarefaModel: TarefaModel) {

        if (validarCampo()) {
            val tarefaDAO = TarefaDAO(applicationContext)
            tarefaModel.tarefaModel = txt_input_tarefas.text.toString()
            tarefaDAO.alterar(tarefaModel)
            Toast.makeText(applicationContext, "Tarefa Alterada", Toast.LENGTH_LONG).show()
            finish()
        } else {
            validarCampo()
        }
    }
}




