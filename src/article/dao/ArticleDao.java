package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.model.Article;
import article.model.Writer;
import jdbc.JdbcUtil;

public class ArticleDao {
	
	public Article insert(Connection conn, Article article) throws SQLException{
		PreparedStatement pstmt =null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			pstmt = conn.prepareStatement("insert into article"
					+ "(writer_id, writer_name,title,read_cnt)"
					+"values(?,?,?,0)");
			pstmt.setString(1, article.getWriter().getId());
			pstmt.setString(2, article.getWriter().getName());
			pstmt.setString(3, article.getTitle());
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from article");
				if(rs.next()) {
					Integer newNum = rs.getInt(1);
					return new Article(newNum, 
							article.getWriter(), 
							article.getTitle(), 
							0);
				}
			}
			return null;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
		
	}
		
	public int selectCount(Connection conn) throws SQLException {//						총 게시글 수를 구하는 메서드.(연결객체 매개로 받음)
		Statement stmt = null;//														실행객체 선언
		ResultSet rs = null;//															결과객체 선언
		try {
			stmt = conn.createStatement();//											연결객체를 통해 실행객체를 생성
			rs = stmt.executeQuery("select count(*) from article");//					실행객체에 바로 "게시글의 수를 조회한다"는 쿼리문을 담아 실행시키고,결과 데이터를 결과객체에 대입한다.
			if(rs.next()) {//															만약 결과객체에 담긴 값이 있다면  
				return rs.getInt(1);//													int(정수형)으로 첫번째 칼럼값(count)을 가져와 반환값으로 준다.
			}
			return 0;//																	그렇지 않다면 0을 반환값으로 준다.
		} finally {
			JdbcUtil.close(rs);//														그 후 결과객체와 실행객체를 닫는다.
			JdbcUtil.close(stmt);
		}
	}
//														↓ 가져올 게시글의 시작 행
	public List<Article> select(Connection conn, int startRow,int size)//				게시글을 게시글번호 순으로 정렬하여 List에 담아 반환하는 메서드.(연결객체를 매개로 받음)
			throws SQLException{//								  ↑ 가져올 게시글의 총 개수.					
		PreparedStatement pstmt = null;//												실행객체를 선언한다.
		ResultSet rs = null;//															결과객체를 선언한다.
		try {//																			실행객체에 "게시물의 모든정보를 게시글번호 역순으로 정렬하여 startRow 행부터 size의 개수만큼 조회한다."
			//																			는 쿼리문을 대입하여 생성하고, 
			pstmt = conn.prepareStatement("select * from article order by article_no desc limit ?, ?");
			pstmt.setInt(1, startRow);//												첫번째 ? 에 int 형으로 startRow를 대입한다.
			pstmt.setInt(2, size);//													두번째 ? 에 int 형으로 size를 대입한다.
			rs = pstmt.executeQuery();//												쿼리문을 실행시켜 결과데이터를 결과객체에 대입한다.
			List<Article> result = new ArrayList<Article>();//							결과객체의 데이터를 담을 List형(Article 객체를 담는) 참조변수 result를 생성하여
			while(rs.next()) {//														반복하여 결과객체 데이터를 
				result.add(convertArticle(rs));//										result에 convertArticle()메서드를 사용해 형변환 한 후 대입한다.
			}
			return result;//															그렇게 대입된 result를 반환한다.
		} finally {//																	실행객체와 결과객체를 닫는다.
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Article convertArticle(ResultSet rs) throws SQLException {//				DB게시글 데이터 형변환 메서드
			return new Article(//														받은 결과객체를 게시글 객체전용으로 형변환하여 생성한다. 
					rs.getInt("article_no"),
					new Writer(rs.getString("writer_id"),rs.getString("writer_name")),
					rs.getString("title"),
					rs.getInt("read_cnt"));
		
	}
	
	public Article selectById(Connection conn,int no)throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select * from article where article_no = ?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Article article = null;
			if(rs.next()) {
				article = convertArticle(rs); 
			}
			return article;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
			// increaseReadCount() 메서드는 조회수를 1 증가시킬 때 사용한다.
	public void increaseReadCount(Connection conn, int no) throws SQLException{
		try(PreparedStatement pstmt = conn.prepareStatement(
				"update article set read_cnt = read_cnt + 1 where article_no = ?")){
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
	
	public int update(Connection conn, int no, String title) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"update article set title = ?"+
						"where article_no = ?")) {
			pstmt.setString(1, title);
			return pstmt.executeUpdate();
		}
	}
	
	public int delete(Connection conn, int no) throws SQLException{
		try (PreparedStatement pstmt = 
				conn.prepareStatement(
						"delete from article where article_no = ?")){
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		}
	}
}