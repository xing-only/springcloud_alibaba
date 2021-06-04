package com.xing.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description: 自定义过滤器
 * @Author DXX
 * @Date 2021/6/4
 **/
@Component
@Slf4j
public class MyGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*******my filter********");
        String name = exchange.getRequest().getQueryParams().getFirst("name");
        if(StringUtils.isBlank(name)){
            log.info("*********用户名为空，非法用户请求。。。");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 加载过滤器的数字，越小，优先级越高
     * @date 2021/6/4 14:05
     * @author DXX
     * @param
     * @return int
     **/
    @Override
    public int getOrder() {
        return 1;
    }
}
