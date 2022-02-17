package kukukode.progem.apigateway.filters.prefilters;

import kukukode.progem.apigateway.service.microservice.JWTMCService;
import kukukode.progem.apigateway.util.RouteURIs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class GlobalPrefilter implements GlobalFilter, Ordered {
    /*
    RULE FOR AUTHHEADER
    Must be in this format
    "Bearer token"
     */
    /*
    Technically we only need to check if we have authHeader or not.
    Scenarios
    ACCESSING ANY MC OTHER THAN "/AUTH"
    1. Accessing Resources without authHeader -  forward the request to MCs with guest as userID in body
    2. Accessing Resources with authHeader - Parse authHeader for email and send it to MCs with email as UserID in body, in case Invalid token, respond client with invalid token

    ACCESSING "/Auth"
    1. Accessing Auth with authHeader - Return "Already contains authHeader"
    2. AccessingAuth without authHeader - forward it to auth MC
     */
    private final JWTMCService jwtmcService;
    private final String authheader = "Authorization";
    public static final String routeAttribute = "org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayRoute";

    @Autowired
    public GlobalPrefilter(JWTMCService jwtmcService) {
        this.jwtmcService = jwtmcService;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //Store the route information
        Route route = (Route) exchange.getAttributes().get(routeAttribute);

        //Check if we have authHeader
        if (exchange.getRequest().getHeaders().containsKey(authheader)) {
            //Check if trying to access auth service or other service
            if (route.getUri().toString().equals(RouteURIs.AUTH)) {
                //**Trying to access Auth MC with authHeader**

                // should not be allowed to access Auth MC with authHeader
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            //**Trying to access resources with an authHeader**

            //Parse userID out of the token if possible
            String token = exchange.getRequest().getHeaders().get(authheader).get(0).replace("Bearer ", "");
            String user = jwtmcService.getUserID(token);
            //Modify request and forward it to next filter
            exchange.getRequest().mutate().header(authheader, user);
            return chain.filter(exchange);


        } else {
            //**No AuthHeader supplied in request**

            if (!route.getUri().toString().equals(RouteURIs.AUTH)) {
                //**Trying to access resources without authHeader**

                //so we need to add GUEST as AuthHeader for guest level access
                exchange.getRequest().mutate().header(authheader, "GUEST");
                return chain.filter(exchange);
            }

        }

        //Reached here means client is trying to access Auth MC without a header, which is allowed
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }


}
