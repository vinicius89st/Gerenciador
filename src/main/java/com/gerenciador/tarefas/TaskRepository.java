package com.gerenciador.tarefas;

import com.gerenciador.pessoas.PeopleDto;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j
@Repository
public class TaskRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public boolean insertTask(TaskDto taskDto) {

        String sql = "INSERT INTO tarefas " +
                "(titulo, descricao, prazo, id_departamento, duracao, id_pessoa, finalizado) " +
                "VALUES ('"+taskDto.getTitulo()+"', '"
                +taskDto.getDescricao()+"', '"
                +taskDto.getPrazo()+"', "
                +taskDto.getIdDepartamento()+", "
                +taskDto.getDuracao()+", "
                +taskDto.getIdPessoa()+", "
                +taskDto.getFinalizado()+")";
        try {
            jdbcTemplate.execute(sql);
            log.debug("[INSERT] A Tarefa foi atualizado na tabela tarefas com sucesso! " + "[id:" + taskDto.getId() + "]");
            return true;
        }catch (Exception e){
            log.debug("[INSERT ERROR] Erro ao INSERIR na tabela tarefas: " + System.lineSeparator() + e.getStackTrace());
            return false;
        }
    }

    /*
    * Aloca uma pessoa aleatória (com o mesmo id_departamento da tarefa)
    * a tarefa que foi passada o id
    * */
    public boolean allocateTask(String id) {
        String sqlListOfPerson = "SELECT DISTINCT pessoas.id FROM tarefas, pessoas " +
                "WHERE pessoas.id_departamento = tarefas.id_departamento " +
                "AND tarefas.id = " + id;
        List<Integer> idPessoas = jdbcTemplate.queryForList(sqlListOfPerson, Integer.class);

        //SELECIONA PESSOA ALEATORIA
        Random rand = new Random();
        Integer selectedPerson = idPessoas.get(rand.nextInt(idPessoas.size()));

        String sqlUpdatePerson = "UPDATE tarefas SET id_pessoa = " + selectedPerson + " WHERE id = " + id;
        try {
            jdbcTemplate.execute(sqlUpdatePerson);
            log.debug("[UPDATE] A Tarefa foi atualizada com sucesso! ");
            return true;
        }catch (Exception e){
            log.debug("[UPDATE ERROR] Erro ao fazer o UPDATE na tabela tarefas: " + System.lineSeparator() + e.getStackTrace());
            return false;
        }

    }

    public boolean endTask(String id) {
        String sqlEndTask = "UPDATE tarefas SET finalizado = true WHERE id = " + id;
        try {
            jdbcTemplate.execute(sqlEndTask);
            log.debug("[UPDATE] A Tarefa foi finalizada com sucesso! ");
            return true;
        }catch (Exception e){
            log.debug("[UPDATE ERROR] Erro ao fazer o UPDATE na tabela tarefas: " + System.lineSeparator() + e.getStackTrace());
            return false;
        }
    }

    public List<TaskDto> pendetTask() {
        String sql = "SELECT * from tarefas WHERE id_pessoa IS NULL ORDER BY prazo asc LIMIT 3";
        List<TaskDto> lista = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TaskDto.class));
        return lista;
    }

    public List<DepartmentDto> listDepartments() {
        String sql = "SELECT departamento.titulo, count(id_pessoa) as num_pessoas, count(tarefas.id) as num_tarefas " +
                "FROM tarefas, departamento " +
                "WHERE tarefas.id_departamento = departamento.id " +
                "GROUP BY 1";
        List<DepartmentDto> lista = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DepartmentDto.class));
        return lista;
    }
}

