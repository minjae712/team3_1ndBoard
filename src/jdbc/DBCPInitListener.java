package jdbc;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DBCPInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// web.xml에 지정된 context-param 값중 param-name이 poolConfig인 값을 가져온다.
		String poolConfig = 
				sce.getServletContext().getInitParameter("poolConfig");
		// param 값중 key=value 형식을 처리하기 위해 Properties 생성하여 load 매서드를 실행
		Properties prop = new Properties();
		try {
			prop.load(new StringReader(poolConfig));
		} catch (IOException e) {
			throw new RuntimeException("config load fail", e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
	}

	private void loadJDBCDriver(Properties prop) {
		try {
			// 커넥션 풀에서 사용할 jdbc 드라이버를 로딩
			Class.forName(prop.getProperty("jdbcdriver"));
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}

	private void initConnectionPool(Properties prop) {
		try {
			String jdbcUrl = prop.getProperty("jdbcUrl");
			String username = prop.getProperty("dbUser");
			String pw = prop.getProperty("dbPass");
			
			// 커넥션 패토리를 생성해준다. 커넥션 팩토리를 새로운 커넥션을 생성할때 사용된다
			ConnectionFactory connFactory = 
					new DriverManagerConnectionFactory(jdbcUrl, username, pw);
			
			// DBCP가 커넥션 풀에 커넥션을 보관할때 사용하는 PoolableConnectionFactory를 생성해준다.
			// 실제로 내부적으로 커넥션을 담고있고 커넥션을 관리하는데 기능을 제공한다.(커넥션을 close 하면 종료하지 않고 커넥션 풀에 반환한다.)
			PoolableConnectionFactory poolableConnFactory = 
					new PoolableConnectionFactory(connFactory, null);
			// 커넥션이 유효한지 확인할떄 사용하는 쿼리를 설정한다.
			poolableConnFactory.setValidationQuery(prop.getProperty("validationQuery"));
			// 커넥션 풀의 설정 정보를 저장 GenericObjectPoolConfig을 생성한다.
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			// 유효 커넥션 검사 주기 설정
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			// 풀에 있는 커넥션이 유효한지 검사 유무 설정
			poolConfig.setTestWhileIdle(true);
			// 커넥션 최소 개수 설정
			poolConfig.setMinIdle(Integer.parseInt(prop.getProperty("minIdle")));
			// 커넥션 최대 갯수 설정
			poolConfig.setMaxTotal(Integer.parseInt(prop.getProperty("maxTotal")));

			// 커넥션 풀을 생성해준다. 
			GenericObjectPool<PoolableConnection> connectionPool = 
					new GenericObjectPool<>(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);
			
			// 커넥션 풀을 제공하는 jdbc 드라이버를 등록해준다.
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			// 지정된 URL를 인식하는 드라이버를 흭득한다.
			PoolingDriver driver = (PoolingDriver)
				DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = prop.getProperty("poolName");
			// 풀을 등록해준다.
			driver.registerPool(poolName, connectionPool);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
