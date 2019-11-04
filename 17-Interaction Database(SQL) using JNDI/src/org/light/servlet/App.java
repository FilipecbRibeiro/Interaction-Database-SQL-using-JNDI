package org.light.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/App")
public class App extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/project") // helps to import and get access to the external resource file
	// we will use annotation to help us import a resource, in this case the
	// context.xml inside of WebContent/META-INF

	private DataSource dataSource;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// STEP 1: Initialize connection objects
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		PrintWriter out = response.getWriter();
		try {
			connection = dataSource.getConnection();
			// STEP 2: Create a SQL statements string
			String query = "Select * from users";
			statement = connection.createStatement();
			// STEP 3: Execute SQL query;
			resultSet = statement.executeQuery(query);
			// STEP 4: Process the result set;
			while (resultSet.next()) {
				out.print("<br>" + resultSet.getString("email"));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		// to do the 4 steps we will need a DataSource Object and we need access to the
		// resource using @Resource annotation
		// THIS IS A PRE-REQUISITE!

	}

}
