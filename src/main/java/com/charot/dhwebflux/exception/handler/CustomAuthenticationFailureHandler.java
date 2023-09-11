package com.charot.dhwebflux.exception.handler;

import com.charot.dhwebflux.actuator.metrics.security.LoginMetrics;
import lombok.AllArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CustomAuthenticationFailureHandler  implements ServerAuthenticationFailureHandler {

    private final LoginMetrics metrics;

    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        ServerWebExchange exchange = webFilterExchange.getExchange();
        metrics.incrementFailedLogin();
        return exchange.getResponse().setComplete();
    }

}
