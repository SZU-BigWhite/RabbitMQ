package rabbitmq.learn.mq.producter;

import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import rabbitmq.learn.mq.MqApplicationTests;


public class MQProducter extends MqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    //topic 动态route模式
    @Test
    public void producttopic(){
        rabbitTemplate.convertAndSend("topic","user.save","user.save 消息");
    }

    //direct route模式
    @Test
    public void productDirects(){
        rabbitTemplate.convertAndSend("directs","logs","logs Message");
//        rabbitTemplate.convertAndSend("directs","error","Error Message");
    }

    //fanout 广播--发布者-订阅模式
    @Test
    public void productFanout(){
        rabbitTemplate.convertAndSend("logs","","Hello FonoutQueue");
    }

    //work模式
    @Test
    public void productWork(){
        for(int i=0;i<30;i++)
            rabbitTemplate.convertAndSend("workQueue","This is workQueue"+i);
    }

    //hello模式
    @Test
    public void productHello(){
        rabbitTemplate.convertAndSend("helloQueue","Hello testQueue");
    }


}
