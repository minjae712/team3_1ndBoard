package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticlePage;
import article.service.ListArticleService;
import mvc.command.CommandHandler;
// 													↓ src/mvc/command의 인터페이스 commandHander를 구현한다.
public class ListArticleHandler implements CommandHandler {
//													↓ ListArticleService 인스턴스 생성
	private ListArticleService listService = new ListArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String pageNoVal = req.getParameter("pageNo");//						요청헤더값에서 pageNo 속성의 파라미터를 받아 pageNoVal 변수에 대입
		int pageNo = 1;//														정수형 변수 pageNo는 1로 초기화한다.
		if(pageNoVal != null) {//												pageNoVal에 대입된 값이 null이 아니라면
			pageNo = Integer.parseInt(pageNoVal);//								파라미터로 받은 pageNoVal를 형변환해서 pageNo에 다시 대입해준다.
		}//																		(getParameter 메서드 반환값 자료형과 pageNoVal의 자료형이 String 형이므로)
		
		ArticlePage articlePage = listService.getArticlePage(pageNo);//			멤버변수 listService의 getArticlePage메서드를 사용해 위에서 대입된 pageNo를 매개로 주어
//																				articlePage에 대입한다.
		req.setAttribute("articlePage", articlePage);//							그 후 request 객체 속성에 "articlePage"속성에 articlePage 변수를 주어 추가하거나 변경한다.
		String list = "list";
		req.setAttribute("page",list);
		return "/main.jsp";//								반환값으로 "/WEB-INF/view/listArticle.jsp" 경로를 준다.
	}

}
