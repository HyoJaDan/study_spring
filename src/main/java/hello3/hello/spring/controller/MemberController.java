
package hello3.hello.spring.controller;

import hello3.hello.spring.domain.Member;
import hello3.hello.spring.service.MemberServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //0이렇게 하면 하나만 다른 사용되지 않는 많은 기능들을 사용하기 때문에 사용안한다.
    // private final MemberServices memberServices = new MemberServices();

    //1 결국 딱 하나만 등록해서 한번에 관리하고 사용하면 된다.
    @Autowired
    private final MemberServices memberService;

    /* 2 생성자에 @AutoWired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에 찾아서 넝어준다.
    이렇게 객체 의존관계를 외부에서 넣어주는 것을 DI(Dependency Injection), 의존성 주입이라 한다. */
    public MemberController(MemberServices memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(memberForm form){
        Member member=new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members=memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}