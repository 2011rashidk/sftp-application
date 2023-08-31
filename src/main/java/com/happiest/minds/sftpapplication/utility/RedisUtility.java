package com.happiest.minds.sftpapplication.utility;

import lombok.extern.slf4j.Slf4j;
import org.example.RedisUtil;
import org.springframework.stereotype.Component;

import static com.happiest.minds.sftpapplication.enums.Constants.*;

@Component
@Slf4j
public class RedisUtility {

    public String getAdminToken() {
        try {
            RedisUtil redisUtil = new RedisUtil();
            return redisUtil.get(ADMIN.getValue());
        } catch (Exception e) {
            log.error(EXCEPTION.getValue(), e.getMessage());
        }
        return null;
    }

}
