package com.huy.automationexercise.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Tự động tạo Getter, Setter, equals, canEqual, hashCode, và toString
@AllArgsConstructor // Tạo Constructor cho tất cả các biến
@NoArgsConstructor  // Tạo Constructor rỗng
@Builder // Cho phép khởi tạo đối tượng theo kiểu chuỗi (Fluent API)
public class ProductModel {
    private String description;
    private int rawPrice;
    private int quantity;
    private int total;


}
