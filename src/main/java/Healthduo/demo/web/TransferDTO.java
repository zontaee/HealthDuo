package Healthduo.demo.web;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.domain.Comment;
import Healthduo.demo.domain.Member;
import Healthduo.demo.dto.BbsDTO;
import Healthduo.demo.dto.CommentDTO;
import Healthduo.demo.dto.MemberDTO;
import Healthduo.demo.service.BbsService;
import Healthduo.demo.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class TransferDTO {

    private final BbsService bbsService;

    /**
     * bbs페이징 DTO 변환 메서드
     *
     * @param pageable
     * @param address
     * @return
     * @throws Exception
     */
    public Page<BbsDTO> BbsListPaging(Pageable pageable, String address) throws Exception {
        Page<Bbs> bbsList = bbsService.bbsList(pageable, address);
        Page<BbsDTO> bbsDTo = getBbsDTOS(bbsList, "BbsListPaging start");
        return bbsDTo;
    }

    /**
     * bbs페이징 DTO 변환 메서드
     *
     * @param pageable
     * @return
     * @throws Exception
     */

    public Page<BbsDTO> BbsListPaging(Pageable pageable) throws Exception {
        Page<Bbs> bbsList = bbsService.bbsList(pageable);
        Page<BbsDTO> bbsDTo = getBbsDTOS(bbsList, "BbsListPaging start");
        return bbsDTo;
    }

    /**
     * bbs검색후 페이징 DTO 변환 메서드
     *
     * @param pageable
     * @param bbsListSearch
     * @param searchText
     * @return
     * @throws Exception
     */
    public Page<BbsDTO> BbsListSerchPaging(Pageable pageable, String bbsListSearch, String searchText) throws Exception {
        Page<Bbs> bbsList = bbsService.bbsListSearch(pageable, bbsListSearch, searchText);
        Page<BbsDTO> bbsDTo = getBbsDTOS(bbsList, "BbsListSerchPaging start");

        return bbsDTo;
    }

    /**
     * bbs DTO 변환 메서드
     *
     * @param bbs
     * @return
     */
    public BbsDTO getBbsDTO(Optional<Bbs> bbs) {
        BbsDTO bbsDTO = getDto(bbs);
        return bbsDTO;
    }

    /**
     * bbs DTO 변환 메서드
     *
     * @return
     */
    public List<BbsDTO> getBbsDTO() {
        List<Bbs> bbsList = bbsService.noticeBbsList();
        List<BbsDTO> bbsdto = new ArrayList<>();
        for (Bbs bbs : bbsList) {

            bbsdto.add(new BbsDTO(bbs.getBbsNo(), bbs.getBbsTitle(), bbs.getBbsContent(), bbs.getBbsDate(), bbs.getBbsHit()
                    , bbs.getBbsNotice(), bbs.getBbsSecret(), bbs.getCheckNS(), bbs.getMember()));
        }
        return bbsdto;
    }

    public Member getMember(MemberDTO memberDTO) {
        Member member = new Member(memberDTO.getMemberId(), memberDTO.getMemberPassword(), memberDTO.getMemberSex(), memberDTO.getMemberEmail(), LocalDate.now(), memberDTO.getMemberPnumber());
        return member;
    }
    public void transferCommentDTO(List<CommentDTO> commentDTO, List<Comment> comments) {
        for (Comment comment : comments) {
            CommentDTO recommentDTO = new CommentDTO(comment.getCommentId(), comment.getContent(), comment.getCommentCnt(),
                    comment.getCommentGroup(), comment.getDate(), comment.getCommentSequence(), comment.getLevel(), comment.getMember().getMemberId());
            commentDTO.add(recommentDTO);
        }
    }




    private Page<BbsDTO> getBbsDTOS(Page<Bbs> bbsList, String BbsListPaging_start) {
        Page<BbsDTO> bbsDTo = bbsList.map(m -> new BbsDTO(m.getBbsNo(), m.getBbsTitle(), m.getBbsContent()
                , m.getBbsDate(), m.getBbsHit(), m.getBbsNotice(), m.getBbsSecret(), m.getCheckNS(), m.getMember()));
        log.info(BbsListPaging_start);
        log.info("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                bbsDTo.getTotalElements(), bbsDTo.getTotalPages(), bbsDTo.getSize(),
                bbsDTo.getNumber(), bbsDTo.getNumberOfElements());
        return bbsDTo;
    }


    private BbsDTO getDto(Optional<Bbs> bbs) {
        BbsDTO bbsDTO = new BbsDTO(bbs.get().getBbsNo(), bbs.get().getBbsTitle(), bbs.get().getBbsContent()
                , bbs.get().getBbsDate(), bbs.get().getBbsHit(), bbs.get().getBbsNotice(), bbs.get().getBbsSecret(), bbs.get().getCheckNS(), bbs.get().getMember());
        return bbsDTO;
    }





    /*public List<RegionDTO> getRegionDTO() {
        List<RegionDTO> regionDTO = new ArrayList<>();
        List<Region> regionImfo = regionService.getRegionInfo();
        for (Region region : regionImfo) {
            regionDTO.add(new RegionDTO(region.getRegion(),region.getCity(),region.getFullCity(),region.getStreet()));
        }
        return regionDTO;
    }*/
}
