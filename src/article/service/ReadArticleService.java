package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.connection.ConnectionProvider;

public class ReadArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public ArticleData getArticle(int articleNum, boolean increaseReadCount) {
		try (Connection conn = ConnectionProvider.getConnection()){
			
			// article 테이블에서 지정한 번호의 Article 객체를 구한다.
			Article article = articleDao.selectById(conn, articleNum);
			
			// 만약에 article 객체의 값이  존재하지 않는다면 예외 처리 익셉션을 발생시킨다.
			if (article == null) {
				throw new ArticleNotFoundException();
				// RuntimeException 을 상속받은 ArticleNotFoundException를 강제로 발생시킴
			}
			
			// article_content 테이블에서 지정한 번호의 ArticleContent 객체를 구한다.
			ArticleContent content = contentDao.selectById(conn, articleNum);
			
			// 만약에 article_content 게시글 내용 데이터가 존재하지 않는다면 예외처리 익셉션을 발생시킨다.
			if (content == null) {
				throw new ArticleContentNotFoundException();
				// RuntimeException 을 상속받은 ArticleContentNotFoundException을 강제로 발생시킴
			}
			// 만약 increaseReadCount 값이 true 라고 한다면 readCount의 값을 증가 시켜준다.
			if (increaseReadCount) {
				articleDao.increaseReadCount(conn, articleNum);
			}
			// 방금 구한 article과 content를 매개변수로 받는 ArticleData 생성자를 반환해줌
			return new ArticleData(article, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
