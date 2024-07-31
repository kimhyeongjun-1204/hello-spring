package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService  memberService;
    MemoryMemberRepository memberRepository;

    // 메소드 실행 전에 멤버 서비스에 DB 생성하기
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //저장소 초기화
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given => 주어졌을때
        Member member = new Member();
        member.setName("hello");

        //when => 이걸 실행했을때
        Long saveId = memberService.join(member);

        //then => 결과 도출
        Member findMember = memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when

        memberService.join(member1);
        /* 뒷 메소드 실행시 앞의 예외가 실행되어야함 */
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try {
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e) {
            org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/



        //then

    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }
}