package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.dto.BbsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BbsService  {
    Page<Bbs> bbsList(Pageable pageable) throws Exception;

    void bbsSave(Bbs bbs);

    Optional<Bbs> findContent(Long bbsNo);

    Optional<Bbs> findContentUpdate(Long bbsNo);


    Optional<Bbs> ContentUpdate(Bbs bbs);

    void deleteContent(Long bbsNo);

    Bbs bbsfindById(Long bbsNo);
}
