package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.BuyDao;
import bookshop.dto.BuyDto;

public class _42_OrderList implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		List<BuyDto> buyLists = null;

		BuyDao buyProcess = BuyDao.getInstance();
		int count = buyProcess.getListCount();
		
		if (count > 0){			
			buyLists = buyProcess.getBuyList();
		    request.setAttribute("buyLists", buyLists);
		}
		
		request.setAttribute("count", count);
	    request.setAttribute("type", 0);
	    
		return "/42_orderList.jsp";
		
	}
	
}