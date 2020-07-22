/*
 * Copyright 2020 The Playce Project.
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * SangCheon Park	Jul 22, 2020	    First Draft.
 */
package com.playce.api.skeleton;

import org.apache.derby.drda.NetworkServerControl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * <pre>
 * Playce SpringBoot Application
 *
 * Application 구동 시 DB 초기화가 필요한 경우 다음 두 가지 중 하나를 선택하여 옵션으로 입력한다.
 * <ul>
 *     <li>-Dspring.jpa.hibernate.ddl-auto=create</li>
 *     <li>-Dspring.datasource.initialization-mode=always</li>
 * </ul>
 *
 * Application 구동 시 Derby DB Server의 포트 지정이 필요한 경우 아래 옵션을 입력한다.
 * <ul>
 *     <li>-Dplayce.derby.server.port=1527</li>
 * </ul>
 * </pre>
 *
 * @author SangCheon Park
 * @version 1.0
 */
@SpringBootApplication
@EnableScheduling
public class Application extends SpringBootServletInitializer {

    /**
     * The constant server.
     */
    private static NetworkServerControl server;

    /**
     * The constant properties.
     */
    private static Properties properties;

    /**
     * <pre>
     * The entry point of application.
     * </pre>
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        startDerby();
        SpringApplication.run(Application.class, args);
    }

    /**
     * <pre>
     * On startup.
     * </pre>
     *
     * @param servletContext the servlet context
     * @throws ServletException the servlet exception
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        startDerby();
        super.onStartup(servletContext);
    }

    /**
     * <pre>
     * Configure spring application builder.
     * </pre>
     *
     * @param builder the builder
     * @return the spring application builder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class)
                /*.bannerMode(org.springframework.boot.Banner.Mode.CONSOLE)*/;
    }

    /**
     * <pre>
     * Start derby.
     *
     * If run as a web application, main() method will not be invoked.
     * If run as a standalone application, onStartup() method will not be invoked.
     * </pre>
     */
    private static void startDerby() {
        if (server == null) {
            try {
                String jdbcUrl = getProperty("spring.datasource.url", "jdbc:derby://localhost:1527/playce;create=true;");
                String driverClass = getProperty("spring.datasource.driver-class-name", "org.apache.derby.jdbc.ClientDriver");
                String ddlAutoCreate = getProperty("spring.jpa.hibernate.ddl-auto", "none");
                String username = getProperty("spring.datasource.username", "playce");
                String password = getProperty("spring.datasource.password", "playce");

                if (driverClass.contains("mysql") || driverClass.contains("maria")) {
                    Class.forName(driverClass).newInstance();
                    Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                    Statement stmt = connection.createStatement();

                    ResultSet results = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = '" + connection.getCatalog() + "';");

                    List<String> tableNames = new ArrayList<String>();
                    while (results.next()) {
                        tableNames.add(results.getString("table_name").toUpperCase());
                    }

                    if (!tableNames.contains("ROLE") || !tableNames.contains("MEMBER") ||
                            !tableNames.contains("CLUSTER") || !tableNames.contains("DOMAIN")) {

                        System.out.println(new Date() + " : Playce database NOT initialized. Starting auto configuration for Playce database.");

                        if (ddlAutoCreate.equals("none")) {
                            System.setProperty("spring.datasource.initialization-mode", "always");

                            if (driverClass.contains("maria")) {
                                System.setProperty("spring.datasource.platform", "maria");
                            } else {
                                System.setProperty("spring.datasource.platform", "mysql");
                            }
                        }
                    } else {
                        System.out.println(new Date() + " : Playce database already initialized.");
                    }
                } else if (driverClass.contains("postgres")) {
                    Class.forName(driverClass).newInstance();
                    Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                    Statement stmt = connection.createStatement();

                    ResultSet results = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_type='BASE TABLE' AND table_schema = 'public' AND table_catalog = '" + connection.getCatalog() + "';");

                    List<String> tableNames = new ArrayList<String>();
                    while (results.next()) {
                        tableNames.add(results.getString("table_name").toUpperCase());
                    }

                    if (!tableNames.contains("ROLE") || !tableNames.contains("MEMBER") ||
                            !tableNames.contains("CLUSTER") || !tableNames.contains("DOMAIN")) {

                        System.out.println(new Date() + " : Playce database NOT initialized. Starting auto configuration for Playce database.");

                        if (ddlAutoCreate.equals("none")) {
                            System.setProperty("spring.datasource.initialization-mode", "always");
                            System.setProperty("spring.datasource.platform", "postgres");
                        }
                    } else {
                        System.out.println(new Date() + " : Playce database already initialized.");
                    }
                } else if (driverClass.contains("derby")) {
                    String port = System.getProperty("playce.derby.server.port", "1527");

                    // Start Derby DB as network server mode
                    server = new NetworkServerControl(InetAddress.getByName("0.0.0.0"), Integer.parseInt(port));
                    server.start(new PrintWriter(System.out));

                    Class.forName(driverClass).newInstance();
                    Connection connection = DriverManager.getConnection(jdbcUrl);
                    Statement stmt = connection.createStatement();
                    ResultSet results = stmt.executeQuery("SELECT TABLENAME FROM SYS.SYSTABLES WHERE TABLETYPE = 'T'");

                    List<String> tableNames = new ArrayList<String>();
                    while (results.next()) {
                        tableNames.add(results.getString("TABLENAME").toUpperCase());
                    }

                    if (!tableNames.contains("ROLE") || !tableNames.contains("MEMBER") ||
                            !tableNames.contains("CLUSTER") || !tableNames.contains("DOMAIN")) {

                        System.out.println(new Date() + " : Playce database NOT initialized. Starting auto configuration for Playce database.");

                        if (ddlAutoCreate.equals("none")) {
                            System.setProperty("spring.datasource.initialization-mode", "always");
                            System.setProperty("spring.datasource.platform", "derby");
                        }
                    } else {
                        System.out.println(new Date() + " : Playce database already initialized.");
                    }
                } else {
                    throw new Exception(driverClass + " is unsupported DB.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println();
        }
    }

    /**
     * Load properties.
     */
    private static String getProperty(String key, String defaultValue) throws Exception {
        if (properties == null) {
            properties = new Properties();
            properties.load(Application.class.getClassLoader().getResourceAsStream("application.properties"));
        }

        String value = System.getProperty(key);

        if (value == null || value.equals("")) {
            value = properties.getProperty(key, defaultValue);
        }

        return value;
    }
}
//end of Application.java