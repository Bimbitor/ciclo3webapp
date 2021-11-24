package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Message;
import co.usa.ciclo3.ciclo3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;


    public List<Message> getAll(){
        return (java.util.List<Message>) messageRepository.getAll();
    }

    public Optional<Message> getMessages(int id){

        return messageRepository.getMessage(id);
    }

    public Message save(Message m){
        if (m.getIdMessage()==null){
            return messageRepository.save(m);
        }else{
            Optional<Message> taux = messageRepository.getMessage(m.getIdMessage());
            if (taux.isEmpty()){
                return messageRepository.save(m);
            }else{
                return m;
            }
        }
    }
}
