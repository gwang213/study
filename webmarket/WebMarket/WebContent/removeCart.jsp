<%@page import="common.JSFunction"%>
<%@page import="dao.CartDAO"%>
<%@page import="dto.Cart"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.Product"%>
<%@ page import="dao.ProductRepository"%>
<%
	String id = request.getParameter("cartId");
	if (id == null || id.trim().equals("")) {
		response.sendRedirect("products.jsp");
		return;
	}

	Cart dto = new Cart();
	CartDAO dao = new CartDAO();	
	int result = dao.removeCart(id);
	dao.close();
	if(result ==1){
		JSFunction.alertLocation(response, "삭제 완료", "cart.jsp");
	} else {
		JSFunction.alertBack(response, "삭제에 실패하였습니다.");
	}	
%>
