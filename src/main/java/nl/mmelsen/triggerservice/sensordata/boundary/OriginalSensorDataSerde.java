package nl.mmelsen.triggerservice.sensordata.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

@Slf4j
public class OriginalSensorDataSerde implements Serde<SensorData> {
    @Override
    public void configure(Map<String, ?> map, boolean b) {
        // Do nothing
    }

    @Override
    public void close() {
        // Do nothing
    }

    @Override
    public Serializer<SensorData> serializer() {
        return new Serializer<>() {

            @Override
            public void configure(Map<String, ?> map, boolean b) {
                // Do nothing
            }

            @Override
            public byte[] serialize(String arg0, SensorData originalSensorData) {
                byte[] retVal = null;
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    retVal = objectMapper.writeValueAsString(originalSensorData).getBytes();
                } catch (Exception e) {
                    log.error("Exception while serializing original sensor", e);
                }
                return retVal;
            }

            public void close() {
                // Do nothing
            }
        };
    }

    @Override
    public Deserializer<SensorData> deserializer() {
        return new Deserializer<>() {
            @Override
            public void configure(Map<String, ?> map, boolean b) {
                // Do nothing
            }

            @Override
            public SensorData deserialize(String s, byte[] bytes) {
                ObjectMapper mapper = new ObjectMapper();
                SensorData originalSensorData = null;
                try {
                    originalSensorData = mapper.readValue(bytes, SensorData.class);
                } catch (Exception e) {
                    log.error("Exception while deserializing original sensor data", e);
                }
                return originalSensorData;
            }

            @Override
            public void close() {
                // Do nothing
            }
        };
    }
}
