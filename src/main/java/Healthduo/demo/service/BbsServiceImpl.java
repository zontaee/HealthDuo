package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.repository.BbsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BbsServiceImpl implements BbsService {
    private final BbsRepository bbsRepository;
    @Override
    public List<Bbs> bbsList() {
        log.info("bbsList(Service start");
        List<Bbs> bbsList = bbsRepository.findAll();

        return bbsList;
    }

    @Override
    public void bbsSave(Bbs bbs1) {
        log.info("bbsSave(Service start");
        bbsRepository.BbsSave(bbs1);

    }
}
