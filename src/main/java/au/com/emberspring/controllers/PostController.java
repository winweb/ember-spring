package au.com.emberspring.controllers;

import au.com.emberspring.domain.Post;
import au.com.emberspring.ember.EmberModel;
import au.com.emberspring.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("posts")
public class PostController {
	
	private final transient BlogService blogService;
	
	@Autowired
	public PostController(final BlogService blogService) {
		this.blogService = blogService;
	}

    @RequestMapping(
    		value = "{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EmberModel savePost(@RequestBody Post post, @PathVariable("id") final long postId) {
        return new EmberModel.Builder<Post>(post)
        		.addMeta("serverSaid", String.format("Received PUT request for Post(%d) successfully", postId))
        		.build();
    }

}
