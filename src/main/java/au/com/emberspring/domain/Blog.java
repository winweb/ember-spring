package au.com.emberspring.domain;

import au.com.emberspring.ember.EmberLinks;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Blog implements EmberLinks {

    private Long id;
    private boolean active;
    private String name;
    private Category category;
    private Date createDate;
    private List<Post> posts = new ArrayList<Post>(0);
    
    @Id
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIdentityReference(alwaysAsId = true)
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@JsonSetter
	public void setCategory(Long id) {
		if(id != null) {
			this.category = new Category();
			this.category.setId(id);
		}
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@JsonIgnore
	public List<Post> getPosts() {
		return posts;
	}
	
	@JsonSetter
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void addPost(Post post) {
		this.posts.add(post);
		post.setBlog(this);
	}

	@Override
	public ConcurrentMap<String, String> getLinks() {
		ConcurrentMap<String, String> links = new ConcurrentHashMap<String, String>();
		links.put("posts", "posts");
		return links;
	}
    
}
