package chan.movie.movie.step02;

import chan.movie.money.Money;
import chan.movie.movie.step02.pricing.AmountDiscountPolicy;
import chan.movie.movie.step02.pricing.PeriodCondition;
import chan.movie.movie.step02.pricing.SequenceCondition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class ReservationDBTest {

    ReservationDB reservationDB;
    Movie avatar;
    Screening screeningAvatar;
    Customer chan;

    @BeforeEach
    public void beforeEach(){
        avatar = new Movie("avatar",
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(Money.wons(800),
                        new SequenceCondition(1),
                        new SequenceCondition(10),
                        new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10,0), LocalTime.of(11,59)),
                        new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10,0), LocalTime.of(20,59))));

        screeningAvatar = new Screening(avatar, 120, LocalDateTime.of(2021, 1, 1, 0, 0, 0));

        chan = new Customer("chan", "45694410");


    }
    @Test
    @DisplayName("데이터 넣기")
    void insertTest() {
        // given
        ReservationDB reservationDB1 = new ReservationDB(new ArrayList());
        Reservation reservationAvatar1 = new Reservation(chan, screeningAvatar, screeningAvatar.getMovieFee(), 5, reservationDB1);

        // when
        reservationDB1.insert(reservationAvatar1);
        // then
//        System.out.println("reservationDB1 = " + reservationDB1);
        assertThat(reservationDB1).isNotNull();
    }

    @Test
    @DisplayName("다른 DB안에 예약 넣기")
    void differentDBInsertTest() {
        // given
        ReservationDB reservationDB1 = new ReservationDB(new ArrayList());
        ReservationDB reservationDB2 = new ReservationDB(new ArrayList());
        Reservation reservationAvatar1 = new Reservation(chan, screeningAvatar, screeningAvatar.getMovieFee(), 5, reservationDB1);
        Reservation reservationAvatar2 = new Reservation(chan, screeningAvatar, screeningAvatar.getMovieFee(), 5, reservationDB2);


        // when
        reservationDB1.insert(reservationAvatar1);
        reservationDB2.insert(reservationAvatar2);

        // then
        assertThat(reservationDB1).isNotSameAs(reservationDB2);
    }

    @Test
    @DisplayName("DB에서 Customer 정보 받기")
    void getCustomerTest() {
        // given
        ReservationDB reservationDB1 = new ReservationDB(new ArrayList());
        Reservation reservationAvatar1 = new Reservation(chan, screeningAvatar, screeningAvatar.getMovieFee(), 5, reservationDB1);


        // when
        reservationDB1.insert(reservationAvatar1);

        // then
        assertThat(reservationDB1.size()).isEqualTo(1);
    }

    @Test
    void checkTest() {

    }

    @Test
    void deleteTest() {
    }
}