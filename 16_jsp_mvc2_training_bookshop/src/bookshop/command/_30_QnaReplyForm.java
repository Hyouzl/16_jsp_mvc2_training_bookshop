package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.QnaDao;
import bookshop.dto.QnaDto;

public class _30_QnaReplyForm implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
				
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));
		QnaDto qna  = QnaDao.getInstance().updateGetArticle(qna_id);
		
		request.setAttribute("qna_id", qna_id);
		request.setAttribute("book_id",  qna.getBook_id());
		request.setAttribute("book_title", qna.getBook_title());
		request.setAttribute("qna_content",  qna.getQna_content());
		request.setAttribute("qora", 2);
		request.setAttribute("type", 0);
		
		return "/30_qnaReplyForm.jsp";
		
	}
	
}