package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BbsService  {
    Page<Bbs> bbsList(Pageable pageable) throws Exception;

    void bbsSave(Bbs bbs1);
}
