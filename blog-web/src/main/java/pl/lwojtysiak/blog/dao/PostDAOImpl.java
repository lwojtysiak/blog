package pl.lwojtysiak.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import pl.lwojtysiak.blog.db.ConnectionFactory;
import pl.lwojtysiak.blog.model.Post;

/**
 * DAO implementation. Provides connection with database for Posts.
 * 
 * @author lwojtysiak
 * 
 */
public class PostDAOImpl implements PostDAO {
	/** Connection timeout. */
	private static int TIMEOUT = 30;

	/**
	 * Constructor. Prepares database for Messenger - creates table if not
	 * exists.
	 */
	public PostDAOImpl() {
		prepareTableIfNotExists();
	}

	/**
	 * Gets database connection.
	 * 
	 * @return connection object
	 * @throws SQLException
	 *             Exception thrown when creating connection to database failed.
	 */
	private Connection getConnection() throws SQLException {
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();

		return conn;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Post> findAll() {
		List<Post> messages = new LinkedList<Post>();
		Post message = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT id,author,title,insert_date,summary FROM posts ORDER BY id desc");
			stmt.setQueryTimeout(TIMEOUT);
			rs = stmt.executeQuery();

			while (rs.next()) {
				message = new Post();
				message.setId(rs.getLong(1));
				message.setAuthor(rs.getString(2));
				message.setTitle(rs.getString(3));
				message.setInsertDate(rs.getDate(4));
				message.setSummary(rs.getString(5));

				messages.add(message);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ignore) {
			}
		}

		return messages;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPostContent(long id) {
		String message = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT content FROM posts WHERE id=?");
			stmt.setLong(1, id);
			stmt.setQueryTimeout(TIMEOUT);
			rs = stmt.executeQuery();

			while (rs.next()) {
				message = rs.getString(1);
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ignore) {
			}
		}

		return message;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Post getById(long id) {
		Post message = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT author,title,insert_date,summary,content FROM posts WHERE id=?");
			stmt.setLong(1, id);
			stmt.setQueryTimeout(TIMEOUT);
			rs = stmt.executeQuery();

			while (rs.next()) {
				message = new Post();
				message.setId(id);
				message.setAuthor(rs.getString(1));
				message.setTitle(rs.getString(2));
				message.setInsertDate(rs.getDate(3));
				message.setSummary(rs.getString(4));
				message.setText(rs.getString(5));
			}
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ignore) {
			}
		}

		return message;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(Post message) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();
			stmt = conn
					.prepareStatement("INSERT INTO posts (author,title,insert_date,summary,content) VALUES(?,?,?,?,?)");

			stmt.setQueryTimeout(TIMEOUT);
			stmt.setString(1, message.getAuthor());
			stmt.setString(2,message.getTitle());
			stmt.setDate(3, new java.sql.Date(message.getInsertDate().getTime()));
			stmt.setString(4, message.getSummary());
			stmt.setString(5, message.getText());

			int result = stmt.executeUpdate();

			if (result > 0)
				return true;
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ignore) {
			}
		}
		
		return false;
	}

	/**
	 * Prepares database for Blog - creates table if not exists.
	 */
	private void prepareTableIfNotExists() {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.setQueryTimeout(TIMEOUT);
			stmt.execute("CREATE TABLE IF NOT EXISTS posts (id INTEGER PRIMARY KEY, author text, title text NOT NULL, insert_date date NOT NULL, summary text NOT NULL, content text NOT NULL)");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ignore) {
			}
		}
	}
}
