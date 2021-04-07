package article.service;

import java.sql.Connection;
import java.util.List;

import article.dao.ArticleDao;
import article.model.Article;
import jdbc.connection.ConnectionProvider;

public class ListArticleService {

	private ArticleDao articleDao = new ArticleDao();
	private int size = 10;
	
	public ArticlePage getArticlePage(int pageNum) {
		try(Connection conn = ConnectionProvider.getConnection()){//			연결객체를 가져와 conn에 대입한 후, try문을 수행한다.
			
			int total = articleDao.selectCount(conn);//							articleDao의 selectCount 메서드에 conn을 매개로 준 후, 반환값(게시글 수)을 total에 대입한다.
			List<Article> content = articleDao.select(conn,(pageNum -1) * size,size);
			//																	연결객체(conn),게시글 번호목록((pageNum -1)*10),히나의 목록에 보여줄 게시글 번호의 개수(size) 
			//																	를 매개로 select()를 호출한 후 DB에 접근해 쿼리를 실행시키고, 존재하는 Article(게시글)들을   
			//																	content에 대입한다.(pageNum 에 -1을 하는 이유는 쿼리에서 limit의 첫번째 행은 0부터 시작하기 때문이다.)
		
			return new ArticlePage(total,pageNum,size,content);//				새 게시글 페이지 객체를 생성해 반환해준다.
			
		} catch (Exception e) {													// 예외발생시 RuntimeException처리.
			throw new RuntimeException();
		}																		// try의 오토클로스를 적용해서 따로 닫아주지는 않는다.
	}
	
}
