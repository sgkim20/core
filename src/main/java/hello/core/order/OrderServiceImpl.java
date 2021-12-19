package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

//    추상에도 의존하고 구현체에도 의존한다 -> DIP 위반
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

//    NPE(null pointer exception) 오류가 발생한다. Why? 변수에 아무값도 할당 안되어 있기 때문에
//    private DiscountPolicy discountPolicy;

//    final이 있으면 값이 무조건 할당되어야 한다.(기본? 생성자)
    private final DiscountPolicy discountPolicy;


    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // ConfiguraionSingletone 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
