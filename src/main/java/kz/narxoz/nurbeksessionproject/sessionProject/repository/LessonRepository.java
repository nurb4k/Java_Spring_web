package kz.narxoz.nurbeksessionproject.sessionProject.repository;

import kz.narxoz.nurbeksessionproject.sessionProject.Model.Lesson;
import kz.narxoz.nurbeksessionproject.sessionProject.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository

public interface LessonRepository extends JpaRepository<Lesson,Long> {

}
