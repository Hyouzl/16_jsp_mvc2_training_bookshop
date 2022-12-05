package bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bookshop.dto.CartDto;

public class CartDao {
	
	private CartDao() {}
	private static CartDao instance = new CartDao();
    public static CartDao getInstance() {
    	return instance;
    }
    
    private Connection conn			= null;
	private PreparedStatement pstmt = null;
	private ResultSet rs 		    = null;
   
    public void getConnection() {
    	
    	try {
    		Context initCtx = new InitialContext();
    		Context envCtx = (Context) initCtx.lookup("java:comp/env");
    		DataSource ds = (DataSource)envCtx.lookup("jdbc/pool/bookshop");
    		conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    public void getClose() {
    	  if (rs != null) 	try { rs.close(); } 	 catch(SQLException ex) {}
          if (pstmt != null)  try { pstmt.close(); } catch(SQLException ex) {}
          if (conn != null)  try { conn.close(); }   catch(SQLException ex) {}
    }
    
    
    public void insertCart(CartDto cart)  {
    	
        try {
        	
            getConnection();
            String sql = "insert into cart (book_id, buyer," +
            		"book_title,buy_price,buy_count,book_image) " +
            		"values (?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, cart.getBook_id());
            pstmt.setString(2, cart.getBuyer());
            pstmt.setString(3, cart.getBook_title());
            pstmt.setInt(4, cart.getBuy_price());
            pstmt.setInt(5, cart.getBuy_count());
            pstmt.setString(6, cart.getBook_image());
            
            pstmt.executeUpdate();
            
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
           getClose();
        }
        
    }
    
    
    public int getListCount(String id)  {
    	
        int x = 0;

        try {
        	
            getConnection();
            pstmt = conn.prepareStatement("select count(*) from cart where buyer=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
               x= rs.getInt(1);
			}
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
        	getClose();
        }
        
		return x;
		
    }

	 
     public List<CartDto> getCart(String id, int count) {
    	 
         List<CartDto> lists = new ArrayList<CartDto>(count);
         
         try {
        	 
        	 getConnection();
             pstmt = conn.prepareStatement("select * from cart where buyer = ?");
             pstmt.setString(1, id);
             rs = pstmt.executeQuery();
             
             while (rs.next()) {
            	 
            	 CartDto cart = new CartDto();
            	 cart.setCart_id(rs.getInt("cart_id"));
            	 cart.setBook_id(rs.getInt("book_id"));
            	 cart.setBook_title(rs.getString("book_title"));
            	 cart.setBuy_price(rs.getInt("buy_price"));
            	 cart.setBuy_count(rs.getByte("buy_count")); 
            	 cart.setBook_image(rs.getString("book_image"));
            	 lists.add(cart);
            	 
			 }
             
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             getClose();
         }
         
		 return lists;
		 
     }
   
     
     public void updateCount(int cart_id, byte count) {
    	
       
         try {
        	 
        	 getConnection();
             pstmt = conn.prepareStatement("update cart set buy_count=? where cart_id=?");
             pstmt.setByte(1, count);
             pstmt.setInt(2, cart_id);
             pstmt.executeUpdate();
             
         }catch(Exception ex) {
             ex.printStackTrace();
         }finally {
            getClose();
         }
         
     }
   
     
     public void deleteList(int cart_id) throws Exception {
        
         try {
        	 
			 getConnection();
             pstmt = conn.prepareStatement("delete from cart where cart_id=?");
             pstmt.setInt(1, cart_id);
             pstmt.executeUpdate();
             
         } catch (Exception ex) {
             ex.printStackTrace();
         } finally {            
        	 getClose();
         }
     }
     
     
     public void deleteAll(String id) throws Exception {
         
         try {
        	 
			 getConnection();
             pstmt = conn.prepareStatement("delete from cart where buyer=?");
             pstmt.setString(1, id);
             pstmt.executeUpdate();
             
         } catch (Exception ex) {
             ex.printStackTrace();
         } finally {
        	 getClose();
         }
         
     }
     
}
