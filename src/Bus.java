public class Bus extends Car implements PayAble {
    private static int serialCount = 1000;    // 고유넘버링
    private int countPassenger; // 탑승한승객수
    private Car.stat stat;  // 차량상태

    //생성자
    public Bus() {
        this.idNumber = serialCount;
        Bus.serialCount++;
        this.maxPassenger = 30;
        this.curPassenger = 0;
        this.countPassenger = 0;
        this.defaultFee = 1000;
        this.stat = stat.운행중;
    }

    // 운행시작
    public void run() {
        switchStatus("운행중");
    }

    @Override
    // 상태가 변경되면 승객수가 초기화되므로 전부 초기화시킨다.
    public void switchStatus(String str) {
        if (isValidOil(false) && str.equals("운행중")) {
            stat = stat.운행중;
            curPassenger = 0;
        } else
            stat = stat.차고지행;
    }

    // 버스승객 탑승시키기
    public void addPassengerBus(int numOfPass) {
        // 최대승객수보다 많거나 운행중이아니면 탑승시키지않는다.
        if (isMaxPassenger(numOfPass + curPassenger) && stat.name().equals("운행중")) {
            countPassenger = numOfPass;
            addPassenger(countPassenger);
            pay();            // 요금을 낸다.
        } else
            System.out.println("탑승할 수 없습니다.\n");
    }

    // 속도바꾸기. 버스만 오일게이지 확인후 속도를 바꿀 수 있고 택시요구사항에는 없길래 버스와 택시를 따로만들었다.
    @Override
    public void changeSpeed(int newSpeed) {
        if (isValidOil(false)) { // 연료량이 10이상일때만 속도변경시킨다.
            setSpeed(newSpeed);
        }
    }

    @Override
    // 요금내기
    public void pay() {
        totalFee += calcPay();
        printStatus();
    }

    // 요금계산
    @Override
    public int calcPay() {
        return countPassenger * defaultFee;
    }

    // 기름넣기
    @Override
    public void addOil(int amount) {
        this.setOilGage(amount);
        if (!isValidOil(false))
            switchStatus("차고지행");
        printStatus();
    }

    // 차량상태 출력
    @Override
    public void printStatus() {
        int oilAmount = getOilGage();
        System.out.println("버스번호 : " + idNumber);
        if (stat.name().equals("운행중")) {
            System.out.println("상태 : 운행중");
            System.out.println("탑승 승객 수 : " + curPassenger);
            System.out.println("잔여 승객 수 : " + (maxPassenger - curPassenger));
            System.out.println("요금확인 : " + calcPay());
            System.out.println("누적요금확인 : " + totalFee);
        } else {
            System.out.println("상태 : 차고지행");
        }
        System.out.println("주유량 : " + oilAmount);
        isValidOil(true);
        System.out.println();
    }

}