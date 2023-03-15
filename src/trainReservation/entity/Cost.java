package trainReservation.entity;

// 비용 Entity class
//비용 (class - 출발역, 도착역, 금액) : 독립적이기 때문에 따로 class만들어서 빼줌.
// Entity : setter을 사용하지 않고 읽기전용으로만 사용.
public class Cost {
	private String departureStation;
	private String arrivalStation;
	private int amount;
	
	public Cost() {}
	
	public Cost(String departureStation, String arrivalStation, int amount) {
		this.departureStation = departureStation;
		this.arrivalStation = arrivalStation;
		this.amount = amount;
	}
	
	public String getDepartureStation() {
		return this.departureStation;
	}
	
	public String getArrivalStation() {
		return this.arrivalStation;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	// 모든 class의 조상인 Object 의 toString을 재정의 (오버라이딩).
	public String toString() {
		return "Cost [departureStation: "+ this.departureStation +
				", arrivalStation: "+ this.arrivalStation +
				", amount: "+ this.amount +"]";
	}
}
