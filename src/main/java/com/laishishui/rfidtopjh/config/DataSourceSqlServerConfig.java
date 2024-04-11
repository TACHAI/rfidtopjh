package com.laishishui.rfidtopjh.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceSqlServerConfig
 * @Author https://github.com/TACHAI
 * @Email tc1206966083@gmail.com
 * @Date 2024-04-11 9:03
 */

//@Configuration
//@MapperScan(basePackages = "com.laishishui.dao.sqlServer", sqlSessionTemplateRef = "sqlServerSqlSessionTemplate")
public class DataSourceSqlServerConfig {


    private final static Logger logger = LoggerFactory.getLogger(DataSourceSqlServerConfig.class);
    @Bean(name="sqlServerDataSource")
    @ConfigurationProperties(prefix = "sqlserver.spring.datasource.sqlserver")
    public DataSource testDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean(name="sqlServerSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("sqlServerDataSource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        /*加载mybatis全局配置文件
         *加载所有的mapper.xml映射文件
         */
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlServerMappers/*.xml"));
//添加插件
// bean.setPlugins(new Interceptor[]{pageHelper()});
        logger.info("加载sqlServer数据库连接......");
        return bean.getObject();
    }
    @Bean(name="sqlServerTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("sqlServerDataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name="sqlServerSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlServerSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


//暂未使用
// @Bean
// public PageHelper pageHelper(){
//// logger.info("MyBatis分页插件PageHelper");
// //分页插件
// PageHelper pageHelper = new PageHelper();
// Properties properties = new Properties();
// properties.setProperty("offsetAsPageNum", "true");
// properties.setProperty("rowBoundsWithCount", "true");
// properties.setProperty("reasonable", "true");
// properties.setProperty("supportMethodsArguments", "true");
// properties.setProperty("returnPageInfo", "check");
// properties.setProperty("params", "count=countSql");
// pageHelper.setProperties(properties);
//
// return pageHelper;
// }


}
