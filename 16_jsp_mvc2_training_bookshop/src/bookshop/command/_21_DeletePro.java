package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.dao.MemberDao;
import bookshop.controller.CommandAction;


public class _21_DeletePro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {	
		
		String id = request.getParameter("id");
		String passwd  = request.getParameter("passwd");
		
		request.setAttribute("check", MemberDao.getInstance().deleteMember(id,passwd));
		
		return "/21_deletePro.jsp";
	
	}
	
}