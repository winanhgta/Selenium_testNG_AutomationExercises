package com.huy.automationexercise.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Create Getter, Setter, equals, canEqual, hashCode, and toString
@AllArgsConstructor // Create Constructor for all variables
@NoArgsConstructor  // Create empty Constructor
@Builder // Allow to create instance type string (Fluent API)
public class ProductModel {
    private String description;
    private int rawPrice;
    private int quantity;
    private int total;
}
