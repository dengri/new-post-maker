package postmaker.dto;

import java.util.List;

import postmaker.Post;

public class Posts {
	private List<Post> posts;

	public Posts() {
	}

	public List<Post> getPosts() {
		return posts;
	}
	
	

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
}
