<%@page import="common.JSFunction"%>
<%@page import="dao.CartDAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="dto.Product"%>
<%@ page import="dao.ProductRepository"%>
<%

	String userId = session.getAttribute("sessionId").toString();
	if (userId == null || userId.trim().equals("")) {
		response.sendRedirect("products.jsp");
		return;
	}
	
	CartDAO dao = new CartDAO();
	int result = dao.removeAllCart(userId);
	if(result >=1){
		JSFunction.alertLocation(response, "삭제 완료", "cart.jsp");
	} else {
		JSFunction.alertBack(response, "삭제에 실패하였습니다.");
	}	
%>
