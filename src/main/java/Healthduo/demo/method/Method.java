package Healthduo.demo.method;

import Healthduo.demo.domain.Bbs;
import Healthduo.demo.dto.BbsDTO;
import Healthduo.demo.service.BbsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Component
public class Method {

    private final BbsService bbsService;

  /*  public Page<BbsDTO> BbsListPaging(){
        log.info("BbsList(Method start");
        Page<Bbs> bbs = bbsService.bbsList();
        List<BbsDTO> bbsDTO = new ArrayList<>();
        for (Bbs bbschange : bbs) {
            BbsDTO bbsDTOAdd = new BbsDTO(bbschange.getBbs_no(),bbschange.getBbs_title(),
                    bbschange.getBbs_content(),bbschange.getBbs_date(),bbschange.getBbs_hit(),
                    bbschange.getBbs_notice(),bbschange.getBbs_secret());
            bbsDTO.add(bbsDTOAdd);
        }
        return null;
    }*/
}
