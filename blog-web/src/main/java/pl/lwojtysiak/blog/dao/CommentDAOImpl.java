package pl.lwojtysiak.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import pl.lwojtysiak.blog.db.ConnectionFactory;
import pl.lwojtysiak.blog.model.Comment;

/**
 * DAO implementation. Provides connection with database for Posts.
 * 
 * @author lwojtysiak
 * 
 */
public class CommentDAOImpl implements CommentDAO {
	/** Connection timeout. */
	private static int TIMEOUT = 30;

	/**
	 * Constructor. Prepares database for Comments - creates table if not
	 * exists.
	 */
	public CommentDAOImpl() {
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
	public List<Comment> getByPostId(long postId) {
		List<Comment> comments = new LinkedList<Comment>();
		Comment comment = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn
					.prepareStatement("SELECT id,author,insert_date,content FROM comments WHERE pid=? ORDER BY id DESC");
			stmt.setLong(1, postId);
			stmt.setQueryTimeout(TIMEOUT);
			rs = stmt.executeQuery();

			while (rs.next()) {
				comment = new Comment();
				comment.setId(rs.getLong(1));
				comment.setAuthor(rs.getString(2));
				comment.setInsertDate(rs.getDate(3));
				comment.setText(rs.getString(4));
				comment.setPostId(postId);

				comments.add(comment);
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

		return comments;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfCommentsByPostId(long postId) {
		int numberOfComments = 0;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stmt = conn
					.prepareStatement("SELECT COUNT(id) FROM comments WHERE pid=?");
			stmt.setLong(1, postId);
			stmt.setQueryTimeout(TIMEOUT);
			rs = stmt.executeQuery();

			while (rs.next()) {
				numberOfComments = rs.getInt(1);
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

		return numberOfComments;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean save(Comment comment) {
		Connection conn = null;
		PreparedStatement stmt = null;

		if (comment.getPostId() == null)
			return false;

		try {
			conn = getConnection();
			stmt = conn
					.prepareStatement("INSERT INTO comments (author,insert_date,content,pid) VALUES(?,?,?,?)");

			stmt.setQueryTimeout(TIMEOUT);
			stmt.setString(1, comment.getAuthor());
			stmt.setDate(2,
					new java.sql.Date(comment.getInsertDate().getTime()));
			stmt.setString(3, comment.getText());
			stmt.setLong(4, comment.getPostId());

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
			stmt.execute("CREATE TABLE IF NOT EXISTS comments (id INTEGER PRIMARY KEY, author text, insert_date date NOT NULL, content text NOT NULL, pid INTEGER NOT NULL, FOREIGN KEY(pid) REFERENCES posts(id))");
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
