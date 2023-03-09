package spring_basic2.Membership2.member;

public interface MemberService {
    void join(Member member);           //회원 가입
    Member findMember(Long memberId);   //회원 탐색
}
