package nl.mmelsen.triggerservice.sensordata.boundary;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import static nl.mmelsen.triggerservice.sensordata.boundary.OriginalSensorDataBinding.OSD_IN;

@EnableBinding(OriginalSensorDataBinding.class)
@Component
@Slf4j
public class Consumer {

    @StreamListener
    public void consume(@Input(OSD_IN) KStream<String, SensorData> stream) {

        stream.foreach((key, value) -> log.info("{} {}", key, value.toString()));
    }
}
