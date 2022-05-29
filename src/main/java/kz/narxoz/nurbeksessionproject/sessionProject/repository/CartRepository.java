package kz.narxoz.nurbeksessionproject.sessionProject.repository;

import kz.narxoz.nurbeksessionproject.sessionProject.Model.Cart;
import kz.narxoz.nurbeksessionproject.sessionProject.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

}
