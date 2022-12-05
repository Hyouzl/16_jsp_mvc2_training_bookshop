package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;

public class _36_CartUpdateForm implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		request.setAttribute("cart_id", request.getParameter("cart_id"));
		request.setAttribute("buy_count",  request.getParameter("buy_count"));
		request.setAttribute("type", 1);
		
		return "/36_cartUpdateForm.jsp";
		
	}

}
