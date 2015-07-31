package com.recommendation.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.recommendation.services.IverticaConnection;

@Service
public class verticaConnection implements IverticaConnection {

	@Value("${vertica.driverClassName}")
	private String driverClassName;

	@Value("${vertica.dbUrl}")
	private String DB_URL;

	@Value("${vertica.user}")
	private String USER;
	
	@Value("${vertica.pass}")
	private String PASS;

	@Override
	public Connection getverticaConnection() {

		Connection conn = null;

		try {
			Class.forName(driverClassName);
			System.out.println("Trying to connect vertica");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			System.out.println("Connected to vertica");
		} catch (Exception e) {

			System.out.println("Error in connecting to vertica");
			e.printStackTrace();
		}

		return conn;

	}

}
