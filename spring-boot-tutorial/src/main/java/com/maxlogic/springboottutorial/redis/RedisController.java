package com.maxlogic.springboottutorial.redis;

import com.maxlogic.springboottutorial.redis.service.RedisService;
import com.maxlogic.springboottutorial.redis.service.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {

    @Autowired
    RedisService redisService;

    @GetMapping(value = "/fetchUserAuth")
    public ResponseEntity<User> fetchUserAuthorizationData(
            @RequestParam(value = "dataAccessRequired", required = false ) Boolean dataAccessRequired,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) String headerToken) {
        return ResponseEntity.ok(redisService.fetchAuthorizationData(headerToken, "abc@pqr.com"));
    }

    @GetMapping(value = "/getUserFromCache")
    public ResponseEntity<User> getUserFromCache(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) String headerToken) {
        try {
            User user = redisService.getUserFromCache(headerToken, "abc@pqr.com");
            log.info("User info got from getUserFromCache [{}]", user);
            return ResponseEntity.ok(user);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        log.info("How come we are here !!");
        return (ResponseEntity<User>) ResponseEntity.ok();
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<Boolean> logoutUser(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) String headerToken){
        Boolean response = false;
        try {
            response = redisService.logoutUser(headerToken, "abc@pqr.com");
            log.info("User logout execution completed [{}]", response);
            return ResponseEntity.ok(response);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }
}
