package vn.techmaster.hw.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    String id;
    String name;
    String description;
    int price;
    String brand;
}
