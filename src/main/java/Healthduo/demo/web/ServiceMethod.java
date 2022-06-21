package Healthduo.demo.web;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Member;
import Healthduo.demo.repository.BbsRepository;
import Healthduo.demo.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServiceMethod {
    private final BbsRepository bbsRepository;
    private final RegionRepository regionRepository;

    public Pageable getPageable(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "bbsNo"));
        return pageable;
    }

    public Page<Bbs> resultBySearch(Pageable pageable, String bbsListSearch, String searchText, Page<Bbs> searchedBbsList) {
        switch (bbsListSearch) {
            case "bbsTitle":
                searchedBbsList = bbsRepository.findBySearchTitle(searchText, pageable);
                break;
            case "bbsContent":
                searchedBbsList = bbsRepository.findBySearchContent(searchText, pageable);
                break;
            case "userID":
                searchedBbsList = bbsRepository.findBySearchUserID(searchText, pageable);
                break;
        }
        return searchedBbsList;
    }

    public void bbsLnitialization(Bbs bbs, String street, Member member) {
        bbs.setMember(member);
        bbs.setAddress(street);
        bbs.setBbsDate(String.valueOf(LocalDate.now()));
        bbs.setBbsHit(0);
        bbs.setCheckNS(" ");
        if (bbs.getBbsNotice()) {
            bbs.noticeAddCheck();
        }
        if (bbs.getBbsSecret()) {
            bbs.secretAddCheck();

        }
    }

    public void increaseHit(Optional<Bbs> bbsContent) {
        bbsContent.get().setBbsHit(bbsContent.get().getBbsHit() + 1);
    }

    public int loginCheck(Member member, Member loginCheck) {
        int loginCheckResult;
        if (loginCheck == null) {
            loginCheckResult = 1;
            return loginCheckResult;
        } else {
            if (loginCheck.getMemberPassword().equals(member.getMemberPassword())) {
                loginCheckResult = 2;
                return loginCheckResult;
            } else {
                loginCheckResult = 3;
                return loginCheckResult;
            }
        }
    }
           //loginCheck = 1 등록된 아이디가 없습니다.
           //loginCheck = 2 아이디 비밀번호 둘다 일치
           //loginCheck = 3 등록된 비밀번호가 틀렸습니다.


    public int CheckIdNumber(Member findId) {
        int checkIdNumber;
        if (findId == null) {
            checkIdNumber = 0; //중복회원x
        } else {
            checkIdNumber = 1; //중복회원o
        }
        return checkIdNumber;
    }

    public List<String> distinguishRegion(String region) {
        if(region.equals("서울특별시")) {
            List<String> fullCity = regionRepository.findFullCity(region);
            return fullCity;
        }else {
            List<String> citys = regionRepository.findCity(region);
            return citys;
        }
    }
}

