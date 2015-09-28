package au.com.emberspring.controllers;

import au.com.emberspring.domain.Category;
import au.com.emberspring.ember.EmberModel;
import au.com.emberspring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
public class CategoryController {
	
	private final transient CategoryService categoryService;
	
	@Autowired
	public CategoryController(final CategoryService categoryService) {
		this.categoryService = categoryService;
	}

    @RequestMapping(
    		value = "{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EmberModel savePost(@PathVariable("id") final long categoryId) {
    	Category category = categoryService.getCategory(categoryId);
        return new EmberModel.Builder<Category>(category)
        		.addMeta("serverSaid", String.format("Yay, ember-data is lazy loading the category for us automatically"))
        		.build();
    }

}
