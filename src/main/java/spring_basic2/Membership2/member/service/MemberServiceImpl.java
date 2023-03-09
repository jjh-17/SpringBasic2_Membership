package spring_basic2.Membership2.member.service;

import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;
import spring_basic2.Membership2.member.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService{

    //변수
    private final MemberRepository memberRepository = new MemoryMemberRepository();

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
