## 学习RabbitMQ多种模式

### 多模式的实现
      通过Exchange的type设置的

      简单模式：生产者---队列----n个消费者，多个消费者消费同一队列，

      广播模式：生产者---交换机---n队列---n消费者，每个消费者都能收到消息

      路由模式：生产者---交换机----routingKey匹配---n队列---n消费者，匹配routingKey的消费者能收到消息


简单模型

如果由多个消费者，一条消息只能被一个消费者收到，而不是每个消费者都能收到消息。

生产者

@Autowired
RabbitTemplate rabbitTemplate;
@Test
public void productWork(){
    for(int i=0;i<10;i++)
        rabbitTemplate.convertAndSend("workQueue","This is workQueue"+i);
}
消费者

@Component
public class WorkListener {
    //消费者1
    @RabbitListener(queuesToDeclare = @Queue("workQueue"))
    public void getMsg1(String msg){
        System.out.println("MSG1："+msg);
    }
    //消费者2
    @RabbitListener(queuesToDeclare = @Queue("workQueue"))
    public void getMsg2(String msg){
        System.out.println("MSG2："+msg);
    }
}
广播模型


生产者发送消息给Exchange, Exchange把消息都转发给每个队列
使用场景：生产者发送消息后，多个消费者都需要收到消息
生产者


路由（routing）模式

广播模式缩减版

有些消息比较琐碎，不需要将这些消息发送给领导

通过Exchange的Routing的配置，达到有些消息不发送给某队列目的


 
