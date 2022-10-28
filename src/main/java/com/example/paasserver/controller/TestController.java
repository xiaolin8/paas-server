package com.example.paasserver.controller;

import com.example.paasserver.pojo.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/test/trace")
    public ApiResponse getTrace() {
        return ApiResponse.ok(method1("foo", 22));
    }

    @GetMapping("/test/trace/tag")
    public ApiResponse getTraceTag() {
        return ApiResponse.ok(method1Tag("foo", 22));
    }

    @Trace
    public UserInfo method1(String name, int age) {
        return method2(name, age);
    }

    @Trace
    public UserInfo method2(String name, int age) {
        UserInfo userInfo = new UserInfo(name, age, "Beijing Road");
        return userInfo;
    }

    @Trace
    public UserInfo method1Tag(String name, int age) {
        return method2Tag(name, age);
    }

    @Trace(operationName = "method2Tag")
    @Tags({@Tag(key = "name", value = "arg[0]"),
            @Tag(key = "age", value = "arg[1]"),
            @Tag(key = "address", value = "returnedObj.address")})
    public UserInfo method2Tag(String name, int age) {
        UserInfo userInfo = new UserInfo(name, age, "Beijing Road");
        return userInfo;
    }

    @Data
    @AllArgsConstructor
    public class UserInfo {
        private String name;
        private Integer age;
        private String address;
    }
}


