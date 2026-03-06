package com.crbas.domain;

public class StatePatternQuickTest {
    public static void main(String[] args) {
        Booking b = new Booking(1, 1);
        System.out.println(b.getStatus());

        b.approve();
        System.out.println(b.getStatus());

        b.cancel();
        System.out.println(b.getStatus());
    }
}
