package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Tools;
import co.usa.ciclo3.ciclo3.repository.ToolsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ToolsService {

    @Autowired
    private ToolsRepository toolsRepository;


    public List<Tools> getAll(){
        return (java.util.List<co.usa.ciclo3.ciclo3.model.Tools>) toolsRepository.getAll();
    }

    public Optional<Tools> getTools(int id){

        return toolsRepository.getTools(id);
    }

    public Tools save(Tools t){
        if (t.getId()==null){
            return toolsRepository.save(t);
        }else{
            Optional<Tools> taux = toolsRepository.getTools(t.getId());
            if (taux.isEmpty()){
                return toolsRepository.save(t);
            }else{
                return t;
            }
        }
    }
}
