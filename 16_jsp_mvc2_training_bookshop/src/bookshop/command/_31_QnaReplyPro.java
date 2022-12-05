package bookshop.command;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.QnaDao;
import bookshop.dto.QnaDto;


public class _31_QnaReplyPro implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
		
        request.setCharacterEncoding("utf-8");
		
        int qna_id =  Integer.parseInt(request.getParameter("qna_id"));
		
		QnaDto qna = new QnaDto();
		qna.setQna_id(qna_id);
		qna.setBook_id(Integer.parseInt(request.getParameter("book_id")));
		qna.setBook_title(request.getParameter("book_title"));
		qna.setQna_content("[답변]:"+request.getParameter("qna_content"));
        qna.setQna_writer(request.getParameter("qna_writer"));
        qna.setGroup_id(qna_id);
        qna.setReply((byte)1);
        qna.setReg_date(new Timestamp(System.currentTimeMillis()));
		qna.setQora(Byte.parseByte(request.getParameter("qora")));
		
		request.setAttribute("check", QnaDao.getInstance().insertArticle(qna, qna_id));
		
		return "/31_qnaReplyPro.jsp";
		
	}

}
