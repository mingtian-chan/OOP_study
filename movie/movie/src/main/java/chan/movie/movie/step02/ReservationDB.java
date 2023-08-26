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

    public void check(String name, String id){
        System.out.println("Function Called");
        for (int i = 0; i < this.size(); i++) {
            Reservation reservation = (Reservation) this.get(i);
            if (reservation.check(name, id)) {
                System.out.println("reservation = " + reservation);
            }
        }
    }

    public void delete() {

    }
}
