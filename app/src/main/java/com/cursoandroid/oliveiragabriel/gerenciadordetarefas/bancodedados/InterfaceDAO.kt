package com.cursoandroid.oliveiragabriel.gerenciadordetarefas.bancodedados

import com.cursoandroid.oliveiragabriel.gerenciadordetarefas.model.TarefaModel

interface InterfaceDAO {

    fun inserir(tarefa: TarefaModel): Boolean

    fun deletar(tarefa: TarefaModel): Boolean

    fun alterar(tarefa: TarefaModel): Boolean

   fun consultar(): MutableList<TarefaModel>
}