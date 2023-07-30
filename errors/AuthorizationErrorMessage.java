package cinema.errors;

public class AuthorizationErrorMessage {
    private String error;

    public AuthorizationErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
