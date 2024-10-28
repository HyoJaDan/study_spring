package hello2.studySpringMVC.web.frontcontroller.v5;


import hello2.studySpringMVC.web.frontcontroller.ModelView;
import hello2.studySpringMVC.web.frontcontroller.MyView;
import hello2.studySpringMVC.web.frontcontroller.V3.controller.MemberFormControllerV3;
import hello2.studySpringMVC.web.frontcontroller.V3.controller.MemberListControllerV3;
import hello2.studySpringMVC.web.frontcontroller.V3.controller.MemberSaveControllerV3;
import hello2.studySpringMVC.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello2.studySpringMVC.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello2.studySpringMVC.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello2.studySpringMVC.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello2.studySpringMVC.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    // 1. 이전이랑 다르게 모든 Controller을 담아야 하기 때문에 Object 형식으로 함.(그림의 1. 핸들러 매핑 정보를 위해 만듬)
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    // 4. 다양한 어뎁터를 담고 찾기위해 만듬. 앞에서 만든 ControllerV3HandlerAdapter을 담음(그림의 2. 핸들러 어댑터 목록을 위해 만듬)

    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
    // 2,5
    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }
    //3. 컨트롤러 매핑(그림의 1. 핸들러 매핑 정보에서 필요한 데이터를 저장)
    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }
    // 6. 앞에서 만든 ControllerV3HandlerAdapter을 담음. 추가 가능. OCP (그림의 2. 핸들러 어댑터 목록에 필요한 데이터 저장)
    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }
    // 7. service, 즉 요청이 오면
    @Override
     protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //8
        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 10 (그림의 2. 핸들러 어뎁터 목록)에서 핸틀러를 처리할 수 있는 핸들러 어댑터 조회
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        // 13. 그림의 3번 handle(handler)을 핸들러 어댑터(그림의 3.)보내면. 핸들러 컨트롤러 갔다가 ModelView 반환
        ModelView mv = adapter.handle(request, response, handler);
        // 14. viewResolver로 MyView반환.
        MyView view = viewResolver(mv.getViewName());
        // 15 렌더.
        view.render(mv.getModel(), request, response);
     }
     // 9. 위에 등록한 handlerMappingMap에서 request.uri을 찾는다.  (그림의 1. 핸들러 매핑 정보 부분에서 핸들러 조회.)
     private Object getHandler(HttpServletRequest request) {
         String requestURI = request.getRequestURI();
         return handlerMappingMap.get(requestURI);
    }
    // 11. 순환하며 조회. 이때 6번에서 추가했던 어뎁터를 반환
     private MyHandlerAdapter getHandlerAdapter(Object handler) {
         for (MyHandlerAdapter adapter : handlerAdapters) {
             // 12. 여기서 찾음. supports는 return (이 핸들러가 instance of v3) 의 기능을 함.
             if (adapter.supports(handler)) {
                 return adapter;
             }
         }
         throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }
     private MyView viewResolver(String viewName) {
         return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
