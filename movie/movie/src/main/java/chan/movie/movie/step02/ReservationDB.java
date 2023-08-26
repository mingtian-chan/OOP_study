package chan.movie.movie.step02;

import java.util.ArrayList;

public class ReservationDB extends ArrayList {
    private ArrayList arrayList ;

    public ReservationDB(ArrayList reservationDB) {
        this.arrayList = reservationDB;
    }


    public void insert(Reservation reservation) {
        this.add(reservation);
    }

//    public void check(String name, String id){
//        for (Reservation reservation : arrayList) {
//            reservation.check();
//        }
//    }

    public void delete() {

    }
}
