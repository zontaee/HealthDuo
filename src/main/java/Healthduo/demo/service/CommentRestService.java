package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;

public interface CommentRestService {
    void CommentSave(String content, Bbs bbs, Member member);
}
