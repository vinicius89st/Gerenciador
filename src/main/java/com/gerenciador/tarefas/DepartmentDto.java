package com.gerenciador.tarefas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class DepartmentDto {
    String titulo;
    String numPessoas;
    String numTarefas;
}
