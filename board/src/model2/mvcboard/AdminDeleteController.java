package model2.mvcboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.JSFunction;

@WebServlet("/mvcboard/admindelete.do")
public class AdminDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    	
    	String idx = req.getParameter("idx");

    	MVCBoardDAO dao = new MVCBoardDAO();
        MVCBoardDTO dto = dao.selectView(idx);
        int result = dao.deletePost(idx);  // 게시물 삭제
        dao.close();
        
        JSFunction.alertLocation(resp, "삭제되었습니다.", "../mvcboard/adminlist.do"); 
        // 현재 삭제는 되는데 다시 관리자리스트 화면으로 돌아오지 못함

    }
}
