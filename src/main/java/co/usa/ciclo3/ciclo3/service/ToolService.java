package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Tool;
import co.usa.ciclo3.ciclo3.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ToolService {

    @Autowired
    private ToolRepository toolRepository;


    public List<Tool> getAll(){
        return toolRepository.getAll();
    }

    public Optional<Tool> getTool(int id){

        return toolRepository.getTool(id);
    }

    public Tool save(Tool t){
        if (t.getId()==null){
            return toolRepository.save(t);
        }else{
            Optional<Tool> taux = toolRepository.getTool(t.getId());
            if (taux.isEmpty()){
                return toolRepository.save(t);
            }else{
                return t;
            }
        }
    }

    public Tool update(Tool t){
        if (t.getId()!=null) {
            Optional<Tool>alt=toolRepository.getTool(t.getId());
            if (!alt.isEmpty()){
                if (t.getName()!=null){
                    alt.get().setName(t.getName());
                }
                if (t.getBrand()!=null){
                    alt.get().setBrand(t.getBrand());
                }
                if (t.getYear()!=null){
                    alt.get().setYear(t.getYear());
                }
                if (t.getDescription()!=null) {
                    alt.get().setDescription(t.getDescription());
                }
                return toolRepository.save(alt.get());
            }
        }
        return t;
    }

    public boolean delete(int id){
        Boolean alt = getTool(id).map(tool -> {
            toolRepository.delete(tool);
            return true;
        }).orElse(false);
        return alt;
    }
}
