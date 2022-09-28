<%@page import="dto.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.ProductRepository"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%
/* ProductRepository dao = new ProductRepository("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/WebMarketDB", "root", "1234"); */
ProductRepository dao = new ProductRepository();

Map<String, Object> param = new HashMap<String, Object>(); 
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");
if (searchWord != null) {
    param.put("searchField", searchField);
    param.put("searchWord", searchWord);
}

List<Product> productLists = dao.getAllProducts(param);
dao.close();
%>
<html>
<head>
<link rel="stylesheet" href="./resources/css/bootstrap.min.css" />
<title>상품 목록</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 목록</h1>
		</div>
	</div>
	<div class="container">
		<div class="row" align="center">
			<%
				for(Product dto : productLists)
				{
			%>
			<div class="col-md-4">
<%-- 			<img src="c:/upload/<%= dto.getFilename() %>" style="width: 100%"> --%>
				<img alt="" src="${pageContext.request.contextPath}/resources/images/<%= dto.getFilename() %>" style="width:100%; height:50%">
				<h3><%= dto.getPname() %></h3>
				<p><%= dto.getDescription() %>
				<p><%= dto.getUnitPrice() %>원
				<p><a href="./product.jsp?id=<%= dto.getProductId() %>"class="btn btn-secondary" role="button">상세 정보 &raquo;></a>
			</div>
			<%
				}
			%>
		</div>
		<hr>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
