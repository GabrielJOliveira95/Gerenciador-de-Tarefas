package com.cursoandroid.oliveiragabriel.gerenciadordetarefas.activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.R
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.adapter.OnClickDeletarTarefas
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.adapter.TarefasAdapter
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.bancodedados.DBHelper
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.bancodedados.TarefaDAO
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.model.TarefaModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

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
        var mutableListTarefas: MutableList<TarefaModel> = mutableListOf()
        val tarefaDAO = TarefaDAO(applicationContext)
        mutableListTarefas = tarefaDAO.consultar()
        val adapter = TarefasAdapter(mutableListTarefas)

        recyclerview.adapter = adapter
        recyclerview.addItemDecoration(DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL))
        adapter.setOnclickDeletar(object : OnClickDeletarTarefas {
            override fun onClickEditar(position: Int) {
                Toast.makeText(applicationContext, "Editar", Toast.LENGTH_SHORT).show()

            }

            override fun onClickDelete(position: Int) {
                Toast.makeText(applicationContext, "Deletar", Toast.LENGTH_SHORT).show()

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
