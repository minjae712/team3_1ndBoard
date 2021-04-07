package article.service;

import java.sql.Connection; 
import java.sql.SQLException;
import java.util.Date; 

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.JdbcUtil; 
import jdbc.connection.ConnectionProvider;

public class WriteArticleService {

	// ArticleDao, ArticleContentDao 생성자 객체 생성 articleDao,contentDao 참조변수 활용
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();

	// public으로 내장 Intger의 write() 메서드  WriteRequest req로 파라미터 값 요청
	public Integer write(WriteRequest req) {
		Connection conn = null;
		
		// try catch 활용한 예외 처리
		try {
			conn = ConnectionProvider.getConnection(); // ConnectionProvider(jdbc connection 활용 db연동) 할 conn 지정
			conn.setAutoCommit(false); // 트랜잭션 처리를 위한 오토커밋 false 처리

			
			// toArticle() 메서드 이용해서 article값 넣어 사용
			Article article = toArticle(req);
			Article savedArticle = articleDao.insert(conn, article); // savedArticle은 articleDao에서 insert() 메서드로 db연결후 article값 insert함
			if (savedArticle == null) { // savedArticle 값이 null 일떄 throw catch문 활용한 예외처리
				throw new RuntimeException("fail to insert article");   // "fail to insert article" 실패 화면 띄움
			}
			ArticleContent content = new ArticleContent( // ArticleContet 의 content생성자 생성 
					savedArticle.getNumber(),			// savedArticle의 num 값 가져옴
					req.getContent());					// content()메서드 값 가져옴
			ArticleContent savedContent = contentDao.insert(conn, content);	// svaedContent는 articlecontent에서 insert() 메서드로 db연결후 content값 insert함 
			if (savedContent == null) {					// savedContent 값이 null 값일때
				throw new RuntimeException("fail to insert article_content"); // "fail to insert article_content" 실패 화면 띄움
			}

			conn.commit();  // 데이터베이스 conn연결 commit커밋

			return savedArticle.getNumber();  // savedArticle number값 리턴 
		} catch (SQLException e) {     
			JdbcUtil.rollback(conn);	// rollback 연결
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);  // rollback 연결
			throw e;
		} finally {
			JdbcUtil.close(conn); // 완료되면 jdbc 닫음
		}
	}

	
	// private으로  Article의 toArticle()메서드 활용해 writeRequeset의 날짜 및 id,pw 제목 값 리턴 가져오기
	private Article toArticle(WriteRequest req) {
		Date now = new Date(); // now 변수의 date()메서드의 생성자 생성
		return new Article(null, req.getWriter(), req.getTitle(), now, now, 0);
	}
}
