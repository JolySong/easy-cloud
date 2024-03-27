package cn.iomc.common.config;

import cn.hutool.core.date.DateTime;
import cn.iomc.common.utils.UserInfoContext;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author song
 * @Date 2024/3/28 04:27
 * @Version 1.0
 */
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {

    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor(){
        return new MybatisPlusInterceptor();
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        //默认未删除
        setFieldValByName("delFlag",0, metaObject);
        //判断添加/更新的时候是否给他赋值
        Object createTime1 = getFieldValByName("createTime", metaObject);
        if (createTime1 == null) {
            //this.strictInsertFill(metaObject, "createdTime", Long.class,System.currentTimeMillis());
            //创建时间默认当前时间
            setFieldValByName("createTime", DateTime.now(), metaObject);
        }

        setFieldValByName("createUserId", UserInfoContext.getUserInfo().getUserId(), metaObject);
        setFieldValByName("createUserName", UserInfoContext.getUserInfo().getUsername(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        //创建时间默认当前时间
        setFieldValByName("updatedTime", DateTime.now(), metaObject);
        setFieldValByName("updateUserId", UserInfoContext.getUserInfo().getUserId(), metaObject);
        setFieldValByName("updateUserName", UserInfoContext.getUserInfo().getUsername(), metaObject);
    }
}
