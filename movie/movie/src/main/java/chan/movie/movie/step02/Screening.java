package chan.movie.movie.step02;

import chan.movie.money.Money;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private Member member;
    private MovieCategory category;
    private int sequence;
    private LocalDateTime whenScreened;
    private String cinema; // 영화관
    private String theater; // 상영관
    private ReservationDB reservationDB;

    public Screening(Movie movie, Member member, MovieCategory category, int sequence, LocalDateTime whenScreened, String cinema, String theater) {
        this.movie = movie;
        this.member = member;
        this.category = category;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
        this.cinema = cinema;
        this.theater = theater;
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    // Movie에 있는 getFee는 Screening에서 그 책임을 받는게 나아 보여.
    // 왜냐면 "영화관"마다, 인간 유형마다 다 금액이 다르니까.
    public Money getMovieFee() {
        return movie.getFee();
    }

    // Customer 랑 Member 를 합칠 수 있지 않을까... 코드가 너무 개판이 되어 가는거 같은데......
    public Reservation reserve(Customer customer, int audienceCount) {
            return new Reservation(customer, this, calculateFee(audienceCount),
                reservationDB);
    }

    private Money calculateFee(int audienceCount) {
        return movie.calculateTotalMovieFee(this).times(audienceCount);
    }
}
