package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleContentNotFoundException;
import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ReadArticleService;
import mvc.command.CommandHandler;

public class ReadArticleHandler implements CommandHandler {

	private ReadArticleService readService = new ReadArticleService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
			throws Exception {
		// request의 파라미터 no 값을 가져와 naVal에 담아준다.
		String noVal = req.getParameter("no");
		// String 형인 noVal을 매핑 하여 int 값으로 바꾼후 articleNum 에 담아준다.
		int articleNum = Integer.parseInt(noVal);
		try {
			// readService.getArticle() 메서드로 ArticleData 객체를 생성한다.
			ArticleData articleData = readService.getArticle(articleNum, true);
			// request의 articleData 속성에 게시글을 저장한다.
			req.setAttribute("articleData", articleData);
			// main.jsp 에서 보여줄 list.jsp 와 read.jsp를 구분하기 위해 String read 변수에 "read" 값을 담아줌
			String read = "read";
			// request의 articleData 속성에 게시글을 저장한다.
			req.setAttribute("page",read);
			return "/main.jsp";
			// 게시글이 존재하지 않으면 예외처리 익셉션이 발생한다면
		} catch (ArticleNotFoundException | ArticleContentNotFoundException e) {
			// 로그 메시지를 기록하고
			req.getServletContext().log("no article", e);
			// 404 에러 코드를 전송한다.
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
