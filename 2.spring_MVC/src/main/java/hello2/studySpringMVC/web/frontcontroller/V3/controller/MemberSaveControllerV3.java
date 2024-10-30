package hello2.studySpringMVC.web.frontcontroller.V3.controller;

import hello2.studySpringMVC.domain.member.Member;
import hello2.studySpringMVC.domain.member.MemberRepository;
import hello2.studySpringMVC.web.frontcontroller.ModelView;
import hello2.studySpringMVC.web.frontcontroller.MyView;
import hello2.studySpringMVC.web.frontcontroller.V3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String ,String> paramMap){
        String username = paramMap.get("userName");
        int age=Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관한다.
        ModelView mv=new ModelView("save-result");
        mv.getModel().put("member",member);
        return mv;
    }
}
