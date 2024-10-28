package hello2.studySpringMVC.web.frontcontroller.V3;

import hello2.studySpringMVC.web.frontcontroller.ModelView;
import hello2.studySpringMVC.web.frontcontroller.MyView;
import hello2.studySpringMVC.web.frontcontroller.V3.controller.MemberFormControllerV3;
import hello2.studySpringMVC.web.frontcontroller.V3.controller.MemberListControllerV3;
import hello2.studySpringMVC.web.frontcontroller.V3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    // 1. 주소를 연결한다.
    private Map<String, ControllerV3> controllerMap = new HashMap<>();
    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //주소 받고 requestURI에 넣기
        String requestURI = request.getRequestURI();
        // 받은 주소를 위에서 등록한 controllerMap에서 찾기
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // request를 createParamMap을 통해 보낸다.
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        String viewName = mv.getViewName();

        // 아래 있는 MyView를 통해 컨트롤러가 반환한 논리 뷰 이름을 실제 물리 뷰 경로로 변경한다. 그리고 실제 물리 경로가 있는 MyView 객체를 반환한다.
        MyView view = viewResolver(viewName);
        view.render(mv.getModel(), request, response);
    }

    //HttpServletRequest에서 파라미터 정보를 꺼내서 Map으로 변환한다. 그리고 해당 Map(paramMap)을 컨트롤러에 전달하면서 호출한다.
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
        return paramMap;
    }
    //위에서 말한 myView
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");

    }
}