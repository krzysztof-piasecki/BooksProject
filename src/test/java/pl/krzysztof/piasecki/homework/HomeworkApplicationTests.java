package pl.krzysztof.piasecki.homework;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.krzysztof.piasecki.homework.dao.BookDao;

@SpringBootTest
class HomeworkApplicationTests {

	@Autowired
	private BookDao bookDao;
	@Test
	void contextLoads() {
		Assert.assertNotNull(bookDao);
		Assert.assertNotNull(bookDao.getAllBooks());
	}

}
