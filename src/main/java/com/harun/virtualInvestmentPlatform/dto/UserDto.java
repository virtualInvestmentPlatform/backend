package com.harun.virtualInvestmentPlatform.dto;

import java.util.Objects;

public class UserDto {
    private String name;
    private String surname;
    private long balance;

    public UserDto(){

    }

    public UserDto(String name, String surname, long balance) {
        this.name = name;
        this.surname = surname;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return balance == userDto.balance && Objects.equals(name, userDto.name) && Objects.equals(surname, userDto.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, balance);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", balance=" + balance +
                '}';
    }
}
