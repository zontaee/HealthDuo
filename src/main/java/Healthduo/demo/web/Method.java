package Healthduo.demo.web;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.dto.BbsDTO;
import Healthduo.demo.service.BbsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class Method {

    private final BbsService bbsService;

    public Page<BbsDTO> BbsListPaging(Pageable pageable) throws Exception {
        Page<Bbs> bbsList = bbsService.bbsList(pageable);
        Page<BbsDTO> bbsDTo =bbsList.map(m-> new BbsDTO(m.getBbsNo(),m.getBbsTitle(),m.getBbsContent()
                ,m.getBbsDate(),m.getBbsHit(),m.getBbsNotice(),m.getBbsSecret(),m.getMember()));
        log.info("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                bbsDTo.getTotalElements(), bbsDTo.getTotalPages(), bbsDTo.getSize(),
                bbsDTo.getNumber(), bbsDTo.getNumberOfElements());

        return bbsDTo;
    }
    public BbsDTO getBbsDTO(Optional<Bbs> bbs) {
        BbsDTO bbsDTO = new BbsDTO(bbs.get().getBbsNo(), bbs.get().getBbsTitle(), bbs.get().getBbsContent()
                , bbs.get().getBbsDate(), bbs.get().getBbsHit(), bbs.get().getBbsNotice(), bbs.get().getBbsSecret(),bbs.get().getMember());
        return bbsDTO;
    }
}
