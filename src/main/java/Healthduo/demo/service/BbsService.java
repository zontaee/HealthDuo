package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BbsService  {
    Page<Bbs> bbsList(Pageable pageable) throws Exception;

    void bbsSave(Bbs bbs, String street, Member member);

    Optional<Bbs> findContent(Long bbsNo);

    Optional<Bbs> findUpdatingContent(Long bbsNo);


    Optional<Bbs> ContentUpdate(Bbs bbs);

    void deleteContent(Long bbsNo);

    Bbs bbsfindById(Long bbsNo);

    Page<Bbs> bbsListSearch(Pageable pageable, String bbsListSearch, String searchText);

    List<Bbs> noticeBbsList();

    Page<Bbs> bbsList(Pageable pageable, String address);
}
