package spring_basic2.Membership2.member;

import spring_basic2.Membership2.enumeration.Grade;

public class Member {
    private long id;
    private String name;
    private spring_basic2.Membership2.enumeration.Grade Grade;

    public Member(long id, String name, Grade Grade) {
        this.id = id;
        this.name = name;
        this.Grade = Grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return Grade;
    }

    public void setGrade(Grade Grade) {
        this.Grade = Grade;
    }
}
