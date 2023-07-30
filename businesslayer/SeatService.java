package cinema.businesslayer;

import cinema.exceptions.ReturnTicketException;
import cinema.exceptions.SeatAlreadyBookedException;
import cinema.exceptions.SeatNotFoundException;
import cinema.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SeatService {
    private final int totalRows = 9;
    private final int totalColumns = 9;
    private final List<Seat> availableSeats = this.initializeSeats();
    private final Map<String, SeatDTO> purchasedSeats = new HashMap<>();

    public MovieTheatreInfo getSeats() {
        return new MovieTheatreInfo(this.totalRows, this.totalColumns, this.availableSeats);
    }

    public ResponseEntity<Object> purchaseSeat(int row, int column) {
        MovieTheatreInfo movieTheatre = this.getSeats();

        if ( row > movieTheatre.getTotalRows()
                || column > movieTheatre.getTotalColumns()
                || row < 1
                || column < 1
        ) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "The number of a row or a column is out of bounds!");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        boolean isSeatBooked = true;
        for (var seat : movieTheatre.getAvailableSeats()) {
            if (row == seat.getRow() && column == seat.getColumn()) {
                isSeatBooked = false;
                break;
            }
        }

        if (isSeatBooked) {
            throw new SeatAlreadyBookedException("The ticket has been already purchased!");
        }

        SeatDTO seatDTO = null;
        Seat purchasedSeat = new Seat(row, column);
        Iterator<Seat> iterator = movieTheatre.getAvailableSeats().iterator();
        while (iterator.hasNext()) {
            Seat seat = iterator.next();
            if (seat.equals(purchasedSeat)) {
                seatDTO = new SeatDTO(seat.getRow(), seat.getColumn(), seat.getPrice());
                iterator.remove();
                break;
            }
        }

        if (seatDTO == null) {
            throw new SeatNotFoundException("Couldn't find seat. Please check if row and column is correct.");
        }

        Map<String, Object> response = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        response.put("token", uuid.toString());
        response.put("ticket", seatDTO);

        this.purchasedSeats.put(uuid.toString(), seatDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> returnTicket(String token) {
        if (this.purchasedSeats.containsKey(token)) {
            SeatDTO seatDTO = this.purchasedSeats.get(token);
            this.purchasedSeats.remove(token);
            this.availableSeats.add(new Seat(seatDTO.getRow(), seatDTO.getColumn()));

            Map<String, SeatDTO> response = new HashMap<>();
            response.put("returned_ticket", seatDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        throw new ReturnTicketException("Wrong token!");
    }

    public ResponseEntity<Object> getStats(String password) {
        if (password == null || !password.equals("super_secret")) {
            throw new UnauthorizedException("The password is wrong!");
        }

        int currentIncome = 0;
        for (var seats : this.purchasedSeats.values()) {
            currentIncome += seats.getPrice();
        }

        StatsInfo statsInfo = new StatsInfo(currentIncome, this.availableSeats.size(), this.purchasedSeats.size());
        return new ResponseEntity<>(statsInfo, HttpStatus.OK);
    }

    private List<Seat> initializeSeats() {
        List<Seat> availableSeats = new ArrayList<>();

        for (int row = 1; row <= this.totalRows; row++) {
            for (int column = 1; column <= this.totalColumns; column++) {
                availableSeats.add(new Seat(row, column));
            }
        }

        return availableSeats;
    }
}
