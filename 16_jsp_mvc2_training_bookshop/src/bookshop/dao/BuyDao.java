package bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bookshop.dto.BuyDto;
import bookshop.dto.CartDto;

public class BuyDao {
	
	private BuyDao() {}
    private static BuyDao instance = new BuyDao();
	public static BuyDao getInstance() {
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
    
    
    public List<String> getAccount(){
    	
        List<String> accountList = null;
        
        try {
        	
            getConnection();
            pstmt = conn.prepareStatement("select * from bank");
            rs = pstmt.executeQuery();
            
            accountList = new ArrayList<String>();
            
            while (rs.next()) {
           	  String account = rs.getString("account") + " " + rs.getString("bank") + " " + rs.getString("name");
           	  accountList.add(account);
		    }
            
        }catch(Exception e) {
        	e.printStackTrace();
        } finally {
        	getClose();
        }
        
        return accountList;
        
    }
    
    @SuppressWarnings("resource")
	public void insertBuy( List<CartDto> lists,String id, String account, String deliveryName, String deliveryTel,String deliveryAdress) throws Exception {
    	
        Timestamp reg_date 		= null;
        String sql = "";
        String maxDate =" ";
        String number = "";
        String todayDate = "";
        String compareDate = "";
       
        long buyId = 0;       
        short nowCount ;
        
        try {
        	
            getConnection();
            reg_date = new Timestamp(System.currentTimeMillis());
            todayDate = reg_date.toString();
            compareDate = todayDate.substring(0, 4) + todayDate.substring(5, 7) + todayDate.substring(8, 10);
            
            pstmt = conn.prepareStatement("select max(buy_id) from buy");
            
            rs = pstmt.executeQuery();
            rs.next();
            if (rs.getLong(1) > 0){         
            	Long val = new Long(rs.getLong(1));
                maxDate = val.toString().substring(0, 8);
                number =  val.toString().substring(8);
                if (compareDate.equals(maxDate)){
                	if ((Integer.parseInt(number)+1)<10000)
                	    buyId = Long.parseLong(maxDate + (Integer.parseInt(number)+1+10000));
                	else
                		buyId = Long.parseLong(maxDate + (Integer.parseInt(number)+1));
                }
                else{
                	compareDate += "00001";
        		    buyId = Long.parseLong(compareDate);
                }
            }
            else {
            	compareDate += "00001";
    		    buyId = Long.parseLong(compareDate);
            }
            
            conn.setAutoCommit(false);
            System.out.println(buyId);
            for (int i=0; i<lists.size();i++){
            	CartDto cart = lists.get(i);
            	
            	sql = "insert into buy ("
            			+ "buy_id,"
            			+ "buyer,"
            			+ "book_id,"
            			+ "book_title,"
            			+ "buy_price,"
            			+ "buy_count,"         			
               			+ "book_image,"
                		+ "buy_date,"
                		+ "account,"
                		+ "deliveryName,"
                		+ "deliveryTel,"
                		+ "deliveryAdress)";
                sql += " values (?,?,?,?,?,?,?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql);
                
                pstmt.setLong(1, buyId);
                pstmt.setString(2, id);
                pstmt.setInt(3, cart.getBook_id());
                pstmt.setString(4, cart.getBook_title());
                pstmt.setInt(5, cart.getBuy_price());
                pstmt.setInt(6, cart.getBuy_count());
                pstmt.setString(7, cart.getBook_image());
                pstmt.setTimestamp(8, reg_date);
                pstmt.setString(9, account);
                pstmt.setString(10, deliveryName);
                pstmt.setString(11, deliveryTel);
                pstmt.setString(12, deliveryAdress);
                
                pstmt.executeUpdate();
                
                pstmt = conn.prepareStatement("select book_count from book where book_id=?");
                pstmt.setInt(1, cart.getBook_id());
                rs = pstmt.executeQuery();
                rs.next();
                
                nowCount = (short)(rs.getShort(1) - 1);
                
                pstmt = conn.prepareStatement("update book set book_count=? where book_id=?");
           
                pstmt.setShort(1, nowCount);
    			pstmt.setInt(2, cart.getBook_id());
                
                pstmt.executeUpdate(); 
            }
            
            pstmt = conn.prepareStatement("delete from cart where buyer=?");
            pstmt.setString(1, id);
          
            pstmt.executeUpdate();
            
            conn.commit();
            conn.setAutoCommit(true);
            
        }catch(Exception ex) {
        	ex.printStackTrace();
        } finally {
        	getClose();
        }
    }
    
    
    public int getListCount(String id)  {

        int x=0;

        try {
        	
            getConnection();
            pstmt = conn.prepareStatement("select count(*) from buy where buyer=?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
               x= rs.getInt(1);
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
		return x;
    }
    
    
    public int getListCount() {

        int x = 0;

        try {
        	
            getConnection();
            
            pstmt = conn.prepareStatement("select count(*) from buy");
            rs = pstmt.executeQuery();

            if (rs.next()) {
               x= rs.getInt(1);
			}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
        
		return x;
		
    }
    
    
    public List<BuyDto> getBuyList(String id) {
   	   
        List<BuyDto> lists = new ArrayList<BuyDto>();
        
        try {
        	
       	    getConnection();
            pstmt = conn.prepareStatement("select * from buy where buyer = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	
              BuyDto buy = new BuyDto();
           	  buy.setBuy_id(rs.getLong("buy_id"));
           	  buy.setBook_id(rs.getInt("book_id"));
           	  buy.setBook_title(rs.getString("book_title"));
           	  buy.setBuy_price(rs.getInt("buy_price"));
           	  buy.setBuy_count(rs.getByte("buy_count")); 
           	  buy.setBook_image(rs.getString("book_image"));
           	  buy.setSanction(rs.getString("sanction"));
           	 
           	  lists.add(buy);
           	  
			}
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
		return lists;
    }
    
    
    public List<BuyDto> getBuyList() {
    	
        List<BuyDto> lists = new ArrayList<BuyDto>();
        
        try {
        	
	       	getConnection();
            pstmt = conn.prepareStatement("select * from buy");
            rs = pstmt.executeQuery();

            while (rs.next()) {
            	
              BuyDto buy = new BuyDto();
           	  buy.setBuy_id(rs.getLong("buy_id"));
           	  buy.setBuyer(rs.getString("buyer"));
           	  buy.setBook_id(rs.getInt("book_id"));
           	  buy.setBook_title(rs.getString("book_title"));
           	  buy.setBuy_price(rs.getInt("buy_price"));
           	  buy.setBuy_count(rs.getByte("buy_count")); 
           	  buy.setBook_image(rs.getString("book_image"));
           	  buy.setBuy_date(rs.getTimestamp("buy_date"));
           	  buy.setAccount(rs.getString("account"));
           	  buy.setDeliveryName(rs.getString("deliveryName"));
           	  buy.setDeliveryTel(rs.getString("deliveryTel"));
           	  buy.setDeliveryAddress(rs.getString("deliveryAdress"));
           	  buy.setSanction(rs.getString("sanction"));
           	  lists.add(buy);
           	  
		    }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	getClose();
        }
        
		return lists;
		
    }
    
}