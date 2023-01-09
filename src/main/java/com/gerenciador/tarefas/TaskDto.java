package com.gerenciador.tarefas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class TaskDto {
    Integer id;
    String titulo;
    String descricao;
    String prazo;
    String idDepartamento;
    String duracao;
    String idPessoa;
    Boolean finalizado;
}
