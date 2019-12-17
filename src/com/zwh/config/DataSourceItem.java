package com.zwh.config;

public class DataSourceItem {
	
	private String name;
	
	private String driverClassName;
	
	private String url;
	
	private String userName;
	
	private String passWord;
	// 设置连接池的最大连接数
	private Integer maxPoolSize;
	// 设置连接池的最小连接数
	private Integer minPoolSize;
	// 初始化时获取连接个数
	private Integer initialPoolSize;
	// 获得连接的最大等待时间(两次连接的间隔)
	private Integer acquireRetryDelay; //单位 亳秒
	// JDBC的标准参数，用以控制数据源内加载的PreparedStatements数量。但由于预缓存的statements属于单个connection而不是整个连接池。所以设置这个参数需要考虑到多方面的因素。如果maxStatements与maxStatementsPerConnection均为0，则缓存被关闭。Default:0 
	private Integer maxStatements;
	// maxStatementsPerConnection定义了连接池内单个连接所拥有的最大缓存statements数
	private Integer maxStatementsPerConnection;
	// 最大空闲时间,超时未使用则连接被丢弃。若为0则永不丢弃。Default: 0 maxIdleTime
	private Integer maxIdleTime;   // 单位 亳秒
	// 当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3 acquireIncrement
	private Integer acquireIncrement;
	// 间隔多少时间检查所有连接池中的空闲连接。Default: 0 idleConnectionTestPeriod
	private Integer idleConnectionTestPeriod;// 单位 秒
	// 连接关闭时默认将所有未提交的操作回滚。Default: false autoCommitOnClose
	private boolean autoCommitOnClose;
	// 定义在从数据库获取新连接失败后重复尝试的次数。Default: 30 acquireRetryAttempts
	private Integer acquireRetryAttempts;

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getMinPoolSize() {
		return minPoolSize;
	}

	public void setMinPoolSize(Integer minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	public Integer getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(Integer maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public Integer getMaxStatements() {
		return maxStatements;
	}

	public void setMaxStatements(Integer maxStatements) {
		this.maxStatements = maxStatements;
	}

	public Integer getInitialPoolSize() {
		return initialPoolSize;
	}

	public void setInitialPoolSize(Integer initialPoolSize) {
		this.initialPoolSize = initialPoolSize;
	}

	public Integer getMaxIdleTime() {
		return maxIdleTime;
	}

	public void setMaxIdleTime(Integer maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAcquireRetryDelay() {
		return acquireRetryDelay;
	}

	public void setAcquireRetryDelay(Integer acquireRetryDelay) {
		this.acquireRetryDelay = acquireRetryDelay;
	}

	public Integer getMaxStatementsPerConnection() {
		return maxStatementsPerConnection;
	}

	public void setMaxStatementsPerConnection(Integer maxStatementsPerConnection) {
		this.maxStatementsPerConnection = maxStatementsPerConnection;
	}

	public Integer getAcquireIncrement() {
		return acquireIncrement;
	}

	public void setAcquireIncrement(Integer acquireIncrement) {
		this.acquireIncrement = acquireIncrement;
	}

	public Integer getIdleConnectionTestPeriod() {
		return idleConnectionTestPeriod;
	}

	public void setIdleConnectionTestPeriod(Integer idleConnectionTestPeriod) {
		this.idleConnectionTestPeriod = idleConnectionTestPeriod;
	}

	public boolean isAutoCommitOnClose() {
		return autoCommitOnClose;
	}

	public void setAutoCommitOnClose(boolean autoCommitOnClose) {
		this.autoCommitOnClose = autoCommitOnClose;
	}
	
	public Integer getAcquireRetryAttempts() {
		return acquireRetryAttempts;
	}

	public void setAcquireRetryAttempts(Integer acquireRetryAttempts) {
		this.acquireRetryAttempts = acquireRetryAttempts;
	}

}
