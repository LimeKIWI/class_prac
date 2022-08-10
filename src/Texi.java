public class Texi extends Car implements PayAble {
    private static int serialCount = 2000; // 고유넘버링
    private Car.stat stat;  // 차량상태
    private String destination; // 목적지이름
    private int destDistance; // 목적지까지거리
    private int additionalFee; // 추가요금
    private int defaultDistance; // 기본거리
    private int defaultFee; // 기본요금

    public Texi() {
        this.idNumber = serialCount;
        Texi.serialCount++;
        this.maxPassenger = 4;
        this.curPassenger = 0;
        this.defaultFee = 3000;
        this.defaultDistance = 1;
        this.additionalFee = 1000;
        this.stat = stat.일반;
    }

    // 운행시작
    public void run() {
        switchStatus("일반");
    }

    // 상태변경
    @Override
    public void switchStatus(String str) {
        if (isValidOil(false)) {
            if (str.equals("일반")) {
                stat = stat.일반;
                curPassenger = 0;
            } else if (str.equals("운행중")) {
                stat = stat.운행중;
            } else
                stat = stat.운행불가;
        } else
            stat = stat.운행불가;
    }

    // 택시승객 탑승시키기
    public void addPassengerTexi(int numOfPass, String dest, int distance) {
        // 최대승객수보다 많거나 일반상태가아니면 탑승시키지않는다.
        if (isMaxPassenger(numOfPass) && stat.name().equals("일반")) {
            switchStatus("운행중");
            addPassenger(numOfPass);
            destDistance = distance;
            destination = dest;
            printStatus();
        } else
            System.out.println("탑승할 수 없습니다.\n");
    }

    // 속도바꾸기. 버스만 오일게이지 확인후 속도를 바꿀 수 있고 택시요구사항에는 없길래 버스와 택시를 따로만들었다.
    @Override
    public void changeSpeed(int newSpeed) {
        setSpeed(newSpeed);
    }

    // 요금계산
    @Override
    public int calcPay() {
        int dist = destDistance - defaultDistance;
        if (dist > 0) {
            return defaultFee + (dist * additionalFee);
        }
        return defaultFee;
    }

    // 결제하기
    @Override
    public void pay() {
        totalFee += calcPay();
        curPassenger = 0;
        run();
        printStatus();
    }

    // 기름넣기
    @Override
    public void addOil(int amount) {
        this.setOilGage(amount);
        if (!isValidOil(false))
            switchStatus("운행불가");
    }


    // 차량상태 출력
    @Override
    public void printStatus() {
        int oilAmount = getOilGage();
        System.out.println("택시번호 : " + idNumber);
        if (stat.name().equals("운행불가")) {
            System.out.println("상태 : 운행불가");
        } else if (stat.name().equals("운행중")) {
            System.out.println("상태 : 운행중");
            System.out.println("탑승 승객 수 : " + curPassenger);
            System.out.println("잔여 승객 수 : " + (maxPassenger - curPassenger));
            System.out.println("기본요금확인 : " + defaultFee);
            System.out.println("목적지 : " + destination);
            System.out.println("목적지까지 거리 : " + destDistance + "km");
            System.out.println("지불할 요금 : " + calcPay());
        } else {
            System.out.println("상태 : 일반");
        }
        System.out.println("누적 요금 : " + totalFee);
        System.out.println("주유량 : " + oilAmount);
        isValidOil(true);
        System.out.println();

    }

}
