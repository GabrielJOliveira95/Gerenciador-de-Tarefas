package com.cursoandroid.oliveiragabriel.gerenciadordetarefas.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.R
import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.model.TarefaModel

class TarefasAdapter(var list: MutableList<TarefaModel>? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val listaDeTarefas = arrayOf("Teste 1 ", "Teste 2", "Teste 3", "Teste 4")
    var onClickListener:OnClickDeletarTarefas? = null

    fun setOnclickDeletar(onClick: OnClickDeletarTarefas?){
        this.onClickListener = onClick
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerviewlayout, parent, false)
        return TarefasViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TarefasViewHolder){
            val model = TarefaModel()
            model.tarefaModel = list?.get(position)?.tarefaModel

           holder.txt_tarefa.text = model.tarefaModel
        }
    }


}

class TarefasViewHolder(itemView: View, click: OnClickDeletarTarefas?) : RecyclerView.ViewHolder(itemView){
    var txt_tarefa = itemView.findViewById<TextView>(R.id.txt_tarefa)
    var icone_detelar = itemView.findViewById<ImageView>(R.id.icone_detelar)
    var icone_editar = itemView.findViewById<ImageView>(R.id.icone_editar)
    init {
        icone_detelar.setOnClickListener {
            if (click != null){
                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION){
                    click.onClickDelete(position)
                }
            }
        }

        icone_editar.setOnClickListener {

            if(click != null){
                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION){
                    click.onClickEditar(position)
                }
            }
        }

    }



}

interface OnClickDeletarTarefas{

    fun onClickDelete(position: Int)

    fun onClickEditar(position: Int)
}




