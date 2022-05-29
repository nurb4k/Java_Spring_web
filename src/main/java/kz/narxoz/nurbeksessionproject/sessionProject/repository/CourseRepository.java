package kz.narxoz.nurbeksessionproject.sessionProject.repository;

import kz.narxoz.nurbeksessionproject.sessionProject.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CourseRepository  extends JpaRepository<Course,Long> {
}
