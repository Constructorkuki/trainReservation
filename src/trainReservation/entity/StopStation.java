package trainReservation.entity;

// 정차역 Entity class
//정차역 (class - 역명, 도착시간, 출발시간)
public class StopStation {
	private String stationName;
	private String departureTime;
	private String arrivalTime;
	
	public StopStation() {}

	public StopStation(String stationName, String departureTime, String arrivalTime) {
		this.stationName = stationName;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	public String getStationName() {
		return this.stationName;
	}

	public String getDepartureTime() {
		return this.departureTime;
	}

	public String getArrivalTime() {
		return this.arrivalTime;
	}

	@Override
	public String toString() {
		return "StopStation [stationName=" + this.stationName + 
				", departureTime=" + this.departureTime + 
				", arrivalTime="+ this.arrivalTime + "]";
	}
	
	
}
