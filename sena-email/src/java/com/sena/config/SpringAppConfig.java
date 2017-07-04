package com.sena.config;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.jolbox.bonecp.BoneCPDataSource;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:service.properties")
@ComponentScan({"com.sena"})
public class SpringAppConfig {

    @Autowired
    ConfigurableEnvironment env;

    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    //default database,sql server
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BoneCPDataSource ds = new BoneCPDataSource();
        ds.setDriverClass(env.getProperty("ds.driver.classname"));
        ds.setJdbcUrl(env.getProperty("ds.url"));
        ds.setUsername(env.getProperty("ds.username"));
        ds.setPassword(env.getProperty("ds.password"));
        ds.setIdleConnectionTestPeriodInMinutes(240);
        ds.setIdleMaxAgeInMinutes(60);
        ds.setMaxConnectionsPerPartition(5);
        ds.setMinConnectionsPerPartition(1);
        ds.setPartitionCount(3);
        ds.setAcquireIncrement(1);
        ds.setStatementsCacheSize(100);
        return ds;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource);
        return template;
    }

    //secondary database,mysql
    @Bean(name = "mysqlDataSource")
    public DataSource getMysqlDataSource() {
        BoneCPDataSource ds = new BoneCPDataSource();
        ds.setDriverClass(env.getProperty("mysqlds.driver.classname"));
        ds.setJdbcUrl(env.getProperty("mysqlds.url"));
        ds.setUsername(env.getProperty("mysqlds.username"));
        ds.setPassword(env.getProperty("mysqlds.password"));
        ds.setIdleConnectionTestPeriodInMinutes(240);
        ds.setIdleMaxAgeInMinutes(60);
        ds.setMaxConnectionsPerPartition(10);
        ds.setMinConnectionsPerPartition(5);
        ds.setPartitionCount(3);
        ds.setAcquireIncrement(5);
        ds.setStatementsCacheSize(100);
        return ds;
    }

    @Bean(name = "dynamoDBMapper")
    DynamoDBMapper dynamoDBMapper() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient(
                new AWSCredentialsProviderChain(new InstanceProfileCredentialsProvider(),
                        new StaticCredentialsProvider(new BasicAWSCredentials(env.getProperty("aws.accessKey"), env.getProperty("aws.secretKey")))));
        client.setRegion(Region.getRegion(Regions.US_WEST_1));
        return new DynamoDBMapper(client);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "cacheClient")
    public MemCachedClient memCachedClient() {
        // https://github.com/gwhalin/Memcached-Java-Client/blob/master/doc/HOWTO.txt
        MemCachedClient mcc = new MemCachedClient();

        // server list and weights
        String[] servers = {
                "lg-prod-mcach.ggvi7j.cfg.usw1.cache.amazonaws.com:11211"
        };

        // grab an instance of our connection pool
        SockIOPool pool = SockIOPool.getInstance();

        // set the servers and the weights
        pool.setServers(servers);

        // set some basic pool settings
        // 5 initial, 5 min, and 250 max conns
        // and set the max idle time for a conn
        // to 6 hours
        pool.setInitConn(5);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaxIdle(1000 * 60 * 60 * 6);

        // set the sleep for the maint thread
        // it will wake up every x seconds and
        // maintain the pool size
        pool.setMaintSleep(30);

        // set some TCP settings
        // disable nagle
        // set the read timeout to 3 secs
        // and don't set a connect timeout
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setSocketConnectTO(0);

        // initialize the connection pool
        pool.initialize();

        return mcc;
    }
}
