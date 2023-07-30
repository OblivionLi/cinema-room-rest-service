package cinema.errors;

public class SeatRequestErrorMessage {
    private String error;

    public SeatRequestErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
