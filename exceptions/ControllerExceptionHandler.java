package cinema.exceptions;

import cinema.errors.AuthorizationErrorMessage;
import cinema.errors.SeatRequestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ReturnTicketException.class)
    public ResponseEntity<SeatRequestErrorMessage> handleReturnTicketWrongToken(ReturnTicketException e, WebRequest request) {
        SeatRequestErrorMessage body = new SeatRequestErrorMessage(e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<SeatRequestErrorMessage> handleReturnTicketWrongToken(SeatNotFoundException e, WebRequest request) {
        SeatRequestErrorMessage body = new SeatRequestErrorMessage(e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatAlreadyBookedException.class)
    public ResponseEntity<SeatRequestErrorMessage> handleReturnTicketWrongToken(SeatAlreadyBookedException e, WebRequest request) {
        SeatRequestErrorMessage body = new SeatRequestErrorMessage(e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<AuthorizationErrorMessage> handleUnauthorizedAdmin(UnauthorizedException e, WebRequest request) {
        AuthorizationErrorMessage body = new AuthorizationErrorMessage(e.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }
}
