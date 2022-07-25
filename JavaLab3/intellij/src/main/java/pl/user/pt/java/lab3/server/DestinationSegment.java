package pl.user.pt.java.lab3.server;

import java.util.Objects;

public class DestinationSegment {
    private Integer number;
    private boolean isPrime;

    public DestinationSegment(Integer number, boolean isEven) {
        this.number = number;
        this.isPrime = isEven;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DestinationSegment that = (DestinationSegment) o;
        return isPrime == that.isPrime && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, isPrime);
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }
}
