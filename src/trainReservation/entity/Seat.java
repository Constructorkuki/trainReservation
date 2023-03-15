package trainReservation.entity;

//남아있는 좌석 (class - 호차, 좌석번호, 좌석 상태)
public class Seat {
	private int roomNumber;
	private String seatNumber;
	private boolean seatStatus;
	
	public Seat() {}

	public Seat(int roomNumber, String seatNumber, boolean seatStatus) {
		this.roomNumber = roomNumber;
		this.seatNumber = seatNumber;
		this.seatStatus = seatStatus;
	}

	public int getRoomNumber() {
		return this.roomNumber;
	}

	public String getSeatNumber() {
		return this.seatNumber;
	}

	public boolean isSeatStatus() {
		return this.seatStatus;
	}

	@Override
	public String toString() {
		return "Seat [roomNumber=" + this.roomNumber + 
				", seatNumber=" + this.seatNumber + 
				", seatStatus=" + this.seatStatus + "]";
	}
	
}
