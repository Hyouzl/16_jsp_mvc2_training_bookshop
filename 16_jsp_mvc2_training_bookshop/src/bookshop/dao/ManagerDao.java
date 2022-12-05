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

import bookshop.dto.ManagerDto;

public class ManagerDao {

	private ManagerDao() {}
	private static ManagerDao instance = new ManagerDao();
	public static ManagerDao getInstance() {
		return instance;
	}

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

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
	
	public int userCheck(String id, String passwd) {
		
		int check = -1;
		
		try {
			
			getConnection();
			pstmt = conn.prepareStatement("SELECT managerPw FROM manager WHERE managerId=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbpw = rs.getString("managerPw");
				if (passwd.equals(dbpw)) {
					check = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return check;
		
	}
	
	
	public int getBookCount(String book_kind)  {
	   
	    int x=0;

	    try {
	    	
	        getConnection();
	        pstmt = conn.prepareStatement("select count(*) from book where book_kind = ?");
	        pstmt.setInt(1, Integer.parseInt(book_kind));
	        rs = pstmt.executeQuery();

	        if (rs.next()) 
	            x= rs.getInt(1);
	        
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    } finally {
	    	getClose();
	    }
	    
		return x;
		
	}
	
	
	public String getBookTitle(int book_id){
        
        String x = "";

        try {
            
        	getConnection();          
            pstmt = conn.prepareStatement("select book_title from book where book_id = ? ");
            pstmt.setInt(1, book_id);
            rs = pstmt.executeQuery();

            if (rs.next()) 
               x= rs.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
        
		return x;
		
    }
	
	
	public int getBookCount() {

		int count = 0;

		try {
			
			getConnection();
			pstmt = conn.prepareStatement("SELECT COUNT(*) FROM book");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return count;
	}

	
	public List<ManagerDto> getBooks(String book_kind) {

		List<ManagerDto> bookList = new ArrayList<ManagerDto>();

		try {

			getConnection();

			if (book_kind.equals("all")) {
				pstmt = conn.prepareStatement("SELECT * FROM book");
			} 
			else {
				pstmt = conn.prepareStatement("SELECT * FROM book WHERE book_kind=? ORDER BY reg_date DESC");
				pstmt.setString(1, book_kind);
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				do {
					
					ManagerDto book = new ManagerDto();
					book.setBook_id(rs.getInt("book_id"));
					book.setBook_kind(rs.getString("book_kind"));
					book.setBook_title(rs.getString("book_title"));
					book.setBook_price(rs.getInt("book_price"));
					book.setBook_count(rs.getInt("book_count"));
					book.setAuthor(rs.getString("author"));
					book.setPublishing_com(rs.getString("publishing_com"));
					book.setPublishing_date(rs.getString("publishing_date"));
					book.setBook_image(rs.getString("book_image"));
					book.setDiscount_rate(rs.getInt("discount_rate"));
					book.setReg_date(rs.getString("reg_date"));
					bookList.add(book);
					
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return bookList;
		
	}

	
	public int registedBookconfirm(String book_kind, String book_title, String author) {

		int check = 1; 

		try {

			getConnection();
			pstmt = conn.prepareStatement( "SELECT book_title FROM book WHERE book_kind=? AND book_title=? AND author=?");
			pstmt.setString(1, book_kind);
			pstmt.setString(2, book_title);
			pstmt.setString(3, author);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				check = -1; 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return check;
		
	}

	
	public void insertBook(ManagerDto book) {

		int num = 0;

		try {
			
			getConnection();

			String sql = "SELECT COUNT(book_id) FROM book";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1) + 1;
			}

			sql = "INSERT INTO book(book_id,book_kind,book_title,book_price,";
			sql += "book_count,author,publishing_com,publishing_date,book_image,";
			sql += "book_content,discount_rate,reg_date) VALUES (?,?,?,?,?,?,?,?,?,?,?,now())";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);
			pstmt.setString(2, book.getBook_kind());
			pstmt.setString(3, book.getBook_title());
			pstmt.setInt(4, book.getBook_price());
			pstmt.setInt(5, book.getBook_count());
			pstmt.setString(6, book.getAuthor());
			pstmt.setString(7, book.getPublishing_com());
			pstmt.setString(8, book.getPublishing_date());
			pstmt.setString(9, book.getBook_image());
			pstmt.setString(10, book.getBook_content());
			pstmt.setInt(11, book.getDiscount_rate());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
	}

	
	public ManagerDto getBook(int bookId) {

		ManagerDto book = null;

		try {
			
			getConnection();

			String sql = "SELECT * FROM book WHERE book_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				book = new ManagerDto();

				book.setBook_kind(rs.getString("book_kind"));
				book.setBook_title(rs.getString("book_title"));
				book.setBook_price(rs.getInt("book_price"));
				book.setBook_count(rs.getInt("book_count"));
				book.setAuthor(rs.getString("author"));
				book.setPublishing_com(rs.getString("publishing_com"));
				book.setPublishing_date(rs.getString("publishing_date"));
				book.setBook_image(rs.getString("book_image"));
				book.setBook_content(rs.getString("book_content"));
				book.setDiscount_rate(rs.getInt("discount_rate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return book;
		
	}
	
	
    public void updateBook(ManagerDto book, int book_id) {
        
    	try {
    		
            getConnection();
            
            String sql = "UPDATE book SET book_kind=?,book_title=?,book_price=?";
		            sql += ",book_count=?,author=?,publishing_com=?,publishing_date=?";
		            sql += ",book_image=?,book_content=?,discount_rate=?";
		            sql += " where book_id=?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getBook_kind());
            pstmt.setString(2, book.getBook_title());
            pstmt.setInt(3, book.getBook_price());
            pstmt.setInt(4, book.getBook_count());
            pstmt.setString(5, book.getAuthor());
            pstmt.setString(6, book.getPublishing_com());
			pstmt.setString(7, book.getPublishing_date());
			pstmt.setString(8, book.getBook_image());
			pstmt.setString(9, book.getBook_content());
			pstmt.setInt(10, book.getDiscount_rate());
			pstmt.setInt(11, book_id);
            
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	getClose();
        }
    }
    
    
    public void deleteBook(int bookId) {
        
        try {
        	
			getConnection();
            pstmt = conn.prepareStatement("DELETE FROM book WHERE book_id=?");
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
            
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
        	getClose();
        }
    }
    
    
 	public ManagerDto[] getBooks(String book_kind, int count) {

 		ManagerDto bookList[]=null;
         
 		int i = 0;
         
         try {
        	 
             getConnection();
             pstmt = conn.prepareStatement("SELECT * FROM book WHERE book_kind=? ORDER BY reg_date DESC LIMIT ?,?");
             pstmt.setString(1, book_kind);
             pstmt.setInt(2, 0);
             pstmt.setInt(3, count);
             
         	 rs = pstmt.executeQuery();

             if (rs.next()) {
             
             	bookList = new ManagerDto[count];
                 
             	do {
             		
                 	 ManagerDto book= new ManagerDto();
                     book.setBook_id(rs.getInt("book_id"));
                     book.setBook_kind(rs.getString("book_kind"));
                     book.setBook_title(rs.getString("book_title"));
                     book.setBook_price(rs.getInt("book_price"));
                     book.setBook_count(rs.getInt("book_count"));
                     book.setAuthor(rs.getString("author"));
                     book.setPublishing_com(rs.getString("publishing_com"));
                     book.setPublishing_date(rs.getString("publishing_date"));
                     book.setBook_image(rs.getString("book_image"));
                     book.setDiscount_rate(rs.getInt("discount_rate"));
                     book.setReg_date(rs.getString("reg_date"));
                     bookList[i] = book;
                      
                     i++;
                     
 			    }while(rs.next());
 			}
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
        	 getClose();
         }
         
 		return bookList;
 		
     }
 	
}
