package article.service;

import java.util.List;

import article.model.Article;

public class ArticlePage {
//				게시글 페이지 객체
	
	
	private int totalArticle;//						전체 게시글 개수
	private int currentPage;//						사용자가 요청한 페이지 번호
	private List<Article> content;//				전체 게시글 목록
	
	private int totalPages;//						전체 페이지 개수
	private int startPage;//						시작 페이지 번호
	private int endPage;//							끝 페이지 번호

	public ArticlePage(int totalArticle, int currentPage,int size, List<Article> content) {
		
		this.totalArticle = totalArticle;
		this.currentPage = currentPage;
		this.content = content;
		
		if(totalArticle == 0) {//					만약 전체 게시글 개수가 없다면
			totalPages = 0;//						전체 페이지 개수를 0
			startPage = 0;//						시작 페이지 번호를 0
			endPage = 0;//							끝 페이지 번호를 0으로 설정한다.
		
		}else {//									그렇지 않다면(게시글이 존재한다면)
			
			
//			 현재 게시글 수로부터 총 페이지 수를 구한다.
			totalPages = totalArticle/size;//		전체 페이지 개수는, 전체 게시글 개수/10 으로 한다.
			if(totalArticle % size > 0) {//			그리고 만약 나눈 값의 나머지가 1보다 크다면
				totalPages++;//						totalPages에 1을 더한다.(하나의 Page는 10개의 게시글을 담는다.)
			
			}//										ex) 52개의 게시물이 있다고 하면, totalArticle은 52가 된다.
			//										하지만 52개의 게시물을 하나의 웹문서안에는 표현하기 힘드므로 페이지 수를 정하는데
			//										이 때, 페이지수는 totalPages이다.52개의 게시물을 10으로 나누면(Dao의 select에서 가져오는 게시글의 총 수가 10개씩이므로)
			//										5개의 페이지가 된다. 하지만 총 보여져야 하는 페이지는 1~10,11~20,21~30,31~40,41~50 의 5개 페이지 수 + 2개의 게시물이 있는 페이지 수
			//										이므로, 나눈 몫(10단위에서 떨어져나오는 게시글 개수)이 1개 이상이라면 총 6개의 페이지가 보여져야 하므로 +1이 붇는것이다.
			
//			 현재페이지로부터 시작페이지를 구한다.
			int modVal = currentPage % 5;//			그 후 사용자가 요청한 페이지 번호를 5로 나눈 후,그 나머지값을 modVal에 대입하고
			startPage = currentPage / 5 * 5 + 1;//	사용자가 요청한 페이지 번호를 5로 나눈후,다시 5를 곱하고, 그 값에 1을 더한다.
			if(modVal == 0) {//						만약 modVal이 0과 같다면
				startPage -= 5;//					시작 페이지 번호에 5를 뺀후 재대입하고,
			}
			//										ex) 현재 보고있는게 7페이지라면, [6][7][8][9][10] 이 나와야한다. 여기서 startPage는 6이 되어야 한다.
			//										modVal에 나눈 나머지,2를 대입하고 , currentPage를 5로 나누어 나머지는 버리고 몫만 구한 후 다시 5를 곱해준다.(그러면 5의 배수(또는 0)가 된다.)
			//										그 수에 첫번째 인덱스는 (0,5,10,15...)이 아닌 (1,6,11,16...)이므로, +1을 더해주어 startPage에 대입하는 것이다.
			//										하지만 5의 배수는 페이지수의 마지막이므로, 나누기 전의 페이지가 6~9 인 페이지와는 다르게 다음 페이지로 넘어가버리는 값이 나와버리므로
			//										구한 값(startPage)에 5를 뺀 값을 재대입한다.
//			현재 페이지로부터 마지막페이지를 구한다.
			endPage = startPage + 4;//				끝 페이지 번호는 시작페이지 번호에 4를 더한값으로 설정한다.
			if(endPage > totalPages) {//			만약 끝페이지 번호가 전체 페이지 개수보다 크다면
				endPage = totalPages;//				전체페이지 개수만큼의 수를 끝 페이지 번호에 대입하여 서로 맞춰준다.
			}
			//										ex)마지막페이지수는 앞에서 구한 시작페이지 +4로 구한다.
			//										하지만 총 페이지가 5로 딱 나누어지기 때문에 총 페이지수는 3개인데, 마지막페이지는 5,10,15... 가 되므로
		}//											총 페이지수를 넘어가는 경우엔, 끝페이지와 총 페이지수를 맞춰주는것이다.
	}
	//												~getter
	public int getTotal() {
		return totalArticle;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Article> getContent() {
		return content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	//												/~getter
	
	
	
	public boolean hasNoArticles() {//				전체 게시글 개수가 0 이라면 true를 반환하는 메서드.(true 라는건 "게시글이 없다" 라고 알려준다.)
		return totalArticle == 0;
	}
	
	public boolean hasArticles() {//				전체 게시글 개수가 1개 이상이라면 true를 반환하는 메서드.(true 라는건 "게시글이 있다" 라고 알려준다.)
		return totalArticle > 0;
	}

	
	
}
