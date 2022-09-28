package dao;

import java.util.List;
import java.util.Vector;

import dto.Cart;
import mvc.database.DBConnection;

public class CartDAO extends DBConnection {

	public CartDAO() {
		super();
	}
	
	public List<Cart> selectUserCart(String sessionId) {
		List<Cart> cartLists = new Vector<>();
		
		String query = "SELECT C.*, P.p_name, P.p_unitPrice " 
                + " FROM Product P INNER JOIN cart C " 
                + " ON P.p_id=C.p_id "
                + " WHERE id=?";
		
		try {
			
			psmt = conn.prepareStatement(query);
			psmt.setString(1, sessionId);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Cart dto = new Cart();
				dto.setCartId(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setProductId(rs.getString(3));
				dto.setCartUnitsInStock(rs.getString(4));
				dto.setProductName(rs.getString(5));
				dto.setProductUnitPrice(rs.getString(6));
				
				cartLists.add(dto);
			}
		} catch (Exception e) {
			System.out.println("데이터베이스에서 예외 발생");
			e.printStackTrace();
		}
		
		return cartLists;
		
	}
	
    public int insertCart(Cart dto) {
        int result = 0;
        
        try {
            String query = "INSERT INTO cart ( "
                         + " id,p_id,c_unitsInStock) "
                         + " VALUES ( "
                         + " ?, ?, ?)";  

            psmt = conn.prepareStatement(query); 
            psmt.setString(1, dto.getId());
            psmt.setString(2, dto.getProductId());
            psmt.setString(3, dto.getCartUnitsInStock());
            
            result = psmt.executeUpdate(); 
        }
        catch (Exception e) {
            System.out.println("카트 추가 중 예외 발생");
            e.printStackTrace();
        }
        
        return result;
    }
    
    public int updateCart(Cart dto) {
        int result = 0;
        
        try {
            String query = "UPDATE cart SET "
                         + " c_unitInStock=? "
                         + " WHERE c_id=? ";  

            psmt = conn.prepareStatement(query); 
            psmt.setString(1, dto.getCartUnitsInStock());
            psmt.setString(2, dto.getCartId());            
            
            result = psmt.executeUpdate(); 
        }
        catch (Exception e) {
            System.out.println("카트 수정 중 예외 발생");
            e.printStackTrace();
        }
        
        return result;
    }   
	
    public int removeCart(String cartId) {
        int result = 0;
        
        try {
            String query = "DELETE FROM cart WHERE c_id=?";

            psmt = conn.prepareStatement(query); 
            psmt.setString(1, cartId);           
            result = psmt.executeUpdate(); 
        }
        catch (Exception e) {
            System.out.println("카트 삭제 중 예외 발생");
            e.printStackTrace();
        }
        
        return result;
    } 
    
    public int removeAllCart(String sessionId) {
        int result = 0;
        
        try {
            String query = "DELETE FROM cart WHERE id=?";

            psmt = conn.prepareStatement(query); 
            psmt.setString(1, sessionId);           
            result = psmt.executeUpdate(); 
        }
        catch (Exception e) {
            System.out.println("카트 전체 삭제 중 예외 발생");
            e.printStackTrace();
        }
        
        return result;
    }    
   
}
