package hello2.studySpringMVC.web.frontcontroller.V3.controller;

import hello2.studySpringMVC.web.frontcontroller.ModelView;
import hello2.studySpringMVC.web.frontcontroller.V3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String ,String> paramMap){
        return new ModelView("new-form");
    }
}
