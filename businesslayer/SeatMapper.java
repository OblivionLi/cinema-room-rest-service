package cinema.businesslayer;

public class SeatMapper {
    public static SeatDTO convertSeatToDTO(Seat seat) {
        SeatDTO dto = new SeatDTO(seat.getRow(), seat.getColumn(), seat.getPrice());
        return dto;
    }
}
