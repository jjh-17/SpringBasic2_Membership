package spring_basic2.Membership2.member.service;

import org.springframework.stereotype.Component;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;

@Component
public class MemberServiceImplDIPComponent implements MemberService{
    //변수
    private final MemberRepository memberRepository;

    public MemberServiceImplDIPComponent(MemberRepository memberRepository) {
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

    //ConfigurationTest에서 사용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
