package com.monitorllantas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:sqlserver://localhost:1433;DatabaseName=NameDatabase;encrypt=false",
    "spring.datasource.username=userAqui",
    "spring.datasource.password=passAqui"
})
class MonitorLlantasApplicationTests {

    @Test
    void contextLoads() {
    }
}
