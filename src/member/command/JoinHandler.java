package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;
			// 요청하는 폼을 보여주고 joinService를 이용해 회원가입 처리 함
public class JoinHandler implements CommandHandler {
	
	// 요청한 폼의 뷰 경로는 /WEB-INF/view/joinForm.jsp 이다
	private static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp"; 
	private JoinService joinService = new JoinService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		// GET 요청시  폼의 뷰 경로를 반환한다
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
			// POST 요청시 폼을 전송한다
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	/// processForm()은 폼의 뷰 경로를 처리한다
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	// processSubmit()은 폼 전송을 처리한다
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		// 요청한 데이터를 바탕으로 새로운 JoinRequest 객체를 생성한다
		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(req.getParameter("id"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		
		// 에러 속성을 담을 Map 객체를 생성하고, errors에 저장한다
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		// joinReq의 값이 유효한지 검사한다
		joinReq.validate(errors);
		
		// errors가 비어있지 않다면 폼 뷰의 경로를 반환한다
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		// joinService의 join을 실행해서 가입처리에 성공하면 보여줄 뷰 경로는  /WEB-INF/view/joinSuccess.jsp 이다 
		try {
			joinService.join(joinReq);
			return "/WEB-INF/view/joinSuccess.jsp";
		} catch (DuplicateIdException e) {
			// 아이디가 이미 존재하면 DuplicateIdException이 발생하고 errors에 duplicateId를 추가하고 기본 폼 뷰의 경로를 반환한다
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
	}

}
