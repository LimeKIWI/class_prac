public abstract class Car {
    protected int idNumber; // 차량번호
    protected int maxPassenger; // 최대승객수
    protected int curPassenger; // 현재승객수
    protected int defaultFee; // 기본요금
    protected int totalFee; // 벌어들인요금
    private int oilGage; // 주유량
    private int speed; // 현재속도
//    String status; // 현재상태

    enum stat {
        운행중, 일반, 차고지행, 운행불가
    }

    // 자동차생성자
    public Car() {
        this.totalFee = 0;
        this.oilGage = 100;
        this.speed = 0;
    }

    // 최대승객수확인
    public boolean isMaxPassenger(int newPassengerNum) {
        if (maxPassenger > newPassengerNum) {
            return true;
        }
        System.out.println("최대 승객 수 초과!");
        return false;
    }

    // 달릴수 있는 기름양인지 확인
    public boolean isValidOil(boolean alert) {
        if (oilGage < 10) { // 주유량이 10미만이면
            if (alert)
                System.out.println("!!!주유필요!!!");
        }
        return (oilGage < 10) ? false : true;
    }

    // 주유량 출력
    public int getOilGage() {
        return oilGage;
    }

    // 속도 출력
    public int getSpeed() { return speed; }

    // 주유량 변경
    public void setOilGage(int amount) { oilGage = (amount + oilGage < 0) ? 0 : amount + oilGage;  } // 연료의 최소량은 0 이하로 떨어지지않는다.

    // 속도저장
    public void setSpeed(int newSpeed) {
        speed = (speed + newSpeed < 0) ? 0 : speed + newSpeed; // 속도가 0이하로는 떨어지지 않는다.
        System.out.println("현재 속도 : " + speed);
    }

    // 승객 탑승
    public void addPassenger(int numOfPass) {   curPassenger += numOfPass; }// 현재 승객수를 증가시킨다.

    // 운행 시작
    public abstract void run();

    // 상태 변경
    public abstract void switchStatus(String str);

    // 속도 변경
    public abstract void changeSpeed(int newSpeed);

    // 기름 넣기
    public abstract void addOil(int oilAmount);

    // 차량 상태 출력
    public abstract void printStatus();

}