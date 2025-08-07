package com.aliyun.oss;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * aliyun oss 自动配置类
 * 在MATA-INF\spring\org.springframework.boot.autoconfigure.AutoConfiguration.imports中添加这个配置类, 以使得自动配置生效
 * spring boot启动时会通过ImportSelector的实现类将上述文件中配置好的类名全部扫描出来, 然后根据条件是否要把配置类中定义的Bean加载到spring容器中
 */
@EnableConfigurationProperties(AliyunOSSProperties.class) // 将AliyunOSSProperties配置类加载到spring容器中, 用在下面的AliyunOSSOperator中
@Configuration // 配置类
public class AliyunOSSAutoConfiguration {

    /**
     * 创建AliyunOSSOperator的Bean
     */
    @Bean
    @ConditionalOnMissingBean // 如果容器中不存在AliyunOSSOperator的Bean, 就创建一个
    public AliyunOSSOperator aliyunOSSOperator(AliyunOSSProperties aliyunOSSProperties) {
        return new AliyunOSSOperator(aliyunOSSProperties);
    }
}
