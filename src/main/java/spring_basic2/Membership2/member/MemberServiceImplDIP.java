package spring_basic2.Membership2.member;

//MemberServiceImpl을 DIP 준수하도록 수정
public class MemberServiceImplDIP implements MemberService{
    //변수
    private final MemberRepository memberRepository;

    public MemberServiceImplDIP(MemberRepository memberRepository) {
        //MemberRepository의 구현체 중 하나로 초기화 ==> DIP 준수
        this.memberRepository = memberRepository;
    }

    //메서드
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
