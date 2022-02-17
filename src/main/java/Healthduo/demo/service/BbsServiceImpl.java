package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.repository.BbsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BbsServiceImpl implements BbsService  {
    private final BbsRepository bbsRepository;
    @Override
    public Page<Bbs> bbsList(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        pageable = PageRequest.of(page, 10,Sort.by(Sort.Direction.DESC,"bbsno"));

        return bbsRepository.findAll(pageable);
    }

    @Override
    public void bbsSave(Bbs bbs1) {
        log.info("bbsSave(Service start");
        bbsRepository.BbsSave(bbs1);

    }
}
