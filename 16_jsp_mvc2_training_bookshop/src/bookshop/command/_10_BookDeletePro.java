package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.dao.ManagerDao;
import bookshop.controller.CommandAction;


public class _10_BookDeletePro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		ManagerDao.getInstance().deleteBook(Integer.parseInt(request.getParameter("book_id"))); 
		request.setAttribute("book_kind", request.getParameter("book_kind"));
		
		return "/10_bookDeletePro.jsp";
		
	}
	
}