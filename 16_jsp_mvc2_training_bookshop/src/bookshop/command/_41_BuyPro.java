package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.BuyDao;
import bookshop.dao.CartDao;
import bookshop.dto.CartDto;


public class _41_BuyPro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String account = request.getParameter("account");
		String deliveryName = request.getParameter("deliveryName");
		String deliveryTel = request.getParameter("deliveryTel");
		String deliveryAdress = request.getParameter("deliveryAdress");
		String buyer = request.getParameter("buyer");
		int count = 0;
		
		//구매처리를 위해 장바구니의 목록을 얻어냄
		CartDao cartProcess = CartDao.getInstance();
		count = cartProcess.getListCount(buyer);
		List<CartDto> cartLists = cartProcess.getCart(buyer,count);
		
		BuyDao.getInstance().insertBuy(cartLists,buyer,account, deliveryName, deliveryTel, deliveryAdress);

		request.setAttribute("orderStus", "주문완료");
		request.setAttribute("type", 1);
		
		return "/41_buyPro.jsp";
		
	}
	
}