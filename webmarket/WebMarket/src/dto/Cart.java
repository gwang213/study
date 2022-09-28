package dto;

public class Cart {
	private String cartId;
	private String id;
	private String productId;
	private String cartUnitsInStock;
	private String productName;
	private String productUnitPrice;

	
	public String getCartId() {
		return cartId;
	}
	public String getId() {
		return id;
	}
	public String getProductId() {
		return productId;
	}
	public String getCartUnitsInStock() {
		return cartUnitsInStock;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductUnitPrice() {
		return productUnitPrice;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setCartUnitsInStock(String cartUnitsInStock) {
		this.cartUnitsInStock = cartUnitsInStock;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductUnitPrice(String productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}
	
	
}
