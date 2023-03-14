package spring_basic2.Membership2.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring_basic2.Membership2.filter.FilterAutoDI;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;

//Component Scan이 가능하도록 수정
@FilterAutoDI
public class MemberServiceImplAutoDI implements MemberService{
    //변수
    private final MemberRepository memberRepository;

    @Autowired //의존관계 자동 주입 ==> 생성자가 1개이므로 생략 가능
    public MemberServiceImplAutoDI(MemberRepository memberRepository) {
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
