package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.CartDao;
import bookshop.dto.CartDto;

public class _34_InsertCart implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		CartDto cart = new CartDto();
		cart.setBook_id(Integer.parseInt(request.getParameter("book_id")));
		cart.setBook_image(request.getParameter("book_image"));
		cart.setBook_title(request.getParameter("book_title"));
		cart.setBuy_count(Byte.parseByte(request.getParameter("buy_count")));
		cart.setBuy_price((int)Float.parseFloat(request.getParameter("buy_price")));
		cart.setBuyer(request.getParameter("buyer"));
		
		CartDao.getInstance().insertCart(cart);
		
		return "/34_insertCart.jsp";
		
	}
	
}