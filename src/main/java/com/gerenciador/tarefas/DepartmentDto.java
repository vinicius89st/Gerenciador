package com.gerenciador.tarefas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Builder
@Data
public class DepartmentDto {
    String titulo;
    String numPessoas;
    String numTarefas;
}
