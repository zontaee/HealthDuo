package Healthduo.demo.repository;

import Healthduo.demo.domain.Bbs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface BbsRepository extends JpaRepository<Bbs,Long> ,BbsRepositoryCustom {



}
