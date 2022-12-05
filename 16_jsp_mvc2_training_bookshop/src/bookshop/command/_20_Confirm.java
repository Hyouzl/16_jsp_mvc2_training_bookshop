package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bookshop.dao.MemberDao;
import bookshop.controller.CommandAction;

public class _20_Confirm implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		request.setAttribute("check", MemberDao.getInstance().confirm(request.getParameter("id")));
		
		return "/20_confirm.jsp";
		
	}
	
}