package bookshop.command;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.QnaDao;
import bookshop.dto.QnaDto;



public class _25_QnaPro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		QnaDto qna = new QnaDto();
		qna.setBook_id(Integer.parseInt(request.getParameter("book_id")));
		qna.setBook_title(request.getParameter("book_title"));
		qna.setQna_content(request.getParameter("qna_content"));
        qna.setQna_writer(request.getParameter("qna_writer"));
        byte reply = 0; 
        qna.setReply(reply);
        qna.setReg_date(new Timestamp(System.currentTimeMillis()));
		qna.setQora(Byte.parseByte(request.getParameter("qora")));
		
		request.setAttribute("check", QnaDao.getInstance().insertArticle(qna));
		
		return "/25_qnaPro.jsp";
		
	}

}
