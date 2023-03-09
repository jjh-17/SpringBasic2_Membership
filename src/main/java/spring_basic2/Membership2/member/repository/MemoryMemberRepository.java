package spring_basic2.Membership2.member.repository;

import spring_basic2.Membership2.member.Member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

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
