package kz.narxoz.nurbeksessionproject.sessionProject.repository;

import kz.narxoz.nurbeksessionproject.sessionProject.Model.infoPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoPanelRepository extends JpaRepository<infoPanel,Long> {

}
