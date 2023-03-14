package spring_basic2.Membership2.lombok;


import lombok.Getter;
import lombok.Setter;

//자동으로 getter, setter 메서드를 완성해 사용가능
@Getter
@Setter
public class LombokTestClass {
    private String name;
    private int age;

    public static void main(String[] args) {
        LombokTestClass lombokTestClass = new LombokTestClass();

        lombokTestClass.setName("abc");
        lombokTestClass.setAge(12);

        System.out.println("lombokTestClass = " + lombokTestClass);
        System.out.println("name = " + lombokTestClass.getName());
        System.out.println("age = " + lombokTestClass.getAge());
    }
}
