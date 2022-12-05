package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.dao.CartDao;
import bookshop.controller.CommandAction;

public class _37_CartUpdatePro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		int cart_id = Integer.parseInt(request.getParameter("cart_id"));
		byte buy_count = Byte.parseByte(request.getParameter("buy_count"));
		
		CartDao.getInstance().updateCount(cart_id, buy_count);
		
		request.setAttribute("type", 1);
		
		return "/37_cartUpdatePro.jsp";
		
	}
	
}