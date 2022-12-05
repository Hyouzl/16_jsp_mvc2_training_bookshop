package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.dao.ManagerDao;
import bookshop.controller.CommandAction;
import bookshop.dao.QnaDao;
import bookshop.dto.ManagerDto;
import bookshop.dto.QnaDto;

public class _23_BookContent implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		List<QnaDto> qnaLists;
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		
		ManagerDao bookProcess = ManagerDao.getInstance();
		ManagerDto book = bookProcess.getBook(book_id);
		
		QnaDao qnaProcess = QnaDao.getInstance();
		int count = qnaProcess.getArticleCount(book_id);
	
		if (count > 0) {
			qnaLists = qnaProcess.getArticles(count, book_id);
        	request.setAttribute("qnaLists", qnaLists);
        }

		request.setAttribute("book", book);
		request.setAttribute("book_id", book_id);
		request.setAttribute("book_kind", request.getParameter("book_kind"));
		request.setAttribute("count", count);
		request.setAttribute("type", 1);
		
		return "/23_bookContent.jsp";
		
	}
	
}