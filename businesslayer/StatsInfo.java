package cinema.businesslayer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsInfo {
    @JsonProperty("current_income")
    private int currentIncome;
    @JsonProperty("number_of_available_seats")
    private int noOfAvailableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private int noOfPurchasedSeats;

    public StatsInfo(int currentIncome, int noOfAvailableSeats, int noOfPurchasedSeats) {
        this.currentIncome = currentIncome;
        this.noOfAvailableSeats = noOfAvailableSeats;
        this.noOfPurchasedSeats = noOfPurchasedSeats;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(int currentIncome) {
        this.currentIncome = currentIncome;
    }

    public int getNoOfAvailableSeats() {
        return noOfAvailableSeats;
    }

    public void setNoOfAvailableSeats(int noOfAvailableSeats) {
        this.noOfAvailableSeats = noOfAvailableSeats;
    }

    public int getNoOfPurchasedSeats() {
        return noOfPurchasedSeats;
    }

    public void setNoOfPurchasedSeats(int noOfPurchasedSeats) {
        this.noOfPurchasedSeats = noOfPurchasedSeats;
    }
}
