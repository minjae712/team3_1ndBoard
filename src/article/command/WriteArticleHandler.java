package article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import article.service.WriteArticleService;
import article.service.WriteRequest;
import auth.service.User;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler {	// WriteArticleHandler 클래스 CommandHandler 인터페이스 상속받아 
	private static final String FORM_VIEW = "/WEB-INF/view/newArticleForm.jsp";	// FORM_VIEW 변수에 = "/WEB-INF/view/newArticleForm.jsp" 지정
	private WriteArticleService writeService = new WriteArticleService(); // writeService 참조 변수로 WriteArticleService생성자 생성
	
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) { 
		if (req.getMethod().equalsIgnoreCase("GET")) { // 화면 단 GET
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else { 
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);  
			return null;
		}
	}

	
	//  processForm res, req 매개변수 담아 FORM_VIEW 리턴
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW; 
	}
	
	
	// processSubmit processSubmit () 에러담아 
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		
		// 세션에서 로그인한 사용자 정보를 구한다.
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);

		
//		validate 빈값일때  FORM_VIEW로 리턴
//		writeReq.validate(errors);
		
//		if (!errors.isEmpty()) {
//			return FORM_VIEW;
//		}
		
		
		
		int newArticleNo = writeService.write(writeReq);
		req.setAttribute("newArticleNo", newArticleNo);
		
		// newArticleSuccess 성공화면 띄우기
		return "/WEB-INF/view/newArticleSuccess.jsp";
	}

	// createWriteRequestdp title과 content 받기, Writer 생성자 생성 id, name값 user에 받기 
	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(
				new Writer(user.getId(), user.getName()),
				req.getParameter("title"),
				req.getParameter("content"));
	}
}
