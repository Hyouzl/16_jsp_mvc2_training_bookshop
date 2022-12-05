package bookshop.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.ManagerDao;

public class _03_ManagerLoginPro implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
           
        String id = request.getParameter("id");
	    String passwd  = request.getParameter("passwd");

		request.setAttribute("check", ManagerDao.getInstance().userCheck(id,passwd));
		request.setAttribute("id", id);
		
		return "/03_managerLoginPro.jsp";
		
	}

}
