package article.service;

import article.model.Article;

public class PermissionChecker {

	public static boolean canUp_Del(String userId, Article article) {
		return article.getWriter().getId().equals(userId);
	}
}
