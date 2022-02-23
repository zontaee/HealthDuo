package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import Healthduo.demo.dto.CommentDTO;

import java.util.List;

public interface CommentRestService {
    void CommentSave(String content, Bbs bbs, Member member);

    List<CommentDTO> commentFind(Long bbsNo);

    void commentDelete(int commentGroup);

    void childCommentSave(String content, Bbs bbs, Member member, int commentGroup);
}
