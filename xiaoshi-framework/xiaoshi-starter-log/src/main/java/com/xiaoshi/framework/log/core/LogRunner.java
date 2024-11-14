package com.xiaoshi.framework.log.core;

import com.xiaoshi.framework.common.utils.validation.CheckUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class LogRunner implements ApplicationRunner {

    @Value("${spring.profiles.active:local}")
    private String active;

    @Override
    public void run(ApplicationArguments args) {
        // 非本地 将 全部的 System.err 和 System.out 替换为log
        if (CheckUtils.notEqualsIgnoreCase("local", active)) {
            System.setOut(LogPrintStream.out());
            System.setErr(LogPrintStream.err());
        }
    }
}
