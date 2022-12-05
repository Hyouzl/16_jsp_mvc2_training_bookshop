package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;

public class _06_BookRegister implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		request.setAttribute("type", 0);
		return "/06_bookRegister.jsp";
	}
	
}