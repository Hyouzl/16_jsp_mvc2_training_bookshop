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

import bookshop.dto.QnaDto;

public class QnaDao {
	
	private QnaDao(){}
    private static QnaDao instance = new QnaDao();
    public static QnaDao getInstance() {
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
    
    
	public int insertArticle(QnaDto article) {
        
		int x = 0;
        int group_id = 1;        
        
        try {
        	
            getConnection();
            pstmt = conn.prepareStatement("select max(qna_id) from qna");
            rs = pstmt.executeQuery();
            
            if (rs.next()) 
                x= rs.getInt(1);
            
            if (x > 0)
               group_id = rs.getInt(1) + 1;
                   	
            String sql = "insert into qna(book_id,book_title,qna_writer,qna_content,";
            		sql += "group_id,qora,reply,reg_date) values(?,?,?,?,?,?,?,?)";
            		
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, article.getBook_id());
            pstmt.setString(2, article.getBook_title());
            pstmt.setString(3, article.getQna_writer());
            pstmt.setString(4, article.getQna_content());
            pstmt.setInt(5, group_id);
            pstmt.setInt(6, article.getQora());
            pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
            pstmt.executeUpdate();
            x = 1; 
            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
        
        return x;
        
    }
    
	
	public int insertArticle(QnaDto article, int qna_id){
		
		int x = 0;
        
        try {
        	
            getConnection();
            
            String sql = "insert into qna(book_id,book_title,qna_writer,qna_content,";
            	  sql += "group_id,qora,reply,reg_date) values(?,?,?,?,?,?,?,?)";
            	  
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, article.getBook_id());
            pstmt.setString(2, article.getBook_title());
            pstmt.setString(3, article.getQna_writer());
            pstmt.setString(4, article.getQna_content());
            pstmt.setInt(5, article.getGroup_id());
            pstmt.setInt(6, article.getQora());
            pstmt.setInt(7, article.getReply());
			pstmt.setTimestamp(8, article.getReg_date());
            pstmt.executeUpdate();
            
            sql="update qna set reply=? where qna_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 1);
		    pstmt.setInt(2, qna_id);
            pstmt.executeUpdate();
            
            x = 1; 
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
        
        return x;
        
    }
    
	
	public int getArticleCount(){
       
        int x = 0;

        try {
        	
            getConnection();
            pstmt = conn.prepareStatement("select count(*) from qna");
            rs = pstmt.executeQuery();
            if (rs.next()) 
               x= rs.getInt(1);
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
        
		return x;
		
    }
	
	
	public int getArticleCount(int book_id){
       
        int x=0;

        try {
        	
            getConnection();
            pstmt = conn.prepareStatement("select count(*) from qna where book_id = " + book_id);
            rs = pstmt.executeQuery();

            if (rs.next()) 
               x= rs.getInt(1);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
        
		return x;
		
    }
	
		
	public List<QnaDto> getArticles(int count){
       
        List<QnaDto> articleList=null;
       
        try {
        	
            getConnection();
            
            pstmt = conn.prepareStatement("select * from qna order by group_id desc, qora asc");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                articleList = new ArrayList<QnaDto>(count);
                
                do {
                  QnaDto article= new QnaDto();
				  article.setQna_id(rs.getInt("qna_id")); 
				  article.setBook_id(rs.getInt("book_id"));
				  article.setBook_title(rs.getString("book_title"));
                  article.setQna_writer(rs.getString("qna_writer"));
                  article.setQna_content(rs.getString("qna_content"));
                  article.setGroup_id(rs.getInt("group_id"));
                  article.setQora(rs.getByte("qora"));
                  article.setReply(rs.getByte("reply"));
			      article.setReg_date(rs.getTimestamp("reg_date"));
                  articleList.add(article);
			    } while(rs.next());
			}
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
        
		return articleList;
		
    }
	
	
	public List<QnaDto> getArticles(int count, int book_id) {
		
        List<QnaDto> articleList = null;
        
        try {
        	
            getConnection();
           
            pstmt = conn.prepareStatement("select * from qna where book_id = ? order by group_id desc, qora asc");
            pstmt.setInt(1, book_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	
                articleList = new ArrayList<QnaDto>(count);
                do {
                  QnaDto article= new QnaDto();
				  article.setQna_id(rs.getInt("qna_id")); 
				  article.setBook_id(rs.getInt("book_id"));
				  article.setBook_title(rs.getString("book_title"));
                  article.setQna_writer(rs.getString("qna_writer"));
                  article.setQna_content(rs.getString("qna_content"));
                  article.setGroup_id(rs.getInt("group_id"));
                  article.setQora(rs.getByte("qora"));
                  article.setReply(rs.getByte("reply"));
			      article.setReg_date(rs.getTimestamp("reg_date"));
                  articleList.add(article);
			    } while(rs.next());
			}
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
        
		return articleList;
		
    }
	
	
    public QnaDto updateGetArticle(int qna_id){
        
        QnaDto article=null;
       
        try {
        	
            getConnection();
            pstmt = conn.prepareStatement("select * from qna where qna_id = ?");
            pstmt.setInt(1, qna_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                article = new QnaDto();
                article.setQna_id(rs.getInt("qna_id")); 
				article.setBook_id(rs.getInt("book_id"));
				article.setBook_title(rs.getString("book_title"));
                article.setQna_writer(rs.getString("qna_writer"));
                article.setQna_content(rs.getString("qna_content"));
                article.setGroup_id(rs.getInt("group_id"));
                article.setQora(rs.getByte("qora"));
                article.setReply(rs.getByte("reply"));
			    article.setReg_date(rs.getTimestamp("reg_date"));     
			}
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
        
		return article;
		
    }
    
    
	public int updateArticle(QnaDto article){
      
		int x=-1;
		
        try {
        	
            getConnection();
            String sql="update qna set qna_content=? where qna_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, article.getQna_content());
			pstmt.setInt(2, article.getQna_id());
            pstmt.executeUpdate();
			x= 1;
			
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
        
		return x;
		
    }
    
	
	public int deleteArticle(int qna_id){
        
        int x = -1;
       
        try {
        	
			getConnection();
            pstmt = conn.prepareStatement("delete from qna where qna_id=?");
            pstmt.setInt(1, qna_id);
            pstmt.executeUpdate();
			x = 1;
			
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	getClose();
        }
        
		return x;
		
    }
	
}