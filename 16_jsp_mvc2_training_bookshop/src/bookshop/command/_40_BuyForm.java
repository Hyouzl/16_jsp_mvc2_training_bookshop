package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.BuyDao;
import bookshop.dao.CartDao;
import bookshop.dao.MemberDao;
import bookshop.dto.CartDto;
import bookshop.dto.MemberDto;



public class _40_BuyForm implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		String buyer = request.getParameter("buyer");
		
		List<CartDto> cartLists = null;
		int count = 0;

		CartDao bookProcess = CartDao.getInstance();
        count = bookProcess.getListCount(buyer);
		
		if (count > 0) {
			cartLists = bookProcess.getCart(buyer, count);
			request.setAttribute("cartLists", cartLists);
		}
	
		request.setAttribute("member", MemberDao.getInstance().getMember(buyer));
		request.setAttribute("accountLists", BuyDao.getInstance().getAccount());
		request.setAttribute("type", 1);
		
		return "/40_buyForm.jsp";
		
	}
	
}
