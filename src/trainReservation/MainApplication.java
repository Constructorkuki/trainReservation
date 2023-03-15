package trainReservation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import trainReservation.dto.GetTrainListDto;
import trainReservation.entity.Cost;
import trainReservation.entity.Seat;
import trainReservation.entity.StopStation;
import trainReservation.entity.Train;

// Entity : 데이터베이스 데이터를 클래스로 가져와 구현.

public class MainApplication {
	
	// DB가 없기 때문에 리스트 데이터 만들어 놓음.
	private static List<Train> trains = new ArrayList<Train>();
	private static List<Cost> costs = new ArrayList<Cost>();
	
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		
		initData();
		
		// 종료안되고 계속 입력 받도록 while문 true고정.
		while (true) {
			GetTrainListDto trainListDto = new GetTrainListDto(); // 생성자 실행시 동시에 scanner 실행.
			
			LocalTime departureTime = null;
			
			// 길게 Dto 가져와서 짜는 방식보다.
			//if (trainListDto.getArrivalStation().isBlank() || trainListDto.getDepartureStation().isBlank() || trainListDto.getDepartureTime().isBlank()) {
				//System.out.println("모두 입력하세요.");
				//continue;
			//}
			
			// Dto에서 만들어서 이렇게 짜주는게 깔금.
			if (trainListDto.isEmpty()) {
				System.out.println("모두 입력하세요.");
				continue;
			}
			// 입력 구문 ----------------------------------------------------------------------------------------------------------------------------------------------
			try {
				departureTime = LocalTime.parse(trainListDto.getDepartureTime(), timeFormatter);
			} catch(Exception exception) {
				System.out.println("잘못된 시간입니다.");
				continue;
			}
			
			if (trainListDto.getNumberOfPeople() <= 0) {
				System.out.println("잘못된 인원 입니다.");
				continue;
			}
			
			if (trainListDto.isEqualStation()) {
				System.out.println("출발역과 도착역이 같습니다.");
				continue;
			}
			// 프로세스 구문 -----------------------------------------------------------------------------------------------------------------------------------------
			List<Train> possibleTrains = new ArrayList<>();
			
			// 첫 반복문.
			for (Train train: trains) {
				List<StopStation> stopStations = train.getStopStations();
				int sameStationIndex = -1;
				
				for (int stopStationIndex = 0 ; stopStationIndex < stopStations.size() ; stopStationIndex++) {
					
					String stopSationName = stopStations.get(stopStationIndex).getStationName();
					
					if (trainListDto.isEqualDepartureStation(stopSationName)) {
						continue;
					}
					
					LocalTime stationDepartureTime = LocalTime.parse(trainListDto.getDepartureTime(), timeFormatter);
					
					if (stationDepartureTime.isBefore(departureTime)) {
						break;
					}
					
					sameStationIndex = stopStationIndex;
					break;
				}
				
				// 동일한 출발역이 저장되었는가\
				// 아니면 첫 반복문 다시 돌자
				if (sameStationIndex == -1) {
					continue;
				}
				// 좌석
				boolean isPossible = false; 
				
				// 두번째 반복문.
				for (int stopStationIndex = 0 ; stopStationIndex < stopStations.size() ; stopStationIndex++) {
					String stationName = stopStations.get(stopStationIndex).getStationName();
					
					if(!trainListDto.isEqualArrivalStation(stationName)) {
						continue;
					}
					
					if (stopStationIndex <= sameStationIndex) {
						break;
					}
					// 가능한 열차 리스트에 해당 열차 객체 추가.
					//possibleTrains.add(train);
					isPossible = true;
					break;
				}
				
				if (isPossible) {
					continue; // 올려보내.
				}
				
				List<Seat> seats = train.getSrats();
				int possibleSeatCount = 0;
				
				for (Seat seat: seats) {
					if (!seat.isSeatStatus()) possibleSeatCount++;
				}
				
				if (possibleSeatCount < trainListDto.getNumberOfPeople()) {
					continue;
				}
				
				possibleTrains.add(train);
				
				System.out.println(possibleTrains.toString());
			}
			
			// GetTrainListDto 로 이동
			/*
			 * Scanner scanner = new Scanner(System.in); System.out.print("출발역 : ");
			 * trainListDto.setDepartureStation(scanner.nextLine()); //String
			 * departureStation = scanner.nextLine(); System.out.println("도착역 : ");
			 * trainListDto.setArrivalStation(scanner.nextLine()); //String arrivalStation =
			 * scanner.nextLine(); System.out.println("출발 시간 : ");
			 * trainListDto.setDepartureTime(scanner.nextLine()); //String departureTime =
			 * scanner.nextLine(); System.out.println("인원 : ");
			 * trainListDto.setNumberOfPeople(scanner.nextInt()); //int numberOfPeople =
			 * scanner.nextInt();
			 */			
			
			// 검증
			
			System.out.println(trainListDto.toString());
		}
	}
	
	private static void initData() {
		costs.add(new Cost("부산역", "서울역", 30000));
		costs.add(new Cost("부산역", "대전역", 20000));
		costs.add(new Cost("부산역", "대구역", 10000));
		costs.add(new Cost("대구역", "서울역", 20000));
		costs.add(new Cost("대구역", "대전역", 10000));
		costs.add(new Cost("대전역", "서울역", 10000));
		
		costs.add(new Cost("서울역", "부산역", 30000));
		costs.add(new Cost("대전역", "부산역", 20000));
		costs.add(new Cost("대구역", "부산역", 10000));
		costs.add(new Cost("서울역", "대구역", 20000));
		costs.add(new Cost("대전역", "대구역", 10000));
		costs.add(new Cost("서울역", "대전역", 10000));
		
		Train train; // 모든 정보를 담은 하나의 객체(기차)이기 떄문에 기차1, 기차2 만들고(Train train) 배열에 저장(trains.add).
		List<Seat> seats = new ArrayList<>();
		List<StopStation> stopStations = new ArrayList<>();
		
		seats.add(new Seat(1, "A1", false));
		seats.add(new Seat(1, "B1", false));
		seats.add(new Seat(1, "A2", false));
		seats.add(new Seat(1, "B2", false));
		seats.add(new Seat(2, "A3", false));
		seats.add(new Seat(2, "B3", false));
		seats.add(new Seat(2, "A4", false));
		seats.add(new Seat(2, "B4", false));
		seats.add(new Seat(3, "A5", false));
		seats.add(new Seat(3, "B5", false));
		seats.add(new Seat(3, "A6", false));
		seats.add(new Seat(3, "B6", false));
		
		stopStations.add(new StopStation("서울역", "", "06:00"));
		stopStations.add(new StopStation("대전역", "06:45", "07:00"));
		stopStations.add(new StopStation("대구역", "07:45", "08:00"));
		stopStations.add(new StopStation("부산역", "09:00", ""));
		
		train = new Train("KTX001", "서울역", "06:00", "부산역", "9:00", 180, "KTX", stopStations, seats);
		trains.add(train);
		
		seats = new ArrayList<>();
		stopStations = new ArrayList<>();
		
		seats.add(new Seat(1, "A1", false));
		seats.add(new Seat(1, "B1", false));
		seats.add(new Seat(1, "A2", false));
		seats.add(new Seat(1, "B2", false));
		seats.add(new Seat(2, "A3", false));
		seats.add(new Seat(2, "B3", false));
		seats.add(new Seat(2, "A4", false));
		seats.add(new Seat(2, "B4", false));
		seats.add(new Seat(3, "A5", false));
		seats.add(new Seat(3, "B5", false));
		seats.add(new Seat(3, "A6", false));
		seats.add(new Seat(3, "B6", false));
		
		stopStations.add(new StopStation("부산역", "", "09:00"));
		stopStations.add(new StopStation("대구역", "09:45", "10:00"));
		stopStations.add(new StopStation("대전역", "10:45", "11:00"));
		stopStations.add(new StopStation("서울역", "12:00", ""));
		
		train = new Train("KTX002", "부산", "09:00", "서울역", "12:00", 180, "KTX", stopStations, seats);
		trains.add(train);
	}

}
