package com.fankai.mq.rabbitmq;

import com.fankai.configuration.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author fankai
 * @date 2021年06月29日 16:07
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
@Slf4j
public class MsgReceiver {

    @RabbitHandler
    public void process(String content) {
        log.info("接收处理队列A当中的消息： " + content);
    }
}
