package cinema.presentation;

import cinema.businesslayer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieTheatreController {
    @Autowired
    SeatService seatService;

    @GetMapping("/seats")
    public MovieTheatreInfo getSeatsInfo() {
        return seatService.getSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Object> purchaseSeat(@RequestBody SeatInfo seatInfo) {
        return seatService.purchaseSeat(seatInfo.getRow(), seatInfo.getColumn());
    }

    @PostMapping("/return")
    public ResponseEntity<Object> returnTicket(@RequestBody ReturnTicketInfo returnTicketInfo) {
        return seatService.returnTicket(returnTicketInfo.getToken());
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(@RequestParam(required = false) String password) {
        return seatService.getStats(password);
    }
}
