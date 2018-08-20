package apis.weatherapp.security.controller;

import apis.weatherapp.security.JwtAuthenticationRequest;
import apis.weatherapp.security.JwtAuthenticationResponse;
import apis.weatherapp.security.JwtTokenUtil;
import apis.weatherapp.security.JwtUser;
import apis.weatherapp.utils.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationRestController {
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    private LoginHelper loginHelper;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) throws AuthenticationException {

        loginHelper.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final String token = loginHelper.generateTokenForUser(authenticationRequest.getUsername());

        loginHelper.addJwtToCookie(response, token);
        ResponseEntity entity = ResponseEntity.ok(new JwtAuthenticationResponse(token));
        return entity;
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(tokenHeader).substring(7);
        JwtUser user = loginHelper.getUserFromToken(token);

        loginHelper.addJwtToCookie(response, token);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}
