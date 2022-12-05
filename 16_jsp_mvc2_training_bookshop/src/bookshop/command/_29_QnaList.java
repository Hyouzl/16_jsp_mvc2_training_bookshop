package bookshop.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.QnaDao;
import bookshop.dto.QnaDto;


public class _29_QnaList implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		List<QnaDto> qnaLists;
	
		QnaDao qnaProcess = QnaDao.getInstance();
		int count = qnaProcess.getArticleCount();
		
		if (count > 0){
			qnaLists = qnaProcess.getArticles(count);
        	request.setAttribute("qnaLists", qnaLists);
        }
		
		request.setAttribute("count", count);
		request.setAttribute("type", 0);
		
		return "/29_qnaList.jsp";
		
	}
	
}
