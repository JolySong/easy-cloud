package cn.iomc.common.config;

import cn.hutool.core.date.DateTime;
import cn.iomc.common.utils.RequestDataHelper;
import cn.iomc.common.utils.UserInfoContext;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Author song
 * @Date 2024/3/28 04:27
 * @Version 1.0
 */
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {


    /**
     * 批量拦截器
     * @return
     */
    @Bean
    public SpiceSqlInjector spiceSqlInjector() {
        return new SpiceSqlInjector();
    }

    /**
     * mybatisPlus插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        DynamicTableNameInnerInterceptor dyNameInterceptor
                = new DynamicTableNameInnerInterceptor();

        dyNameInterceptor.setTableNameHandler((sql, tableName) -> {
            // 获取参数方法
            Map<String, Object> paramMap = RequestDataHelper.getRequestData();
            if (CollectionUtils.isNotEmpty(paramMap)) {
//                paramMap.forEach((k, v) -> System.err.println(k + "----" + v));
                // 根据获取传递的参数组装动态表名
                return handleDyTableName(tableName, paramMap);
            }
            return tableName;
        });
        // 添加动态表名插件
        interceptor.addInnerInterceptor(dyNameInterceptor);

        // 防全表更新与删除插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

        // 添加分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 处理删除动态表名
     *
     * @param tableName
     * @param paramMap
     * @return
     */
    @Nullable
    private static String handleDyTableName(String tableName, Map<String, Object> paramMap) {
        Object operation = paramMap.get("operation");

        // 不需要动态表名操作
        if (null == operation) {
            return tableName;
        }

        String suffix = paramMap.get("suffix").toString();
        return tableName + suffix;
    }

    @Override
    public void insertFill(MetaObject metaObject) {

        Object delFlag = getFieldValByName("delFlag", metaObject);
        if (delFlag == null) {
            // 默认未删除
            setFieldValByName("delFlag",0, metaObject);
        }

        // 判断添加、更新的时候是否赋值
        Object createTime1 = getFieldValByName("createTime", metaObject);
        if (createTime1 == null) {
            //this.strictInsertFill(metaObject, "createdTime", Long.class,System.currentTimeMillis());
            //创建时间默认当前时间
            setFieldValByName("createTime", DateTime.now(), metaObject);
        }

        setFieldValByName("createUserId", UserInfoContext.getUserInfo().getUserId(), metaObject);
        setFieldValByName("createUserName", UserInfoContext.getUserInfo().getUsername(), metaObject);
        setFieldValByName("updatedTime", DateTime.now(), metaObject);
        setFieldValByName("updateUserId", UserInfoContext.getUserInfo().getUserId(), metaObject);
        setFieldValByName("updateUserName", UserInfoContext.getUserInfo().getUsername(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 创建时间默认当前时间
        setFieldValByName("updatedTime", DateTime.now(), metaObject);
        setFieldValByName("updateUserId", UserInfoContext.getUserInfo().getUserId(), metaObject);
        setFieldValByName("updateUserName", UserInfoContext.getUserInfo().getUsername(), metaObject);
    }
}
