package article.service;

//import java.util.Map;   

import article.model.Writer;  


//public으로 WriteRequest 클래스  private로 writer, title, content 지정
public class WriteRequest {
	
	private Writer writer;   
	private String title;  
	private String content;

	// public으로 WriteRequest생성자 writer, title, content 지정
	public WriteRequest(Writer writer, String title, String content) {
		this.writer = writer;
		this.title = title;
		this.content = content;
	}

	// get으로 getWriter() 메서드 writer리턴
	public Writer getWriter() {
		return writer;
	}
	
	// get으로 getTitle() 메서드 title리턴
	public String getTitle() {
		return title;
	}
	
	// get으로 getContent() 메서드 content리턴
	public String getContent() {
		return content;
	}

	// validate메서드 Map활용 errors에러 확인 
	// title 이 null값이거나 공백일경우
	// 에러 메시지 체크 띄우기
//	public void validate(Map<String, Boolean> errors) {
//		if (title == null || title.trim().isEmpty()) {
//			errors.put("title", Boolean.TRUE);
//		}
//	}
}