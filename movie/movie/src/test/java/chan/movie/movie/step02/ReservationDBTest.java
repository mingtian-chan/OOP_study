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
    void DBSizeTest() {
        // given
        ReservationDB reservationDB1 = new ReservationDB(new ArrayList());


        // when
        Reservation reservationAvatar1 = new Reservation(chan, screeningAvatar, screeningAvatar.getMovieFee(), 5, reservationDB1);


        // then
        assertThat(reservationDB1.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("id로 DB체크")
    void checkByIdTest() {
        // given
        ReservationDB reservationDB1 = new ReservationDB(new ArrayList());
        Reservation reservationAvatar1 = new Reservation(chan, screeningAvatar, screeningAvatar.getMovieFee(), 5, reservationDB1);
        Customer customer1 = new Customer("chan", "45694410");

        // when

        Reservation userReservation = (Reservation) reservationDB1.get(0);
        String reservedId = userReservation.getCustomer().getId();

        // then
        assertThat(customer1.CheckId(reservedId)).isTrue();
    }
    @Test
    @DisplayName("id가 다른 경우 다르다고 나옴")
    void checkByIdTest2() {
        // given
        ReservationDB reservationDB1 = new ReservationDB(new ArrayList());
        Reservation reservationAvatar1 = new Reservation(chan, screeningAvatar, screeningAvatar.getMovieFee(), 5, reservationDB1);
        Customer customer1 = new Customer("notChan", "12345678");

        // when

        Reservation userReservation = (Reservation) reservationDB1.get(0);
        String reservedId = userReservation.getCustomer().getId();

        // then
        assertThat(customer1.CheckId(reservedId)).isFalse();
    }

    @Test
    @DisplayName("DB에서 Customer 정보 받기")
    void getCustomerTest() {
        // given
        ReservationDB reservationDB1 = new ReservationDB(new ArrayList());

        // when
        // 호출하면서 바로 DB에 들어가야함
        Reservation reservationAvatar1 = new Reservation(chan, screeningAvatar, screeningAvatar.getMovieFee(), 5, reservationDB1);

        // then
        assertThat(reservationDB1.size()).isEqualTo(1);
    }

    @Test
    void checkTest1() {

        // given
        // 하나의 DB에 두개의 reservation을 넣고, name과 id로 DB를 돌면서 맞는 reservation을 리턴
        ReservationDB reservationDB1 = new ReservationDB(new ArrayList());

        Customer customerA = new Customer("A", "1234");
        Customer customerB = new Customer("B", "5678");

        Reservation reservationAvatar1 = new Reservation(customerA, screeningAvatar, screeningAvatar.getMovieFee(), 5, reservationDB1);
        Reservation reservationAvatar2 = new Reservation(customerB, screeningAvatar, screeningAvatar.getMovieFee(), 3, reservationDB1);


        // when
        reservationDB1.check("A", "1234");

        // then


    }
    @Test
    void checkTest2() {

        // given
        // name과 id가 다르면 DB에서 reservation이 나오지 않는다.

        // when

        // then
    }

    @Test
    void checkTest3() {

        // given
        // 두개의 DB에 두개의 reservation을 넣고, name과 id로 체크했을 때, DB가 다르면 안 나온다.

        // when

        // then
    }

    @Test
    void deleteTest() {
    }
}