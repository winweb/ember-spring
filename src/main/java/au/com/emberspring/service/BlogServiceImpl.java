package au.com.emberspring.service;

import au.com.emberspring.domain.Blog;
import au.com.emberspring.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	private final FixtureDataDao fixtureDataDao;
	
	@Autowired
	public BlogServiceImpl(final FixtureDataDao fixtureDataDao) {
		this.fixtureDataDao = fixtureDataDao;
	}
	
	@Override
	public Blog getBlog(Long blogId) {
		return fixtureDataDao.getBlog(blogId);
	}

	@Override
	public List<Blog> getBlogs() {
		return fixtureDataDao.getAllBlogs();
	}

	@Override
	public List<Post> getPosts(long blogId) {
		return fixtureDataDao.getPostsForBlog(blogId);
	}

}
