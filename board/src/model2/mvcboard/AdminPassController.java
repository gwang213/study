package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fileupload.FileUtil;
import utils.BoardPage;
import utils.JSFunction;

@WebServlet("/mvcboard/adminpass.do")
public class AdminPassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	req.getRequestDispatcher("/MVCBoard/AdminPass.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	String adminpass = req.getParameter("adminpass");
    	String realpass = getServletContext().getInitParameter("adminpass");
    	if(adminpass.equals(realpass)) {
    		JSFunction.alertLocation(resp, "인증되었습니다.", "../mvcboard/adminlist.do");
    	} else {
    		JSFunction.alertBack(resp, "비밀번호 검증에 실패했습니다.");
    	}
    }
}
