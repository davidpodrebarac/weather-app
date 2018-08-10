package apis.weatherapp.security.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apis.weatherapp.metaweather.city.subscription.CitySubscription;
import apis.weatherapp.metaweather.city.subscription.CitySubscriptionService;
import apis.weatherapp.security.JwtTokenUtil;
import apis.weatherapp.security.JwtUser;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    
    @Autowired
    private CitySubscriptionService citySubService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
    	return getJwtUserFromRequest(request);
    }
    
    private JwtUser getJwtUserFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
	}

	@RequestMapping(value = "user/subscriptions", method = RequestMethod.GET)
    public List<CitySubscription> getAllUserCitySubscriptions(HttpServletRequest request) {
		JwtUser user = getJwtUserFromRequest(request);
    	return citySubService.getAllSubscriptions(user.getId());
    }
    
}
