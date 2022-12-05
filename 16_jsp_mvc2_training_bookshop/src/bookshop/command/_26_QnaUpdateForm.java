package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.QnaDao;
import bookshop.dto.QnaDto;


public class _26_QnaUpdateForm implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		int qna_id = Integer.parseInt(request.getParameter("qna_id"));

		request.setAttribute("qna",  QnaDao.getInstance().updateGetArticle(qna_id));
		request.setAttribute("qna_id", qna_id);
		request.setAttribute("book_kind", request.getParameter("book_kind"));
		request.setAttribute("type", 1);
		
		return "/26_qnaUpdate.jsp";
		
	}
	
}