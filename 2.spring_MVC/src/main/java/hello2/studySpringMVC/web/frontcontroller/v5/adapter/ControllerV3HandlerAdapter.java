package hello2.studySpringMVC.web.frontcontroller.v5.adapter;

import hello2.studySpringMVC.web.frontcontroller.ModelView;
import hello2.studySpringMVC.web.frontcontroller.V3.ControllerV3;
import hello2.studySpringMVC.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    // 1. 핸들러가 여기 연결할수 있는지 여부를 반환
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }
    // 2.유연하게 적용할수 있도록 Object handler을 파라미터에 넣음
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 3. 2번때문에 형변환이 필요
        ControllerV3 controller = (ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(request);
        //4. mv로(v3) 반환해야하기 때문에 mv로 받는다.
        ModelView mv = controller.process(paramMap);
        return mv;
    }
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }
}
