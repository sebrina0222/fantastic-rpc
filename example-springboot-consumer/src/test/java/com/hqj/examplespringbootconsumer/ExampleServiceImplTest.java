package com.hqj.examplespringbootconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.security.PrivateKey;

/**
 * ClassName: ExampleServiceImplTest
 * Package: com.hqj.examplespringbootconsumer
 * Description:
 *
 * @Author:HQJ
 * @Create:2024/5/20 - 17:02
 * @Version v1.0
 */
@SpringBootTest //没加会报空指针错误
public class ExampleServiceImplTest {

    @Resource
    private ExampleServiceImpl exampleService;
    @Test
    void test1() {
        exampleService.test();
    }
}
