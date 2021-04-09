package article.model;

public class Article {
	
	private Integer number; // 게시글 번호
	private Writer writer;  // 작성자
	private String title;   // 게시들 제목
	private int readCount;  // 읽은사람 수
	
	public Article(Integer number,Writer writer, String title,
			int readCount) {
		this.number = number;
		this.writer = writer;
		this.title = title;
		this.readCount = readCount;
	}

	public Integer getNumber() {
		return number;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public int getReadCount() {
		return readCount;
	}
	
}

