package com.gerenciador.pessoas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class PeopleDto {
    Integer id;
    String nome;
    String idDepartamento;
    String departamento;
    Integer duracao;
}

