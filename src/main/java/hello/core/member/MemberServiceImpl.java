package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 생성자를 통해서 추상화에만 의존하게 되었다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // ConfiguraionSingletone 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
