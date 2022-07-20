package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import Healthduo.demo.repository.BbsRepository;
import Healthduo.demo.repository.MemberRepository;
import Healthduo.demo.web.ServiceMethod;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ServiceMethod serviceMethod;
    private final MemberRepository memberRepository;

    @Override
    public Page<Bbs> bbsList(Pageable pageable) {
        log.info("bbsList(Service start)");
        pageable = serviceMethod.getPageableBbs(pageable);
        return bbsRepository.findAll(pageable);
    }


    @Override
    public Page<Bbs> bbsListSearch(Pageable pageable, String bbsListSearch, String searchText) {
        log.info("bbsListSearch(Service start)");
        pageable = serviceMethod.getPageableBbs(pageable);
        Page<Bbs> searchedBbsList = serviceMethod.resultBySearch(pageable, bbsListSearch, searchText);
        return searchedBbsList;
    }

    @Override
    public List<Bbs> noticeBbsList() {
        log.info("noticeBbsList(Service start)");
        List<Bbs> noticeBbsList = bbsRepository.findNoticeBbsList();
        return noticeBbsList;
    }

    @Override
    public Page<Bbs> bbsList(Pageable pageable, String address) {
        log.info("bbsList(Service start)");
        pageable = serviceMethod.getPageableBbs(pageable);
        return bbsRepository.findAllAddress(pageable, address);
    }

    @Override
    public void bbsSave(Bbs bbs, String street, String loginMember) {
        log.info("bbsSave(Service start)");
        Member member = memberRepository.findById(loginMember).orElse(null);
        serviceMethod.bbsSetting(bbs, street, member);
        bbsRepository.BbsSave(bbs);
    }

    @Override
    public Optional<Bbs> findContent(Long bbsNo) {
        log.info("findContent(Service start)");
        Optional<Bbs> bbsContent = bbsRepository.findById(bbsNo);
        serviceMethod.increaseHit(bbsContent);
        return bbsContent;
    }

    @Override
    public Optional<Bbs> findUpdatingContent(Long bbsNo) {
        log.info("findUpdatedContent(Service start)");
        Optional<Bbs> findUpdatingContent = bbsRepository.findById(bbsNo); //merge활용
        return findUpdatingContent;
    }

    @Override
    public Optional<Bbs> ContentUpdate(Bbs bbs) {
        log.info("ContentUpdate(Service start)");
        Optional<Bbs> findContentUpdate = bbsRepository.findById(bbs.getBbsNo()); //merge활용
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
