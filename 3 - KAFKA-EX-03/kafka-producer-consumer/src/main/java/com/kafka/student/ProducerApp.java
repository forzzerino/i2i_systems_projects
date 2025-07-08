package com.kafka.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class ProducerApp {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ObjectMapper mapper = new ObjectMapper();

        User user = new User("Can Öztürk", 22);
        String json = mapper.writeValueAsString(user);

        ProducerRecord<String, String> record = new ProducerRecord<>("user-topic", "user1", json);
        producer.send(record);

        producer.close();
        System.out.println("User sent: " + json);
    }
}