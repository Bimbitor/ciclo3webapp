package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Tool;
import co.usa.ciclo3.ciclo3.repository.ToolsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ToolsService {

    @Autowired
    private ToolsRepository toolsRepository;


    public List<Tool> getAll(){
        return (java.util.List<Tool>) toolsRepository.getAll();
    }

    public Optional<Tool> getTools(int id){

        return toolsRepository.getTools(id);
    }

    public Tool save(Tool t){
        if (t.getId()==null){
            return toolsRepository.save(t);
        }else{
            Optional<Tool> taux = toolsRepository.getTools(t.getId());
            if (taux.isEmpty()){
                return toolsRepository.save(t);
            }else{
                return t;
            }
        }
    }
}
