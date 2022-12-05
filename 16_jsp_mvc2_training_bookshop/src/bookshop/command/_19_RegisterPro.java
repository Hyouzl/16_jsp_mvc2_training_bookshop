package bookshop.command;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.MemberDao;
import bookshop.dto.MemberDto;

public class _19_RegisterPro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
	
		request.setCharacterEncoding("utf-8");	
		
		MemberDto member = new MemberDto();
		member.setId(request.getParameter("id"));
        member.setPasswd(request.getParameter("passwd"));
        member.setName(request.getParameter("name"));
        member.setReg_date(new Timestamp(System.currentTimeMillis()).toString());
		member.setAddress(request.getParameter("address"));
		member.setTel(request.getParameter("tel"));
        
		MemberDao.getInstance().insertMember(member);
		
		return "/19_registerPro.jsp";
		
	}

}
