package com.gerenciador.tarefas;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping("/tarefas")
    public ResponseEntity insertTask(@RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(service.insertTask(taskDto));
    }

    @PutMapping("/tarefas/alocar/{id}")
    public ResponseEntity allocateTask(@PathVariable String id) {
        return ResponseEntity.ok(service.allocateTask(id));
    }

    @PutMapping("/tarefas/finalizar/{id}")
    public ResponseEntity endTask(@PathVariable String id) {
        return ResponseEntity.ok(service.endTask(id));
    }

    @GetMapping("/tarefas/pendentes")
    public ResponseEntity pendentTask() {
        return ResponseEntity.ok(service.pendentTask());
    }

    @GetMapping("/tarefas/departamentos")
    public ResponseEntity listDepartments() {
        return ResponseEntity.ok(service.listDepartments());
    }

}
