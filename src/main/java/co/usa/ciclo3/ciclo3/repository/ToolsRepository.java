package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.model.Tools;
import co.usa.ciclo3.ciclo3.repository.crud.ToolsCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ToolsRepository {

    @Autowired
    private ToolsCrudRepository toolsCrudrepository;

    public List<Tools> getAll(){
        return (List<Tools>) toolsCrudrepository.findAll();
    }

    public Optional<Tools> getTools(int id){
        return toolsCrudrepository.findById(id);
    }

    public Tools save(Tools t){
        return  toolsCrudrepository.save(t);
    }
}
