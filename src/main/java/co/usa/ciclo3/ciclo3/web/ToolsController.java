package co.usa.ciclo3.ciclo3.web;

import co.usa.ciclo3.ciclo3.model.Tools;
import co.usa.ciclo3.ciclo3.service.ToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tools")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ToolsController {

    @Autowired
    private ToolsService toolsService;

    @GetMapping("/all")
    public List<Tools> getTools(){
        return toolsService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Tools> getTool(@PathVariable("id") int id){
        return toolsService.getTools(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Tools save(@RequestBody Tools t){
        return toolsService.save(t);
    }
}
