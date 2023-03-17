package spring_basic2.Membership2.member.repository.beans;

import spring_basic2.Membership2.filter.FilterFieldMatch;
import spring_basic2.Membership2.member.Member;
import spring_basic2.Membership2.member.repository.MemberRepository;

import java.util.HashMap;
import java.util.Map;

@FilterFieldMatch
public class MemoryMemberRepositoryFieldMatch implements MemberRepository {

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
