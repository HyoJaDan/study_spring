package hello2.studySpringMVC.web.frontcontroller.v2;

import hello2.studySpringMVC.web.frontcontroller.MyView;
import hello2.studySpringMVC.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello2.studySpringMVC.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello2.studySpringMVC.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 1. 마지막에 있는 *는 v1 하위 모든 요청은 이 서블릿에서 받아들인다.
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    // 2 key : 매핑 URL, value : 호출될 컨트롤러. 여기서 모든 컨트롤러들을 연결시켜줬다.
     private Map<String, ControllerV2> controllerMap = new HashMap<>();

     // 3. 컨트롤러들을 매핑
     public FrontControllerServletV2() {
         controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
         controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
         controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
     }
     @Override
     protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         System.out.println("FrontControllerServletV1.service");

         // 4. request.getRequestURI로 현재 페이지의 위치를 받는다.
         String requestURI = request.getRequestURI();

         // 5. 다형성으로, controllerMap의(requestURI)를 통해 실제 조회할 컨트롤러들을 찾는다. 위에서 매핑한 정보를 new 한걸 controller에 가져온다.
         ControllerV2 controller = controllerMap.get(requestURI);
         if (controller == null) {
             response.setStatus(HttpServletResponse.SC_NOT_FOUND);
             return;
         }

         // 6. controller에 가져온거 실행.
         MyView view = controller.process(request,response);
         view.render(request,response);
     }
}