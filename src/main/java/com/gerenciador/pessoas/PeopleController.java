package com.gerenciador.pessoas;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
public class PeopleController {

    @Autowired
    private PeopleService service;

    @PostMapping("/pessoas")
    public ResponseEntity insertPeople(@RequestBody PeopleDto peopleDto) {
        return ResponseEntity.ok(service.insertPeople(peopleDto));
    }
    @PutMapping("/pessoas/{id}")
    public ResponseEntity updatePeople(@PathVariable String id, @RequestBody PeopleDto peopleDto) {
        return ResponseEntity.ok(service.updatePeople(id, peopleDto));
    }
    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity deletePeople(@PathVariable String id) {
        return ResponseEntity.ok(service.deletePeople(id));
    }

    @GetMapping("/pessoas")
    public ResponseEntity listByNameDepartmentAndDuration() {
        return ResponseEntity.ok(service.listByNameDepartmentAndDuration());
    }


}
