package spring_basic2.Membership2.member.service;

import spring_basic2.Membership2.member.Member;

public interface MemberService {
    void join(Member member);           //회원 가입
    Member findMember(Long memberId);   //회원 탐색
}
