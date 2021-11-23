package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.model.Tool;
import co.usa.ciclo3.ciclo3.repository.crud.ToolsCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ToolsRepository {

    @Autowired
    private ToolsCrudRepository toolsCrudRepository;

    public List<Tool> getAll(){
        return (List<Tool>) toolsCrudRepository.findAll();
    }

    public Optional<Tool> getTools(int id){
        return toolsCrudRepository.findById(id);
    }

    public Tool save(Tool t){
        return  toolsCrudRepository.save(t);
    }
}
