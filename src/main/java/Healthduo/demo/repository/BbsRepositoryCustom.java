package Healthduo.demo.repository;

import Healthduo.demo.domain.Bbs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BbsRepositoryCustom {
    Bbs BbsSave(Bbs bbs);
    Page<Bbs> findBySearch(String contents, Pageable pageable);
}
