package kz.narxoz.nurbeksessionproject.sessionProject.Model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table( name = "t_infoPanel" )


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class infoPanel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String name;


    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Column(name = "image",columnDefinition = "TEXT")
    private String image;


}
