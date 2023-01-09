package com.gerenciador.pessoas;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j
@Repository
public class PeopleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean insertPeople(PeopleDto peopleDto) {
        String sql = "INSERT INTO pessoas "
                + "(nome, id_departamento) VALUES "
                + "('" +  peopleDto.nome + "', " + peopleDto.idDepartamento + ")";
        try {
            jdbcTemplate.execute(sql);
            log.debug("[INSERT] Inserido na tabela pessoas com sucesso!");
            return true;
        }catch (Exception e){
            log.debug("[INSERT ERROR] Erro ao inserir na tabela pessoas: " + System.lineSeparator() + e.getStackTrace());
            return false;
        }
    }

    public boolean updatePeople(String id, PeopleDto peopleDto) {
        String sql = "UPDATE pessoas SET nome = '" + peopleDto.nome + "', id_departamento = " + peopleDto.idDepartamento
                + " WHERE id = " + id;
        try {
            jdbcTemplate.execute(sql);
            log.debug("[UPDATE] O id foi atualizado na tabela pessoas com sucesso! " + "[id:" + id + "]");
            return true;
        }catch (Exception e){
            log.debug("[UPDATE ERROR] Erro ao atualizar a tabela pessoas: " + System.lineSeparator() + e.getStackTrace());
            return false;
        }
    }

    public boolean deletePeople(String id) {
        String sql = "DELETE FROM pessoas WHERE id = " + id;
        try {
            jdbcTemplate.execute(sql);
            log.debug("[DELETE] O id foi deletedo na tabela pessoas com sucesso! " + "[id:" + id + "]");
            return true;
        }catch (Exception e){
            log.debug("[UPDATE ERROR] Erro ao deletar da tabela pessoas: " + System.lineSeparator() + e.getStackTrace());
            return false;
        }
    }

    public List<PeopleDto> listByNameDepartmentAndDuration() {
        String sql = "SELECT nome, departamento.titulo as departamento, sum(duracao) as duracao" +
                " FROM tarefas, pessoas, departamento" +
                " WHERE pessoas.id = tarefas.id_pessoa" +
                " AND tarefas.id_departamento = departamento.id" +
                " group by 1, 2 ORDER BY 3 DESC";
        List<PeopleDto> lista = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PeopleDto.class));
        return lista;
    }
}
