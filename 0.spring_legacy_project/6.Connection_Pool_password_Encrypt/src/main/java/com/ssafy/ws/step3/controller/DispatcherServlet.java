package com.ssafy.ws.step3.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.ws.step3.dto.Book;
import com.ssafy.ws.step3.dto.User;
import com.ssafy.ws.step3.service.BookService;
import com.ssafy.ws.step3.service.BookServiceImpl;
import com.ssafy.ws.step3.service.UserService;
import com.ssafy.ws.step3.service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@WebServlet(urlPatterns = {"/books","/users"},
            initParams = {
            		@WebInitParam(name="config", value="/WEB-INF/appServlet/servlet-context.properties")
            })
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

 
    /**
     * HandlerMapping 호출 - properties parsing
     */
	@Override
	public void init() throws ServletException {
		String configPath = getServletContext().getRealPath(getInitParameter("config"));
		HandlerMapping.mapping(configPath);
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        process(request, response);
    }

    /**
     * post 방식의 요청에 대해 응답하는 메서드이다.
     * front controller pattern을 적용하기 위해 내부적으로 process를 호출한다.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        process(request, response);
    }

    /**
     * request 객체에서 action 파라미터를 추출해서 실제 비지니스 로직 메서드(ex: doRegist)
     * 호출해준다.
     */
    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String path="/index.jsp";

        Controller controller = HandlerMapping.getController(action);
        path = controller.execute(request, response);
        
        request.getRequestDispatcher(path).forward(request, response);
    }


}
