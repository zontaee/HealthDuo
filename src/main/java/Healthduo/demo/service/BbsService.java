package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;

import java.util.List;

public interface BbsService  {
    List<Bbs> bbsList();

    void bbsSave(Bbs bbs1);
}
