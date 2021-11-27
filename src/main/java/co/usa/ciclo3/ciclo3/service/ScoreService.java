package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Score;
import co.usa.ciclo3.ciclo3.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;


    public List<Score> getAll(){
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id){

        return scoreRepository.getScore(id);
    }

    public Score save(Score s){
        if (s.getIdScore()==null){
            return scoreRepository.save(s);
        }else{
            Optional<Score> taux = scoreRepository.getScore(s.getIdScore());
            if (taux.isEmpty()){
                return scoreRepository.save(s);
            }else{
                return s;
            }
        }
    }

    public Score update(Score s){
        if (s.getIdScore()!=null) {
            Optional<Score>alt=scoreRepository.getScore(s.getIdScore());
            if (!alt.isEmpty()){
                if (s.getValue()!=null){
                    alt.get().setValue(s.getValue());
                }
                return scoreRepository.save(alt.get());
            }
        }
        return s;
    }

    public boolean delete(int id){
        Boolean alt = getScore(id).map(score -> {
            scoreRepository.delete(score);
            return true;
        }).orElse(false);
        return alt;
    }
}
