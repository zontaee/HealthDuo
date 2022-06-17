package Healthduo.demo.repository;

import Healthduo.demo.domain.Comment;
import Healthduo.demo.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,String> {
}
