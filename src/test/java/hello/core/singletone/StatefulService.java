package hello.core.singletone;

public class StatefulService {
    /*
    private  int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제!
    }

    public int getPrice() {
        return price;
    }
    */
    // 따라서 스프링 빈은 아래처럼 항상 무상태로 설계해야한다!
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기가 문제!
        return price;
    }


}
