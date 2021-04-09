package article.command;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ModifyArticleService;
import article.service.ModifyRequest;
import article.service.PermissionChecker;
import article.service.ReadArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class ModifyArticleHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/modifyForm.jsp";

	private ReadArticleService readService = new ReadArticleService();
	private ModifyArticleService modifyService = new ModifyArticleService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			return null;
		}
	}
	//				폼 요청 처리함
	private String processForm(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			// 폼에 보여줄 게시글을 구한다.
			//no값을 가져오고 readCount 값은 false로 조회수를 올리지 않는다.
			ArticleData articleData = readService.getArticle(no, false);
			// 현재 로그인한 사용자 정보를 구한다.
			User authUser = (User) req.getSession().getAttribute("authUser");
			// 현재 로그인한 사용자가 게시글의 작성자가 아니면 아래의 403 응답코드 전송을 처리함
			if (!PermissionChecker.canUp_Del(authUser.getId(), articleData.getArticle()))
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				
				
			// 폼에 데이터를 보여줄 때 사용할 객체를 생성하고, request의 modReq속성에 저장한다.
			ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,
					articleData.getArticle().getTitle(),
					articleData.getContent());
			
			req.setAttribute("modReq", modReq);
			// 폼을 위한 뷰("/WEB-INF/view/modifyForm.jsp")를 리턴함
			return FORM_VIEW;
		// 게시글이 존재하지 않으면 ArticleNotFoundException예외처리를발생하고
		} catch (ArticleNotFoundException e) {
			/* static int	SC_NOT_FOUND (404 응답코드 전송)
			 * 요청한 자원이 존재하지 않음, 요청 된 리소스를 사용할 수 없음을 나타내는 상태 코드 (404)입니다.*/
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	

	private String processSubmit(HttpServletRequest req, HttpServletResponse res){
		// 게시글 수정을 요청한 사용자(authUser) 정보를 구한다.
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);
		// 요청 파라미터와 함께 현재 사용자 정보를 이용해서 ModifyRequest객체를 생성한다.
		ModifyRequest modReq = new ModifyRequest(authUser.getId(), no,
				req.getParameter("title"),
				req.getParameter("content"));
		// ModifyRequest객체를 request의 "modReq"속성에 저장한다. 
		req.setAttribute("modReq", modReq);
			
			modifyService.modify(modReq);
			return "/WEB-INF/view/modifySuccess.jsp";
	}
}
