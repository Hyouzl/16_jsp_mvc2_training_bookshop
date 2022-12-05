package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.BuyDao;
import bookshop.dto.BuyDto;

public class _39_BuyList implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		String buyer = request.getParameter("buyer");
		
		List<BuyDto> buyLists = null;
		int count = 0;
		
		BuyDao buyProcess = BuyDao.getInstance();
		count = buyProcess.getListCount(buyer);

		if (count > 0) {
			buyLists = buyProcess.getBuyList(buyer);
			request.setAttribute("buyLists" , buyLists);
		}
	
		request.setAttribute("count", count);
		request.setAttribute("type", 1);
		
		return "/39_buyList.jsp";
		
	}
	
}