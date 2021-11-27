package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    public List<Category> getAll(){
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id){

        return categoryRepository.getCategory(id);
    }

    public Category save(Category c){
        if (c.getId()==null){
            return categoryRepository.save(c);
        }else{
            Optional<Category> taux = categoryRepository.getCategory(c.getId());
            if (taux.isEmpty()){
                return categoryRepository.save(c);
            }else{
                return c;
            }
        }
    }

    public Category update(Category c){
        if (c.getId()!=null) {
            Optional<Category>alt=categoryRepository.getCategory(c.getId());
            if (!alt.isEmpty()){
                if (c.getName()!=null){
                    alt.get().setName(c.getName());
                }
                if (c.getDescription()!=null){
                    alt.get().setDescription(c.getDescription());
                }
                return categoryRepository.save(alt.get());
            }
        }
        return c;
    }

    public boolean delete(int id){
        Boolean alt = getCategory(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
        return alt;
    }
}
