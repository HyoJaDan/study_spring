package hello2.studySpringMVC.web.frontcontroller.v4;

import hello2.studySpringMVC.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV4 {
    //ModelView process(Map<String ,String> paramMap);
    String process(Map<String ,String >paramMap, Map<String,Object>model);
}
