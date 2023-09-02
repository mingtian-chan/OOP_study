package chan.movie.movie.step02;

import chan.movie.money.Money;

public class Member {
    private int adult; // 그대로
    private int teen; // 2000원 할인
    private int child; // 5000원 할인
    private int fee;

    public Member(int adult, int teen, int child) {
        this.adult = adult;
        this.teen = teen;
        this.child = child;
    }

    public int totalMember() {
        return adult + teen + child;
    }
}
