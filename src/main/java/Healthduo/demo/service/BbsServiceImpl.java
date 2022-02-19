package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.dto.BbsDTO;
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
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BbsServiceImpl implements BbsService  {
    private final BbsRepository bbsRepository;
    @Override
    public Page<Bbs> bbsList(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 10,Sort.by(Sort.Direction.DESC,"bbsNo"));

        return bbsRepository.findAll(pageable);
    }

    @Override
    public void bbsSave(Bbs bbs) {
        log.info("bbsSave(Service start)");
        bbsRepository.BbsSave(bbs);

    }

    @Override
    public Optional<Bbs> findContent(Long bbsNo) {
        log.info("findContent(Service start)");
        Optional<Bbs> bbsContent = bbsRepository.findById(bbsNo);
        bbsContent.get().setBbsHit(bbsContent.get().getBbsHit() +1);
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
}
