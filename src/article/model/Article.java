package article.model;

public class Article {
	
	private Integer number;
	private Writer writer;
	private String title;
	private int readCount;
	
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