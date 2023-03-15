package trainReservation.entity;

import java.util.List;

// 기차 정보 Entity class
public class Train {
	private String trainNumber;
	private String departureStation;
	private String departureTime;
	private String arrivalStation;
	private String arrivalTime;
	private int takeMinute;
	private String type;
	private List<StopStation> stopStations; // 도착역 class 리스트 (기차종류, 역종류가 많기 때문에 List로 관리)
	private List<Seat> srats; // 좌석 class 리스트
	
	public Train() {}

	public Train(String trainNumber, String departureStation, String departureTime, String arrivalStation,
			String arrivalTime, int takeMinute, String type, List<StopStation> stopStations, List<Seat> srats) {
		this.trainNumber = trainNumber;
		this.departureStation = departureStation;
		this.departureTime = departureTime;
		this.arrivalStation = arrivalStation;
		this.arrivalTime = arrivalTime;
		this.takeMinute = takeMinute;
		this.type = type;
		this.stopStations = stopStations;
		this.srats = srats;
	}

	public String getTrainNumber() {
		return this.trainNumber;
	}

	public String getDepartureStation() {
		return this.departureStation;
	}

	public String getDepartureTime() {
		return this.departureTime;
	}

	public String getArrivalStation() {
		return this.arrivalStation;
	}

	public String getArrivalTime() {
		return this.arrivalTime;
	}

	public String getType() {
		return this.type;
	}

	public int getTakeMinute() {
		return this.takeMinute;
	}

	public List<StopStation> getStopStations() {
		return this.stopStations;
	}

	public List<Seat> getSrats() {
		return this.srats;
	}

	@Override
	public String toString() {
		return "Train [trainNumber=" + this.trainNumber + ", departureStation=" + this.departureStation + ", departureTime="
				+ this.departureTime + ", arrivalStation=" + this.arrivalStation + ", arrivalTime=" + this.arrivalTime + ", type="
				+ this.type + ", takeMinute=" + this.takeMinute + ", stopStations=" + this.stopStations + ", srats=" + this.srats + "]";
	}
	
	
	
}
