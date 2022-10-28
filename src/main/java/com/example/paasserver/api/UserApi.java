package com.example.paasserver.api;

import com.example.paasserver.pojo.ApiResponse;
import com.example.paasserver.pojo.UserInfo;
import com.example.paasserver.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserApi {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user/v1")
    public ApiResponse getUser() {
        return ApiResponse.ok(userService.getUserMethod1("foo", 22));
    }

    @GetMapping("/api/user/tag/v1")
    public ApiResponse getUserTag() {
        return ApiResponse.ok(userService.getUserTagMethod1("foo", 22));
    }
}


