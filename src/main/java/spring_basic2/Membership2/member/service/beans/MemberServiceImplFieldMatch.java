package spring_basic2.Membership2.member.service.beans;

import lombok.RequiredArgsConstructor;
import spring_basic2.Membership2.filter.FilterFieldMatch;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.member.service.MemberService;

//AppConfigFieldMatch 만이 스캔하도록 필터링
@FilterFieldMatch
@RequiredArgsConstructor //final이 붙은 필드를 모아서 생성자 자동 생성
public class MemberServiceImplFieldMatch implements MemberService {
    //변수
    private final MemberRepository memberRepository;

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
