package kz.narxoz.nurbeksessionproject.sessionProject.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table( name = "t_cart" )

@Data

@AllArgsConstructor
@NoArgsConstructor

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long c_id;

    @Column(name = "cart_name")
    private String c_name;

    @Column(name = "cart_price")
    private int c_price;

    @Column(name = "c_image",columnDefinition = "TEXT")
    private String c_image;

}
