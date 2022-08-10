// 결제를 할수있는가?
interface PayAble {
    void pay(); // 결제하기

    int calcPay();  // 요금계산하기
}

public class Main {
    public static void main(String[] args) {

        // TODO Auto-generated method stub
        Bus bus1 = new Bus();
        Bus bus2 = new Bus();
        bus1.printStatus();
        bus2.printStatus();

        bus1.addPassengerBus(2);
        bus1.addOil(-50);
        bus1.switchStatus("차고지행");
        bus1.addOil(10);
        bus1.switchStatus("운행중");
        bus1.addPassengerBus(45);
        bus1.addPassengerBus(5);
        bus1.addOil(-55);

        Texi t1 = new Texi();
        Texi t2 = new Texi();
        t1.printStatus();
        t2.printStatus();

        t1.addPassengerTexi(2, "서울역", 2);
        t1.addOil(-80);
        t1.pay();
        t1.addPassengerTexi(5, "구로디지털단지역", 12);
        t1.addPassengerTexi(3, "구로디지털단지역", 12);
        t1.addOil(-20);
        t1.pay();

    }

}