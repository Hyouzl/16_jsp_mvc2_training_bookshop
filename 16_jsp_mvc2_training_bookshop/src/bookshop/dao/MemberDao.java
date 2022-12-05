package bookshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import bookshop.dto.MemberDto;

public class MemberDao {
	
	private MemberDao() {}
	private static MemberDao instance = new MemberDao();
	public static MemberDao getInstance() {
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

	
	public void insertMember(MemberDto member) {
		
		try {
			
			getConnection();
			pstmt = conn.prepareStatement("insert into member values (?,?,?,?,?,?)");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getReg_date());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getTel());
			
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			getClose();
		}
	}

	
	public int userCheck(String id, String passwd) {
		
		int x = -1;

		try {
			
			getConnection();
			pstmt = conn.prepareStatement("select * from member where id = ? and passwd=?");
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = 1;
			} 

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			getClose();
		}
		
		return x;
		
	}

	
	public int confirm(String id) {
		
		int x = -1;

		try {
			
			getConnection();
			pstmt = conn.prepareStatement("select id from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next())	x = 1; 
			else			x = -1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		return x;
	}

	
	public MemberDto getMember(String id) {
		
		MemberDto member = null;

		try {
			
			getConnection();
			pstmt = conn.prepareStatement("select * from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberDto();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setReg_date(rs.getString("reg_date"));
				member.setAddress(rs.getString("address"));
				member.setTel(rs.getString("tel"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			getClose();
		}
		
		return member;
		
	}

	
	public MemberDto getMember(String id, String passwd) {
		
		MemberDto member = null;

		try {
			
			getConnection();
			pstmt = conn.prepareStatement("select * from member where id = ? and passwd = ?"); 
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				member = new MemberDto();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setReg_date(rs.getString("reg_date"));
				member.setAddress(rs.getString("address"));
				member.setTel(rs.getString("tel"));

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			getClose();
		}
		
		return member;
		
	}

	
	@SuppressWarnings("resource")
	public int updateMember(MemberDto member) {
		
		int x = -1;

		try {
			
			getConnection();

			pstmt = conn.prepareStatement("select passwd from member where id = ? and passwd = ?");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt = conn.prepareStatement("update member set name=?,address=?,tel=? where id=?");
				pstmt.setString(1, member.getName());
				pstmt.setString(2, member.getAddress());
				pstmt.setString(3, member.getTel());
				pstmt.setString(4, member.getId());
				pstmt.executeUpdate();
				x = 1;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			getClose();
		}
		
		return x;
		
	}

	
	@SuppressWarnings("resource")
	public int deleteMember(String id, String passwd) {
		
		int x = -1;

		try {
			
			getConnection();
			pstmt = conn.prepareStatement("select * from member where id = ? and passwd = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt = conn.prepareStatement("delete from member where id=?");
				pstmt.setString(1, id);
				pstmt.executeUpdate();
				x = 1;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			getClose();
		}
		
		return x;
		
	}
	
}
