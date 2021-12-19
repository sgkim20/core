package hello.core.singletone;

public class SingletonService {

    // 싱글톤 패턴을 구현하는 방법은 여러가지가 있다. 아래 방법은 객체를 미리 생성해두는 가장 단순하고 안전한 방법이다.

    // 1. 클래스가 메모리에 올라갈때(자바가 실행될 때) static 영역에 객체 instance를 하나 생성해서 올려둔다.
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 설정하여 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance()
    {
        return instance;
    }

    // 3. 생성자를 private로 선언해서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 방지한다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
