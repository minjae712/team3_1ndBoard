package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.DeleteArticleService;
import article.service.DeleteRequest;
import article.service.PermissionChecker;
import article.service.ReadArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {

	private ReadArticleService readService = new ReadArticleService();
	private DeleteArticleService deleteService = new DeleteArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
			//String에 매개변수 noVal 선언하고 req.getParameter("no") 값을 가져옴
			String noVal = req.getParameter("no");   
			// int형에 변수 no를 선언하고 Integer.parseInt(noVal)로  String형의 noVal을 int형으로 변환
			int no = Integer.parseInt(noVal);  		
			// 폼에 보여줄 게시글을 구한다.
			//ArticleData 클래스에 articleData변수를 선언하고, 
			//readService.getArticle(no, false) no값을 가져오고 readCount 값은 false로 조회수를 올리지 않는다.
			ArticleData articleData = readService.getArticle(no, false);    
			// 현재 로그인한 사용자 정보를 구한다. 
			User authUser = (User) req.getSession().getAttribute("authUser");  
			//현재 로그인한 작성자와 글의 작성자가 맞는지 확인 하고 작성자가 아니면 아래의 403 응답코드 전송을 처리함
			if (!PermissionChecker.canUp_Del(authUser.getId(), articleData.getArticle())) 
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
//		// 요청 파라미터와 함께 현재 사용자 정보를 이용해서 DeleteRequest객체를 생성한다.
		DeleteRequest delReq = new DeleteRequest(authUser.getId(), no,
				req.getParameter("title"),
				req.getParameter("content"));
		// DeleteRequest객체를 request의 "delReq"속성에 저장한다. 
		req.setAttribute("delReq", delReq);
//			// 게시글 삭제기능을 실행한다.
			deleteService.delete(delReq);
				return "/WEB-INF/view/deleteSuccess.jsp";
			
	}
}

