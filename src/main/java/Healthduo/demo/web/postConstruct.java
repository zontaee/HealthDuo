package Healthduo.demo.web;

import Healthduo.demo.domain.Member;
import Healthduo.demo.domain.Region;
import Healthduo.demo.repository.MemberRepository;
import Healthduo.demo.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
@AllArgsConstructor
@Transactional
public class postConstruct {
    private final RegionRepository regionRepository;
    private final MemberRepository memberRepository;


    @PostConstruct
    public void init() {
        create();

    }

    public void create() {

        ArrayList<Region> addRegion = new ArrayList<>();
        Member member = new Member("asd", "123", "남자", "whdlsxo123@naver.com", LocalDate.now(), "010-2323-4343");
        Member member2 = new Member("test", "123", "남자", "whdlsxo123@naver.com", LocalDate.now(), "010-2323-4343");
        addRegion.add(new Region("경기도", "성남시", "분당구", "서현동"));
        addRegion.add(new Region("경기도", "성남시", "분당구", "분당동"));
        addRegion.add(new Region("경기도", "성남시", "분당구", "수내동"));
        addRegion.add(new Region("경기도", "성남시", "분당구", "정자동"));
        addRegion.add(new Region("경기도", "성남시", "분당구", "구미동"));
        addRegion.add(new Region("경기도", "성남시", "분당구", "백현동"));
        addRegion.add(new Region("경기도", "성남시", "분당구", "판교동"));
        addRegion.add(new Region("경기도", "성남시", "분당구", "이매동"));
        addRegion.add(new Region("경기도", "성남시", "수정구", "수정동"));
        addRegion.add(new Region("경기도", "성남시", "중원구", "중원동"));
        addRegion.add(new Region("경기도", "하남시", "하남구", "하남동"));
        addRegion.add(new Region("경기도", "안양시", "안양구", "평촌동"));
        addRegion.add(new Region("서울특별시",  "강남구", "서초동"));
        addRegion.add(new Region("서울특별시",  "강남구", "반포동"));
        addRegion.add(new Region("인천특별시", "중원구", "분당구", "인천동"));
        addRegion.add(new Region("강원도", "중원구", "분당구", "속초동"));
        addRegion.add(new Region("충청북도", "중원구", "수정구", "충정동"));
        addRegion.add(new Region("충청남도", "중원구", "분당구", "대전동"));

        for(int i =0; i<addRegion.size(); i++){
            regionRepository.save(addRegion.get(i));
        }
        memberRepository.save(member);
        memberRepository.save(member2);
    }


}
