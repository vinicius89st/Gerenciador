package com.gerenciador.pessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository repository;
    public boolean insertPeople(PeopleDto peopleDto) {
        return repository.insertPeople(peopleDto);
    }

    public boolean updatePeople(String id, PeopleDto peopleDto) {
        return repository.updatePeople(id, peopleDto);
    }

    public boolean deletePeople(String id) {
        return repository.deletePeople(id);
    }

    public List<PeopleDto> listByNameDepartmentAndDuration() {
        return repository.listByNameDepartmentAndDuration();
    }
}
