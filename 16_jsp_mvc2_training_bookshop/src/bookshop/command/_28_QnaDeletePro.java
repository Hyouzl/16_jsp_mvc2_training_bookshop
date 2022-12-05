package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.dao.QnaDao;
import bookshop.controller.CommandAction;

public class _28_QnaDeletePro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		request.setAttribute("check", QnaDao.getInstance().deleteArticle(Integer.parseInt(request.getParameter("qna_id"))));
	
		return "/28_qnaDeletePro.jsp";
	
	}
	
}