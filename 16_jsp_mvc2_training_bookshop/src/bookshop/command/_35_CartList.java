package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.CartDao;
import bookshop.dto.CartDto;

public class _35_CartList implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		String buyer = request.getParameter("buyer");
		 
		List<CartDto> cartLists = null;
		int count = 0;

		//해당 buyer의 장바구니 목록의 수를 얻어냄
		CartDao bookProcess = CartDao.getInstance();
		count = bookProcess.getListCount(buyer);
		
		if (count > 0){			
			cartLists = bookProcess.getCart(buyer, count);
			request.setAttribute("cartLists", cartLists);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("type", 1);
		
		return "/35_cartList.jsp";
		
	}
	
}