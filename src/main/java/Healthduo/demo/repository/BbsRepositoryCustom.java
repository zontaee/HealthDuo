package Healthduo.demo.repository;

import Healthduo.demo.domain.Bbs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BbsRepositoryCustom {
    Bbs BbsSave(Bbs bbs);
    Page<Bbs> findBySearchContent(String contents, Pageable pageable);
    Page<Bbs> findBySearchTitle(String title, Pageable pageable);
    Page<Bbs> findBySearchUserID(String UserID, Pageable pageable);
    List<Bbs> findNoticeBbsList();

    Page<Bbs> findAllAddress(Pageable pageable, String address);
}
