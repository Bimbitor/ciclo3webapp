package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.model.Tool;
import co.usa.ciclo3.ciclo3.repository.crud.ToolCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ToolRepository {

    @Autowired
    private ToolCrudRepository toolCrudRepository;

    public List<Tool> getAll(){
        return (List<Tool>) toolCrudRepository.findAll();
    }

    public Optional<Tool> getTool(int id){
        return toolCrudRepository.findById(id);
    }

    public Tool save(Tool t){
        return  toolCrudRepository.save(t);
    }

    public void delete(Tool t){
        toolCrudRepository.delete(t);
    }
}
