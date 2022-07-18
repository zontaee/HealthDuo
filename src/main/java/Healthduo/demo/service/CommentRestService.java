package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.dto.CommentDTO;

import java.util.List;

public interface CommentRestService {
    void CommentSave(String content, Bbs bbs, String member);

    List<CommentDTO> commentFind(Long bbsNo);

    void commentDelete(int commentGroup, int commentSequence);

    void childCommentSave(String content, Bbs bbs, String member, String childinfo, int seq);


}
