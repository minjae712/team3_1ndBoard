package member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

// 회원 가입 기능
public class JoinService {

	private MemberDao memberDao = new MemberDao();
	
	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			// 만약 이미 ID가 존재하면 롤백을하고 오류를 발생시키고 그렇지 않다면 새 데이터를 Member에 저장한다
			Member member = memberDao.selectById(conn, joinReq.getId());
			if (member != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}
			
			memberDao.insert(conn, new Member(
							joinReq.getId(), 
							joinReq.getName(), 
							joinReq.getPassword())
					);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
