package spring_basic2.Membership2.member.repository.beans;

import spring_basic2.Membership2.filter.FilterQualifier;
import spring_basic2.Membership2.filter.FilterQualifier2;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;

import java.util.HashMap;
import java.util.Map;

//AppConfigQualifier 만이 스캔하도록 필터링
@FilterQualifier2
public class MemoryMemberRepositoryQualifier2 implements MemberRepository {

    //변수
    private static Map<Long, Member> store = new HashMap<>();


    //메서드
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
