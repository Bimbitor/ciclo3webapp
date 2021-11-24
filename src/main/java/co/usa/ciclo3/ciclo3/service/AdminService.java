package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Admin;
import co.usa.ciclo3.ciclo3.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;


    public List<Admin> getAll(){
        return (java.util.List<Admin>) adminRepository.getAll();
    }

    public Optional<Admin> getAdmins(int id){

        return adminRepository.getAdmin(id);
    }

    public Admin save(Admin a){
        if (a.getIdAdmin()==null){
            return adminRepository.save(a);
        }else{
            Optional<Admin> taux = adminRepository.getAdmin(a.getIdAdmin());
            if (taux.isEmpty()){
                return adminRepository.save(a);
            }else{
                return a;
            }
        }
    }
}
