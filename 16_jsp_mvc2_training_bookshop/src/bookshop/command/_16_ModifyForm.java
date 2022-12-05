package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.MemberDao;
import bookshop.dto.MemberDto;

public class _16_ModifyForm implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
	
		request.setAttribute("m", MemberDao.getInstance().getMember(id,passwd));
		request.setAttribute("id", id);
		request.setAttribute("type", 1);
		
		return "/16_modifyForm.jsp";
		
	}
	
}