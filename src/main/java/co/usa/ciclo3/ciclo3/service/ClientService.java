package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;


    public List<Client> getAll(){
        return (java.util.List<Client>) clientRepository.getAll();
    }

    public Optional<Client> getClient(int id){

        return clientRepository.getClient(id);
    }

    public Client save(Client c){
        if (c.getIdClient()==null){
            return clientRepository.save(c);
        }else{
            Optional<Client> taux = clientRepository.getClient(c.getIdClient());
            if (taux.isEmpty()){
                return clientRepository.save(c);
            }else{
                return c;
            }
        }
    }

    public Client update(Client t){
        if (t.getIdClient()!=null) {
            Optional<Client>alt=clientRepository.getClient(t.getIdClient());
            if (!alt.isEmpty()){
                if (t.getName()!=null){
                    alt.get().setName(t.getName());
                }
                if (t.getEmail()!=null){
                    alt.get().setEmail(t.getEmail());
                }
                if (t.getPassword()!=null){
                    alt.get().setPassword(t.getPassword());
                }
                if (t.getName()!=null) {
                    alt.get().setName(t.getName());
                }
                return clientRepository.save(alt.get());
            }
        }
        return t;
    }

    public boolean delete(int id){
        Boolean alt = getClient(id).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return alt;
    }
}
