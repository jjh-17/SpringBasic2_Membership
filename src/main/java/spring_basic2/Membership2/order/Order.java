package spring_basic2.Membership2.order;

public class Order {
    //변수
    private Long memberId;      //주문 회원 ID
    private String productName; //상품 이름
    private int productPrice;   //상품 가격
    private int discountPrice;  //고정 할인 가격

    //생성자

    public Order(Long memberId, String productName, int productPrice, int discountPrice) {
        this.memberId = memberId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.discountPrice = discountPrice;
    }

    //메서드
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
