package apis.weatherapp.utils;

import apis.weatherapp.security.JwtTokenUtil;
import apis.weatherapp.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class UserExtractor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public User getAuthenticatedUser(HttpServletRequest request) {
        return jwtTokenUtil.getUserFromCookiesOrHeader(request);
    }

}
