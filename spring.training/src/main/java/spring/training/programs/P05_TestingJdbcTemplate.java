package spring.training.programs;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import spring.training.cfg.AppConfig4;

public class P05_TestingJdbcTemplate {

	static JdbcTemplate template;

	public static void main(String[] args) {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig4.class);
		template = ctx.getBean(JdbcTemplate.class);

		// printContactsCount();
		// printContactDetails(12); // 12 --> id
		printContactsFromCity("Dallas"); // Dallas --> city
		ctx.close();

	}

	static void printContactsFromCity(String city) {
		String sql = "select * from phonebook where city = ?";
		// use queryForList() or query() when the SQL returns 'n' rows, 'm' columns
		List<Map<String, Object>> list = template.queryForList(sql, city);
		
		for(Map<String, Object> data: list) {
			System.out.printf("%s, %s %s\n", data.get("first_name"), 
					data.get("address"), data.get("city"));
		}
	}

	static void printContactDetails(int id) {
		String sql = "select * from phonebook where id = ?";
		// use queryForMap() when the query returns 1 row 'n' columns
		Map<String, Object> data = template.queryForMap(sql, id);
		System.out.println(data);
	}

	static void printContactsCount() {
		String sql = "select count(*) from phonebook";
		
		// use queryForObject() when the query returns 1 row 1 column
		int cc = template.queryForObject(sql, Integer.class);
		System.out.println("Contacts count = " + cc);
	}
}





