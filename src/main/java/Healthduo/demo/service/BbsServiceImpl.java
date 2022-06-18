package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import Healthduo.demo.repository.BbsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BbsServiceImpl implements BbsService {
    private final BbsRepository bbsRepository;

    @Override
    public Page<Bbs> bbsList(Pageable pageable) {
        log.info("bbsList(Service start)");
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "bbsNo"));

        return bbsRepository.findAll(pageable);
    }

    @Override
    public Page<Bbs> bbsListSearch(Pageable pageable, String bbsListSearch, String searchText) {
        log.info("bbsListSearch(Service start)");
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "bbsNo"));
        Page<Bbs> bySearch = bbsRepository.findBySearchContent(searchText, pageable);
        switch (bbsListSearch) {
            case "bbsTitle":
                bySearch = bbsRepository.findBySearchTitle(searchText, pageable);
                break;
            case "bbsContent":
                bySearch = bbsRepository.findBySearchContent(searchText, pageable);
                break;
            case "userID":
                bySearch = bbsRepository.findBySearchUserID(searchText, pageable);
                break;

        }
        return bySearch;
    }

    @Override
    public List<Bbs> noticeBbsList() {
        log.info("noticeBbsList(Service start)");
        List<Bbs> noticeBbsList = bbsRepository.findNoticeBbsList();

        return noticeBbsList;
    }

    @Override
    public void bbsSave(Bbs bbs, String street, Member member) {
        log.info("bbsSave(Service start)");
        bbs.setMember(member);
        bbs.setAddress(street);
        bbs.setBbsDate(String.valueOf(LocalDate.now()));
        bbs.setBbsHit(0);
        bbs.setCheckNS(" ");

        if(bbs.getBbsNotice()){
            bbs.noticeAddCheck();
        }
        if(bbs.getBbsSecret()){
            bbs.secretAddCheck();

        }
        bbsRepository.BbsSave(bbs);

    }

    @Override
    public Optional<Bbs> findContent(Long bbsNo) {
        log.info("findContent(Service start)");
        Optional<Bbs> bbsContent = bbsRepository.findById(bbsNo);
        bbsContent.get().setBbsHit(bbsContent.get().getBbsHit() + 1);
        return bbsContent;
    }

    @Override
    public Optional<Bbs> findContentUpdate(Long bbsNo) {
        log.info("updateContent(Service start)");
        Optional<Bbs> findContentUpdate = bbsRepository.findById(bbsNo);
        return findContentUpdate;
    }

    @Override
    public Optional<Bbs> ContentUpdate(Bbs bbs) {
        log.info("ContentUpdate(Service start)");
        Optional<Bbs> findContentUpdate = bbsRepository.findById(bbs.getBbsNo());
        findContentUpdate.get().updateBbs(bbs.getBbsNo(), bbs.getBbsTitle(), bbs.getBbsContent(), String.valueOf(LocalDate.now())
                , bbs.getBbsHit(), bbs.getBbsNotice(), bbs.getBbsSecret());
        return findContentUpdate;
    }

    @Override
    public void deleteContent(Long bbsNo) {
        log.info("deleteContent(Service start)");
        bbsRepository.deleteById(bbsNo);
    }

    @Override
    public Bbs bbsfindById(Long bbsNo) {
        log.info("bbsfindById(Service start)");
        Optional<Bbs> bbsFinded = bbsRepository.findById(bbsNo);
        Bbs bbs = bbsFinded.get();
        return bbs;
    }


}
