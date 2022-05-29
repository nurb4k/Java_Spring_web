package kz.narxoz.nurbeksessionproject.sessionProject.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "t_course" )

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "logo",columnDefinition = "TEXT")
    private String logo;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "info_course",length = 4000)
    private String info_course;




}
