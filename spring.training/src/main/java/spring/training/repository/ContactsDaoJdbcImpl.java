package spring.training.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.training.model.Contact;

@Repository
public class ContactsDaoJdbcImpl implements ContactsDao {

	private Connection connection;

	@Autowired (required = false)
	private DataSource dataSource;

	public ContactsDaoJdbcImpl() {
	}

	// spring can do DI using this constructor
	public ContactsDaoJdbcImpl(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() throws DaoException, SQLException {
		if (dataSource != null) {
			return dataSource.getConnection();
		}

		if (connection == null) {
			throw new DaoException("Connection is null!");
		}
		return connection;
	}

	// spring can do DI using this setter
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Contact getContact(int id) throws DaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = this.getConnection();
			String sql = "select * from phonebook where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				Contact contact = resultSetToContact(rs);
				return contact;
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return null;
	}

	@Override
	public List<Contact> getAllContacts() throws DaoException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Contact> list = new ArrayList<>();
		try {
			conn = this.getConnection();
			String sql = "select * from phonebook";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Contact contact = resultSetToContact(rs);
				list.add(contact);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		return list;
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

	@Override
	public void addContact(Contact contact) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public void updateContact(Contact contact) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public void deleteContact(int id) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public List<Contact> getContactsByCity(String city) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public Contact getContactByEmail(String email) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

	@Override
	public Contact getContactByPhone(String phone) throws DaoException {
		throw new DaoException("Method not implemented yet!");
	}

}
