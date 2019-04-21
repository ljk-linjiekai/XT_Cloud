package com.xintu.manager.services.rabbitMQ.test;

import com.xintu.manager.services.ManagerServiceApplication;
import com.xintu.manager.services.rabbitMQ.Receiver;
import com.xintu.manager.services.rabbitMQ.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ManagerServiceApplication.class)
public class HelloApplicationTests {
 
    @Autowired
    private Sender sender;

    @Test
    public void send(){
        sender.send();
    }

}