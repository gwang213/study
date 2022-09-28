<%@page import="java.util.Vector"%>
<%@page import="dto.Cart"%>
<%@page import="java.util.List"%>
<%@page import="dao.CartDAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="dto.Product"%>
<%@ page import="dao.ProductRepository"%>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<%
	String userId = session.getAttribute("sessionId").toString();
%>
<title>장바구니</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">장바구니</h1>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<table width="100%">
				<tr>
					<td align="left"><a href="./removeAllCart.jsp" class="btn btn-danger">삭제하기</a></td>
					<td align="right"><a href="./shippingInfo.jsp" class="btn btn-success">주문하기</a></td>
				</tr>
			</table>
		</div>
		<div style="padding-top: 50px">
			<table class="table table-hover">
				<tr>
					<th>상품</th>
					<th>가격</th>
					<th>수량</th>					
					<th>소계</th>
					<th>비고</th>
				</tr>
				<%				
					int sum = 0;
					CartDAO dao = new CartDAO();					
					List<Cart> cartList = dao.selectUserCart(userId); 
					dao.close();
					if (cartList == null)
						cartList = new Vector<Cart>();
					for (Cart cart : cartList) { // 상품리스트 하나씩 출력하기
						int total = Integer.parseInt(cart.getProductUnitPrice()) * Integer.parseInt(cart.getCartUnitsInStock());
						sum = sum + total;
				%>
				<tr>
				
				<td><%=cart.getProductId()%> - <%=cart.getProductName()%></td>
					<td><%=cart.getProductUnitPrice()%></td>
					<td><%=cart.getCartUnitsInStock()%></td>				
					<%-- <a href="./editCart.jsp?cartId=<%=cart.getCartId()%>" class="badge badge-danger">수정</a></td> --%>
					<td><%=total%></td>
					
					<td><a href="./removeCart.jsp?cartId=<%=cart.getCartId()%>" class="badge badge-danger">삭제</a></td>
				</tr>
				<%
					}
				%>
				<tr>
					<th></th>
					<th></th>
					<th>총액</th>
					<th><%=sum%></th>
					<th></th>
				</tr>
			</table>
			<a href="./products.jsp" class="btn btn-secondary"> &laquo; 쇼핑 계속하기</a>
		</div>
		<hr>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
