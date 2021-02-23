package com.cc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @创建人 池臣
 * @创建时间 2020/12/18
 * @描述 mybatis配置
 */
@Configuration
@MapperScan("com.**.mapper")
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
public class MybatisConfig {

    @Value("${dataSource.url}")
    private String dataSourceUrl;

    @Value("${dataSource.driverClassName}")
    private String driverClassName;

    @Value("${dataSource.username}")
    private String username;

    @Value("${dataSource.password}")
    private String password;

    /**
     * 数据源配置
     *
     * @return
     */
    @Bean(name = "druidDataSource")
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        druidDataSource.setUrl(dataSourceUrl);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        //初始化连接池的连接数量 大小，最小，最大
        druidDataSource.setInitialSize(5);
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxActive(20);
        //配置获取连接等待超时的时间
        druidDataSource.setMaxWait(60000);
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        //配置一个连接在池中最小生存的时间，单位是毫秒
        druidDataSource.setMinEvictableIdleTimeMillis(30000);
        //用来检测连接是否有效的sql，要求是一个查询语句
        druidDataSource.setValidationQuery("SELECT 1 FROM DUAL");
        return druidDataSource;
    }

    /**
     * sqlSession配置
     *
     * @param ds
     * @param mapperLocations
     * @param paginationInterceptor
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("druidDataSource") DruidDataSource ds, @Value("classpath*:com/cc/mapper/**/*.xml") Resource[] mapperLocations, PaginationInterceptor paginationInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(ds);
        bean.setMapperLocations(mapperLocations);
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setJdbcTypeForNull(JdbcType.NULL);
        //如果项目中没有遵守驼峰,就设置为false
        //mybatisConfiguration.setMapUnderscoreToCamelCase(false);
        //关闭二级缓存
        mybatisConfiguration.setCacheEnabled(false);
        //控制台打印SQL
        mybatisConfiguration.setLogImpl(StdOutImpl.class);
        bean.setConfiguration(mybatisConfiguration);
        //设置分页
        bean.setPlugins(paginationInterceptor);
        return bean.getObject();
    }

    /**
     * mybatisPlus分页
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
         paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
         paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

}
