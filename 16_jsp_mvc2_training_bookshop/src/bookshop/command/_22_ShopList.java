package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.ManagerDao;
import bookshop.dto.ManagerDto;


public class _22_ShopList implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		List<ManagerDto> bookList = null;
		int count = 0;
		String book_kind = request.getParameter("book_kind");
		
		ManagerDao bookProcess = ManagerDao.getInstance();
				
		if (book_kind.equals("all"))	count = bookProcess.getBookCount(); 
		else							count = bookProcess.getBookCount(book_kind);
		
        if (count > 0) {
        	request.setAttribute("bookList", bookProcess.getBooks(book_kind));
        }
        
        request.setAttribute("count", count);
        request.setAttribute("book_kind", book_kind);
        request.setAttribute("type", 1);
        
		return "/22_shopList.jsp";
		
	}

}
