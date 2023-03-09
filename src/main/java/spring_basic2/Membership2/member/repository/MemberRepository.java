package spring_basic2.Membership2.member.repository;

import spring_basic2.Membership2.member.Member;

public interface MemberRepository {
    void save(Member member);       //회원 정보 저장
    Member findById(Long memberId); //회원 검색
}
