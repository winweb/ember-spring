package au.com.emberspring.service;

import au.com.emberspring.domain.Blog;
import au.com.emberspring.domain.Category;
import au.com.emberspring.domain.Post;

import java.util.List;


public interface FixtureDataDao {

	Blog getBlog(Long blogId);

	List<Blog> getAllBlogs();

	List<Post> getPostsForBlog(long blogId);

	Category getCategory(Long categoryId);

}
