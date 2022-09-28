package dao;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import dto.Product;
import mvc.database.DBConnection;

public class ProductRepository extends DBConnection {
    public ProductRepository() {
        super();
    }
    
	

	public List<Product> getAllProducts(Map<String, Object> map) { 
        List<Product> productList = new Vector<>();

        String query = "SELECT * FROM product "; 
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        query += " ORDER BY p_id DESC "; 

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Product dto = new Product(); 

                dto.setProductId(rs.getString("p_id"));
                dto.setPname(rs.getString("p_name"));
                dto.setUnitPrice(rs.getInt("p_unitPrice"));
                dto.setDescription(rs.getString("p_description"));
                dto.setCategory(rs.getString("p_category"));
                dto.setManufacturer(rs.getString("p_manufacturer"));
                dto.setUnitsInStock(rs.getLong("p_unitsInStock"));
                dto.setCondition(rs.getString("p_condition"));
                dto.setFilename(rs.getString("p_fileName"));
//                dto.setQuantity(rs.getInt("quantity"));
                

                productList.add(dto);
            }
        } 
        catch (Exception e) {
            System.out.println("예외 발생");
            e.printStackTrace();
        }

        return productList;
    }
	
	
	public Product getProductById(String productId) { 
        Product dto = new Product();
        
        String query = "SELECT * FROM product WHERE p_id=? "; 

        try {
            psmt = conn.prepareStatement(query);
            psmt.setString(1, productId); 
            rs = psmt.executeQuery(); 

            if (rs.next()) {
                dto.setProductId(rs.getString(1));
                dto.setPname(rs.getString(2));
                dto.setUnitPrice(rs.getInt(3));
                dto.setDescription(rs.getString(4));
                dto.setCategory(rs.getString(5));
                dto.setManufacturer(rs.getString(6));
                dto.setUnitsInStock(rs.getLong(7));
                dto.setCondition(rs.getString(8));
                dto.setFilename(rs.getString(9));
                
            }
        } 
        catch (Exception e) {
            System.out.println("상세보기 중 예외 발생");
            e.printStackTrace();
        }
        
        return dto; 
    }	
	
	
	
	
//	public Product getProductById(String productId) {
//		Product productById = null;
//
//		for (int i = 0; i < listOfProducts.size(); i++) {
//			Product product = listOfProducts.get(i);
//			if (product != null && product.getProductId() != null && product.getProductId().equals(productId)) {
//				productById = product;
//				break;
//			}
//		}
//		return productById;
//	}
//	
//	public void addProduct(Product product) {
//		listOfProducts.add(product);
//	}
}
