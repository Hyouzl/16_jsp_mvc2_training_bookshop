package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.ManagerDao;
import bookshop.dto.ManagerDto;

public class _05_BookList implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		List<ManagerDto> bookList = null;
		String book_kind = request.getParameter("book_kind");
		int count = 0;
		
		ManagerDao bookProcess = ManagerDao.getInstance();
        count = bookProcess.getBookCount(); 
        
        if (count > 0) {
        	bookList = bookProcess.getBooks(book_kind);
        	request.setAttribute("bookList", bookList);
        }
       
        request.setAttribute("count", count);
        request.setAttribute("book_kind", book_kind);
        request.setAttribute("type", 0);
       
		return "/05_bookList.jsp";
		
	}

}
