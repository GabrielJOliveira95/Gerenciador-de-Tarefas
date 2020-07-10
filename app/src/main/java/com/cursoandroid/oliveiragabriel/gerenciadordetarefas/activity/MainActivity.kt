package com.cursoandroid.oliveiragabriel.gerenciadordetarefas.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.R
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.adapter.OnClickDeletarTarefas
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.adapter.TarefasAdapter
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.bancodedados.TarefaDAO
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.model.TarefaModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {
    var mutableListTarefas = mutableListOf<TarefaModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val intent = Intent(applicationContext, AdicionarTarefasActivity::class.java)
            startActivity(intent)

        }
        createRecyclerView()


    }

    fun createRecyclerView() {
        val tarefaDAO = TarefaDAO(applicationContext)
        mutableListTarefas = tarefaDAO.consultar()
        val adapter = TarefasAdapter(mutableListTarefas)

        recyclerview.adapter = adapter
        adapter.setOnclickDeletar(object : OnClickDeletarTarefas {
            var tarefaModel = TarefaModel()


            override fun onClickEditar(position: Int) {
                Toast.makeText(applicationContext, "Editar", Toast.LENGTH_SHORT).show()

                tarefaModel.tarefaModel = mutableListTarefas[position].tarefaModel
                tarefaModel.id = mutableListTarefas[position].id

                val intent = Intent(applicationContext, AdicionarTarefasActivity::class.java)
                intent.putExtra("editar_tarefa", tarefaModel)
                startActivity(intent)

            }

            override fun onClickDelete(position: Int) {

                val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)

                alertDialog.setTitle("Excluir Tarefa")
                alertDialog.setMessage("Tem certeza de que deseja excluir a tarefa ${mutableListTarefas[position].tarefaModel}")

                alertDialog.setPositiveButton("Sim") { _: DialogInterface, _: Int ->
                    tarefaDAO.deletar(mutableListTarefas[position])
                    createRecyclerView()
                }
                alertDialog.setNegativeButton("Não", null)

                alertDialog.create()
                alertDialog.show()


            }
        })

        recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        recyclerview.setHasFixedSize(true)

    }

    override fun onStart() {
        super.onStart()
        createRecyclerView()
    }

}
