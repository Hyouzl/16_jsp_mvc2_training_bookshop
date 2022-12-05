package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.ManagerDao;
import bookshop.dto.ManagerDto;


public class _08_BookUpdate implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
			
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		
		request.setAttribute("book_id", book_id);
		request.setAttribute("book_kind", request.getParameter("book_kind"));
        request.setAttribute("book", ManagerDao.getInstance().getBook(book_id));
		request.setAttribute("type", 0);
		
		return "/08_bookUpdate.jsp";
		
	}
	
}