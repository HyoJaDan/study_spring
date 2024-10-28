package sungho1.springCorePrinciple.order;

public class Order {
    private Long MemberId;
    private String itemName;
    private int itemPrice;
    private int DiscountPrice;

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.MemberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.DiscountPrice = discountPrice;
    }
    public int itemPrice() {
        return itemPrice-DiscountPrice;
    }
    public Long getMemberId() {
        return MemberId;
    }

    public void setMemberId(Long memberId) {
        MemberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        DiscountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "MemberId=" + MemberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", DiscountPrice=" + DiscountPrice +
                '}';
    }
}
