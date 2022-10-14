package com.jason.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author：yangwushuo
 * @time：2022/10/14 16:58
 * 自定义网关全局filter
 */
@Configuration
public class CustomerGlobalFilter implements GlobalFilter, Ordered {

    //exchange: request response 封装了 request response
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //httprequest 对象
        ServerHttpRequest request = exchange.getRequest();
        //httpresponse 对象
        ServerHttpResponse response = exchange.getResponse();
        System.out.println("经过全局filter处理....");
        Mono<Void> filter = chain.filter(exchange);
        System.out.println("响应回来filter处理...");
        return filter;
    }

    //order 排序 int 数字:用来指定filter执行顺序 默认顺序按照自然数字进行排序 -1 在所有filter执行之前执行
    @Override
    public int getOrder() {
        return -1;
    }
}
