package apigateway.config;

import apigateway.interceptor.GatewayInterceptor;
import io.micrometer.core.lang.NonNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulHandlerBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    @NonNull
    @Autowired
    private GatewayInterceptor gatewayInterceptor;

    @Override
    public boolean postProcessAfterInstantiation(final Object bean, final String beanName) throws BeansException {

        if (bean instanceof ZuulHandlerMapping) {
            ZuulHandlerMapping zuulHandlerMapping = (ZuulHandlerMapping) bean;
            zuulHandlerMapping.setInterceptors(gatewayInterceptor);
        }
        return super.postProcessAfterInstantiation(bean, beanName);
    }

}