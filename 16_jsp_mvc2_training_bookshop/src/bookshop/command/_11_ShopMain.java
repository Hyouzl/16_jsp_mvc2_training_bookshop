package bookshop.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookshop.controller.CommandAction;
import bookshop.dao.ManagerDao;
import bookshop.dto.ManagerDto;

public class _11_ShopMain implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable {
			
		ManagerDto bookList[] = null;
		List<ManagerDto[]> bookLists = new ArrayList<ManagerDto[]>();
		
		ManagerDao bookProcess = ManagerDao.getInstance();
		
		for(int i = 1; i <= 3;i++){
			bookList = bookProcess.getBooks(i+"00",3);
			bookLists.add(bookList);
		}
		 
        request.setAttribute("bookLists", bookLists);   
		request.setAttribute("type", 1);
		
		return "/11_shopMain.jsp";
		
	}
	
}