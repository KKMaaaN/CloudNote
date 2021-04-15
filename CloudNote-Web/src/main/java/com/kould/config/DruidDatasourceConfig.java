package com.kould.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@PropertySource("classpath:datasouce/DataBase.properties")
@Configuration
public class DruidDatasourceConfig {

    private String dbUrl ;  //数据库连接地址
    private String driverClass ;    //驱动类名
    private String user ;   //数据库账户名
    private String passWord ;   //数据库账户密码
    private int initialSize;    //初始化提供连接数
    private int minIdle;    //最小维持连接数
    private int maxActive;  //最大连接数
    private int maxWait;    //等待连接获取的最大超时时间

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource() ;
        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(user);
        dataSource.setPassword(passWord);

        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);

        return dataSource ;
    }

    @Value("${dataSource.jdbcUrl}")
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }
    @Value("${dataSource.driverClass}")
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }
    @Value("${dataSource.user}")
    public void setUser(String user) {
        this.user = user;
    }
    @Value("${dataSource.password}")
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    @Value("${dataSource.initialSize}")
    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }
    @Value("${dataSource.minIdle}")
    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }
    @Value("${dataSource.maxActive}")
    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }
    @Value("${dataSource.maxWait}")
    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }
}
