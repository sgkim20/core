package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// Configuration 어노테이션을 제거하고 configurationDeep() 테스트를 실행해서 Bean의 클래스명과 memberRepository 호출 수를 확인해보자
@Configuration
public class AppConfig {
    // AppConfig에서는 역할(인터페이스)과 구현(인터페이스를 구현한 클래스, 구현 객체)을 한눈에 확인 할 수 있어야 한다.
    // AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을
    // IoC 컨테이너 또는 DI 컨테이너라 한다.
    // 의존관계 주입에 초점을 맞추어 최근에는 주로 DI 컨테이너라 한다.
    // 또는 어샘블러, 오브젝트 팩토리 등으로 불리기도 한다

    // @Bean memberService -> new MeMemoryMemberRepository()
    // @Bean orderService -> new MeMemoryMemberRepository()
    // 싱글톤이 깨지는것이 아닌가? 라고 의심을 해야 정상이다 -> 고민이 되면 ? -> 테스트 코드로 확인해보자
    //
    // 테스트 예상
    // Call AppConfig.memberService
    // Call AppConfig.memberRepository
    // Call AppConfig.memberRepository
    // Call AppConfig.orderService
    // Call AppConfig.memberRepository
    //
    // 테스트 결과
    // Call AppConfig.memberService
    // Call AppConfig.memberRepository
    // Call AppConfig.orderService
    //
    // ... 스프링이 어떤 방법으로 싱글톤을 보장해주는 것일까?

    @Bean
    public MemberService memberService(){
        System.out.println("Call AppConfig.memberService");

        // 생성자 주입(생성자를 통해서 객체 주입)
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
