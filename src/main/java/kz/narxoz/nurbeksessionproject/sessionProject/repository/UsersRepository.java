package kz.narxoz.nurbeksessionproject.sessionProject.repository;

import kz.narxoz.nurbeksessionproject.sessionProject.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);

}
