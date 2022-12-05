package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.MemberDao;
import bookshop.dto.MemberDto;

public class _17_ModifyPro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		MemberDto member = new MemberDto();
		member.setId(request.getParameter("id"));
        member.setPasswd(request.getParameter("passwd"));
        member.setName(request.getParameter("name"));
		member.setAddress(request.getParameter("address"));
		member.setTel(request.getParameter("tel"));
		
		request.setAttribute("check", MemberDao.getInstance().updateMember(member));
		
		return "/17_modifyPro.jsp";
		
	}
	
}