package Healthduo.demo.service;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.repository.BbsRepository;
import Healthduo.demo.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberServiceImplTest {
    @Autowired
    BbsRepository bbsRepository;
    @Test
    void Bbssave() {
        for (int i= 0; i<102;i++) {
            Bbs bbs = new Bbs("아뇽"+ i, "내용" + i, String.valueOf(LocalDate.now()), 0, "1", "1");
            bbsRepository.save(bbs);
        }
    }

}