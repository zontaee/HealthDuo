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


@Component
@AllArgsConstructor
@Transactional
public class postConstruct {
        private  final RegionRepository regionRepository;
        private  final MemberRepository memberRepository;


    @PostConstruct
    public void init(){
        create();

    }
    public void create() {
        Member member = new Member("asd","123","남자","whdlsxo123@naver.com", LocalDate.now(),"010-2323-4343");
        Region region1 = new Region("경기도","성남시","분당구","서현동");
        Region region2 = new Region("경기도","성남시","분당구","분당동");
        Region region3 = new Region("서울특별시","강남구","서초동");
        Region region4 = new Region("서울특별시","강남구","반포동");
        regionRepository.save(region1);
        regionRepository.save(region2);
        regionRepository.save(region3);
        regionRepository.save(region4);
        memberRepository.save(member);
    }


}
