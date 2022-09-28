<%@page import="common.JSFunction"%>
<%@page import="dao.CartDAO"%>
<%@page import="dto.Cart"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.Product"%>
<%@ page import="dao.ProductRepository"%>
<%
	String productId = request.getParameter("id");
	if (productId == null || productId.trim().equals("")) {
		response.sendRedirect("products.jsp");
		return;
	}
	/* Map<String, Object> param = new HashMap<String, Object>(); 
	ProductRepository dao = new ProductRepository();

	List<Product> goodsList = dao.getAllProducts(param);
	Product goods = new Product();
	for (int i = 0; i < goodsList.size(); i++) {
		goods = goodsList.get(i);
		if (goods.getProductId().equals(id)) { 			
			break;
		}
	}*/
	
	Cart cart = new Cart();
	String sessionId = session.getAttribute("sessionId").toString(); 
	cart.setId(sessionId);
	cart.setProductId(productId);
	String cartQuantity = request.getParameter("cartQuantity");
	cart.setCartUnitsInStock(cartQuantity);
	CartDAO dao = new CartDAO();
	dao.insertCart(cart);
	dao.close();
	
	JSFunction.alertBack(response, "장바구니에 상품이 추가되었습니다.");
	
%>