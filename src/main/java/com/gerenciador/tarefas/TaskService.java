package com.gerenciador.tarefas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public boolean insertTask(TaskDto taskDto) {
        return repository.insertTask(taskDto);
    }

    public boolean allocateTask(String id) {
        return repository.allocateTask(id);
    }

    public boolean endTask(String id) {
        return repository.endTask(id);
    }

    public List<TaskDto> pendentTask() {
        return repository.pendetTask();
    }

    public List<DepartmentDto> listDepartments() {
        return repository.listDepartments();
    }
}
