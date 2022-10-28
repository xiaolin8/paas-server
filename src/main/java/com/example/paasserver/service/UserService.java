package com.example.paasserver.service;

import com.example.paasserver.api.UserApi;
import com.example.paasserver.pojo.UserInfo;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Trace
    public UserInfo getUserMethod1(String name, int age) {
        return getUserMethod2(name, age);
    }

    @Trace
    public UserInfo getUserMethod2(String name, int age) {
        UserInfo userInfo = new UserInfo(name, age, "Beijing Road");
        return userInfo;
    }

    @Trace(operationName = "getUserTagMethod1")
    @Tags({@Tag(key = "name", value = "arg[0]"),
            @Tag(key = "age", value = "arg[1]"),
            @Tag(key = "address", value = "returnedObj.address")})
    public UserInfo getUserTagMethod1(String name, int age) {
        return getUserTagMethod2(name, age);
    }

    @Trace(operationName = "getUserTagMethod2")
    @Tags({@Tag(key = "name", value = "arg[0]"),
            @Tag(key = "age", value = "arg[1]"),
            @Tag(key = "address", value = "returnedObj.address")})
    public UserInfo getUserTagMethod2(String name, int age) {
        UserInfo userInfo = new UserInfo(name, age, "Beijing Road");
        return userInfo;
    }
}
