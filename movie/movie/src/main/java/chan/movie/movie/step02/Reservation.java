package chan.movie.movie.step02;

import chan.movie.money.Money;

import java.util.ArrayList;

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

    public Customer getCustomer() {
        return customer;
    }

    public boolean check(String name, String id) {
        if (customer.CheckName(name) && customer.CheckId(id)) {
            return true;
        } else return false;
    }
}
