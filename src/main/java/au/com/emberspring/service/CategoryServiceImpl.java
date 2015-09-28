package au.com.emberspring.service;

import au.com.emberspring.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private final FixtureDataDao fixtureDataDao;
	
	@Autowired
	public CategoryServiceImpl(final FixtureDataDao fixtureDataDao) {
		this.fixtureDataDao = fixtureDataDao;
	}

	@Override
	public Category getCategory(Long categoryId) {
		return fixtureDataDao.getCategory(categoryId);
	}

}
