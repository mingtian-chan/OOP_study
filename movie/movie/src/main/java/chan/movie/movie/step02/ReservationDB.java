package chan.movie.movie.step02;

import java.util.ArrayList;

public class ReservationDB {
    private ArrayList arrayList ;

    public ReservationDB(ArrayList reservationDB) {
        this.arrayList = reservationDB;
    }


    public void insert(Reservation reservation) {
        arrayList.add(reservation);
    }

    public void check(){

    }

    public void delete() {

    }
}
