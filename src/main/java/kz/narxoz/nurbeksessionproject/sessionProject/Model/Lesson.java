package kz.narxoz.nurbeksessionproject.sessionProject.Model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_lesson")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "topic")
    private String topic;
    @Column (name = "video",columnDefinition = "TEXT")
    private String video;
    @ManyToOne
    private Course course;

}
