package hello2.studySpringMVC.web.frontcontroller.V3;

import hello2.studySpringMVC.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String ,String> paramMap);
}
