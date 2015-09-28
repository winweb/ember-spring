package au.com.emberspring.controllers;

import au.com.emberspring.domain.Blog;
import au.com.emberspring.domain.Post;
import au.com.emberspring.ember.EmberModel;
import au.com.emberspring.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blogs")
public class BlogController {
	
	private final transient BlogService blogService;
	
	@Autowired
	public BlogController(final BlogService blogService) {
		this.blogService = blogService;
	}
	
    @RequestMapping(
    		value = "{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EmberModel saveBlog(@RequestBody Blog blog, @PathVariable("id") final long blogId) {
        return new EmberModel.Builder<Blog>(blog)
        		.addMeta("serverSaid", 
        				String.format("Received PUT request for Blog(%d) successfully with %d posts", blogId, blog.getPosts().size()))
        		.build();
    }

    @RequestMapping(
    		value = "{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EmberModel getBlog(@PathVariable("id") final long blogId) {
    	Blog blog = blogService.getBlog(blogId);
        return new EmberModel.Builder<Blog>(blog)
        		.sideLoad(Post.class, blog.getPosts())
        		.addMeta("totalRecords", 100)
        		.build();
    }
    
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EmberModel getBlogs() {
    	List<Blog> blogs = blogService.getBlogs();
    	return new EmberModel.Builder<Blog>(Blog.class, blogs)
    			.addMeta("totalRecords", blogs.size())
    			.build();
    }
    
    @RequestMapping(
    		value = "{id}/posts",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EmberModel getPosts(@PathVariable("id") final long blogId) {
    	List<Post> posts = blogService.getPosts(blogId);
    	return new EmberModel.Builder<Post>(Post.class, posts)
    			.build();
    }

}
