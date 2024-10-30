package hello2.studySpringMVC.web.frontcontroller.V3.controller;

import hello2.studySpringMVC.domain.member.Member;
import hello2.studySpringMVC.domain.member.MemberRepository;
import hello2.studySpringMVC.web.frontcontroller.ModelView;
import hello2.studySpringMVC.web.frontcontroller.V3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository= MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String ,String> paramMap){
        List<Member> members=memberRepository.findAll();

        ModelView mv = new ModelView("members");
        mv.getModel().put("members",members);

        return mv;
    }
}
