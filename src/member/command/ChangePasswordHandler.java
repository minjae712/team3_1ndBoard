package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;
// 웹 브라우저의 암호변경 요청을 처리하는데 GET 요청이면 changePwdForm.jsp를 이용해 폼을 보여주고,
// POST 요청이면 ChangePasswordService를 이용해 암호변경 기능을 실행한다
public class ChangePasswordHandler implements CommandHandler {
	// "/WEB-INF/view/changePwdForm.jsp"
	private static final String FORM_VIEW = "/WEB-INF/view/changePwdForm.jsp";
	private ChangePasswordService changePwdSvc = new ChangePasswordService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
	throws Exception {
		// GET 요청시  폼의 뷰 경로를 반환한다
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
			// POST 요청시 폼을 전송한다
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	// processForm은 폼의 뷰 경로를 처리한다 
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}


	private String processSubmit(HttpServletRequest req, HttpServletResponse res)
	throws Exception {
		User user = (User)req.getSession().getAttribute("authUser");
			
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);

		String curPwd = req.getParameter("curPwd");
		String newPwd = req.getParameter("newPwd");
		
		if (curPwd == null || curPwd.isEmpty()) {
			errors.put("curPwd", Boolean.TRUE);
		}
		if (newPwd == null || newPwd.isEmpty()) {
			errors.put("newPwd", Boolean.TRUE);
		}
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		// changePwdSvc의 changePassword를 실행해 암호변경에 성공하면 보여줄 
		// 폼의 뷰 경로는  /WEB-INF/view/changePwdSuccess.jsp 이다
		try {
			changePwdSvc.changePassword(user.getId(), curPwd, newPwd);
			return "/WEB-INF/view/changePwdSuccess.jsp";
		// 현재 암호가 올바르지 않아 익셉션이 발생하면 관련 에러코드를 추가하고 폼 뷰를 반환한다
		} catch (InvalidPasswordException e) {
			errors.put("badCurPwd", Boolean.TRUE);
			return FORM_VIEW;
		// 아이디가 존재하지 않아 익셉션이 발생하면 SC_BAD_REQUEST를 전송한다
		} catch (MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}

}
