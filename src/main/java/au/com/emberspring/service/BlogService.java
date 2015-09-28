package au.com.emberspring.service;

import au.com.emberspring.domain.Blog;
import au.com.emberspring.domain.Post;

import java.util.List;

public interface BlogService {

	Blog getBlog(Long blogId);

	List<Blog> getBlogs();

	List<Post> getPosts(long blogId);

}
