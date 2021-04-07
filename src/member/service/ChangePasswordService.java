package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

// 비밀번호를 바꾸는 기능
public class ChangePasswordService {

	private MemberDao memberDao = new MemberDao();
	
	public void changePassword(String userId, String curPwd, String newPwd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			// userId에 해당하는 값을 구한다
			Member member = memberDao.selectById(conn, userId);
			// member가 null이면 MemberNotFoundException을 발생시킨다
			if (member == null) {
				throw new MemberNotFoundException();
			}
			// curPwd가 회원의 실제 암호와 일치하지 않으면 InvalidPasswordException을 발생시킨다
			if (!member.matchPassword(curPwd)) {
				throw new InvalidPasswordException();
			}
			// member의 암호를 변경한다
			member.changePassword(newPwd);
			// member의 바뀐 암호로 수정한다
			memberDao.update(conn, member);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
