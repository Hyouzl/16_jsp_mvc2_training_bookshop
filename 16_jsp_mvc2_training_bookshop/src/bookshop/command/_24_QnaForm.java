package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.dao.ManagerDao;
import bookshop.controller.CommandAction;



public class _24_QnaForm implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		int book_id = Integer.parseInt(request.getParameter("book_id"));  
		
		request.setAttribute("book_kind", request.getParameter("book_kind"));
	    request.setAttribute("book_id", book_id);
	    request.setAttribute("book_title",  ManagerDao.getInstance().getBookTitle(book_id));
	    request.setAttribute("qora", 1);
		request.setAttribute("type", 1);
		
		return "/24_qnaForm.jsp";
		
	}
	
}