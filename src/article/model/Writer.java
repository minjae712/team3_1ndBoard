package article.model;

public class Writer {
	
	private String id;   // 작성자 아이디
	private String name; // 작성자 이름
	
	public Writer(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
