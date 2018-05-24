package spring.training.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import spring.training.model.Contact;

@Repository("jdbcTemplateDao")
public class ContactsDaoJdbcTemplateImpl implements ContactsDao {

	@Autowired(required = false)
	JdbcTemplate template;

	@Override
	public void addContact(Contact c) throws DaoException {
		String sql = "insert into phonebook (first_name, last_name, gender, "
				+ "email, phone, address, city, state, country) " + "values (?,?,?,?,?,?,?,?,?);";

		Object[] args = { c.getFirstName(), c.getLastName(), c.getGender(), c.getEmail(), c.getPhone(), c.getAddress(),
				c.getCity(), c.getState(), c.getCountry() };
		template.update(sql, args);
	}

	@Override
	public Contact getContact(int id) throws DaoException {
		String sql = "select * from phonebook where id = ?";
		return template.queryForObject(sql, (rs, index) -> resultSetToContact(rs), id);
	}

	@Override
	public void updateContact(Contact c) throws DaoException {
		String sql = "update phonebook set first_name = ?, last_name = ?, "
				+ "gender = ?, email = ?, phone = ?, address = ?, " + "city = ?, state = ?, country = ? where id = ?";

		Object[] args = { c.getFirstName(), c.getLastName(), c.getGender(), c.getEmail(), c.getPhone(), c.getAddress(),
				c.getCity(), c.getState(), c.getCountry(), c.getId() };
		template.update(sql, args);
	}

	@Override
	public void deleteContact(int id) throws DaoException {
		template.update("delete from phonebook where id = ?", id);
	}

	@Override
	public List<Contact> getAllContacts() throws DaoException {
		String sql = "select * from phonebook";
		return template.query(sql, (rs, index) -> resultSetToContact(rs));
	}

	@Override
	public List<Contact> getContactsByCity(String city) throws DaoException {
		String sql = "select * from phonebook where city = ?";
		return template.query(sql, (rs, index) -> resultSetToContact(rs), city);
	}

	@Override
	public Contact getContactByEmail(String email) throws DaoException {
		String sql = "select * from phonebook where email = ?";
		return template.queryForObject(sql, (rs, index) -> resultSetToContact(rs), email);
	}

	@Override
	public Contact getContactByPhone(String phone) throws DaoException {
		String sql = "select * from phonebook where phone = ?";
		return template.queryForObject(sql, (rs, index) -> resultSetToContact(rs), phone);
	}

	private Contact resultSetToContact(ResultSet rs) throws SQLException {
		Contact contact = new Contact();
		contact.setId(rs.getInt("id"));
		contact.setFirstName(rs.getString("first_name"));
		contact.setLastName(rs.getString("last_name"));
		contact.setGender(rs.getString("gender"));
		contact.setEmail(rs.getString("email"));
		contact.setPhone(rs.getString("phone"));
		contact.setAddress(rs.getString("address"));
		contact.setCity(rs.getString("city"));
		contact.setState(rs.getString("state"));
		contact.setCountry(rs.getString("country"));
		return contact;
	}

}
