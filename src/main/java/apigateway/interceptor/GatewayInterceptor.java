package apigateway.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import apigateway.utils.JwtTokenUtil;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class GatewayInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(GatewayInterceptor.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String getURL = request.getRequestURI();
        String mehtodType = request.getMethod();
        String authToken = request.getHeader("token");
        if(authToken!=null && !authToken.isEmpty() && !jwtTokenUtil.isTokenExpired(authToken)){//Private Urls
            return true;
        }else{//Public URL
            if(("/api/user-service/login").equalsIgnoreCase(getURL) && "POST".equalsIgnoreCase(mehtodType))
                return true;
        }
        LOGGER.info("Sending error");
        //Token has experied or no token in header
        response.sendError(401);
        return false;
    }
}
