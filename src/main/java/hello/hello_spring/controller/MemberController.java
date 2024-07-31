package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // @Component : 컴포넌트 스캔 방식 , 같거나 하위 패키지에서만 스캔 가능
public class MemberController {

  private final MemberService memberService;

  //컨트롤러에서 서비스를 연결시킴
  @Autowired
  public MemberController(MemberService memberService) {
      this.memberService = memberService;
  }

  @GetMapping("/members/new")
    public String createForm() {
      return "members/createMemberForm";
  }

  @PostMapping("/members/new")
  public String create(MemberForm form) {
      Member member = new Member();
      member.setName(form.getName());

      memberService.join(member);

      return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model) {
      List<Member> members = memberService.findMembers();
      model.addAttribute("members", members);
      return "members/memberList";
  }

}
