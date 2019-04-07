package com.xintu.sso.web.feign;

import com.xintu.sso.domain.model.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserServiceWeb {
    public ResponseEntity<String> check(String param, Integer type, String callback);

    public ResponseEntity<String> queryUserByTicket(String ticket, String callback);

    public ResponseEntity<Map<String, Object>> doRegister(User user);

    public ResponseEntity<Map<String, Object>> doLogin( User user, HttpServletRequest request, HttpServletResponse response);
}
