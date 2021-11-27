package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;


    public List<Reservation> getAll(){
        return (java.util.List<Reservation>) reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id){

        return reservationRepository.getReservation(id);
    }

    public Reservation save(Reservation r){
        if (r.getIdReservation()==null){
            return reservationRepository.save(r);
        }else{
            Optional<Reservation> taux = reservationRepository.getReservation(r.getIdReservation());
            if (taux.isEmpty()){
                return reservationRepository.save(r);
            }else{
                return r;
            }
        }
    }

    public Reservation update(Reservation r){
        if (r.getIdReservation()!=null) {
            Optional<Reservation>alt=reservationRepository.getReservation(r.getIdReservation());
            if (!alt.isEmpty()){
                if (r.getStartDate()!=null){
                    alt.get().setStartDate(r.getStartDate());
                }
                if (r.getDevolutionDate()!=null){
                    alt.get().setDevolutionDate(r.getDevolutionDate());
                }
                if (r.getStatus()!=null){
                    alt.get().setStatus(r.getStatus());
                }
                if (r.getScore()!=null) {
                    alt.get().setScore(r.getScore());
                }
                return reservationRepository.save(alt.get());
            }
        }
        return r;
    }

    public boolean delete(int id){
        Boolean alt = getReservation(id).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return alt;
    }
}
