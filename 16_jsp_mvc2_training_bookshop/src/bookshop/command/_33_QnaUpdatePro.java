package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.QnaDao;
import bookshop.dto.QnaDto;

public class _33_QnaUpdatePro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
	
		request.setCharacterEncoding("utf-8");
		
		QnaDto qna = new QnaDto();
		qna.setQna_id(Integer.parseInt(request.getParameter("qna_id")));
		qna.setQna_content(request.getParameter("qna_content"));
		
		
		request.setAttribute("check" , QnaDao.getInstance().updateArticle(qna));
		
		return "/33_qnaReplyUpdatePro.jsp";
		
	}
	
}