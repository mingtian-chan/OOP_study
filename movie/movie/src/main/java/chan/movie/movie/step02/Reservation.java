package chan.movie.movie.step02;

import chan.movie.money.Money;

public class Reservation {
    private Customer customer;
    private Screening Screening;
    private Money fee;
    private int audienceCount;
    private ReservationDB reservationDB;

    public void insertData(Reservation reservation, ReservationDB reservationDB) {
        reservationDB.insert(reservation);
    }

    public Reservation(Customer customer, Screening Screening, Money fee, int audienceCount, ReservationDB reservationDB) {
        this.customer = customer;
        this.Screening = Screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
        this.reservationDB = reservationDB;
        reservationDB.insert(this);
    }

    public Reservation Check(){

        return ;

    }
}
