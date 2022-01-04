package com.y1ph.easy.design.data.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

/**
 * Mybatis-Plus自动化配置
 *
 * @author WFT
 * @since 2022/1/4
 */
@Configuration
@ComponentScan
@EnableTransactionManagement
public class MyBatisAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(value = ISqlInjector.class)
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector();
    }

    /**
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     *
     * @return {@link PaginationInterceptor}
     */
    @Bean
    @ConditionalOnMissingBean(value = PaginationInterceptor.class)
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor().setOverflow(true);
    }

    /**
     * 自动填充插件
     *
     * @return {@link MetaObjectHandler}
     */
    @Bean
    @ConditionalOnMissingBean(value = MetaObjectHandler.class)
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                //  创建/最后修改时间
                LocalDateTime now = LocalDateTime.now();
                this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, now);
                this.strictInsertFill(metaObject, "modifyTime", LocalDateTime.class, now);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                //  最后修改时间
                this.strictInsertFill(metaObject, "modifyTime", LocalDateTime.class, LocalDateTime.now());
            }
        };
    }

}
