package nl.mmelsen.triggerservice.sensordata.boundary;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

@Slf4j
public class OriginalSensorDataTimestampExtractor implements TimestampExtractor {

    OriginalSensorDataSerde originalDataSerde = new OriginalSensorDataSerde();

    @Override
    public long extract(final ConsumerRecord<Object, Object> record, final long previousTimestamp) {
        if (record != null && record.value() != null) {

            String timestamp = String.valueOf(record.timestamp());
            byte[] value = (byte[]) record.value();

            SensorData originalSensorData = originalDataSerde.deserializer().deserialize(timestamp, value);
            if (originalSensorData != null && originalSensorData.getTimestamp() != null) {
                return originalSensorData.getTimestamp();
            }
        }

        log.warn("Record was not of type {} so we will fallback to using current time as the record key",
                OriginalSensorDataTimestampExtractor.class.getName());
        // We will fallback to the currentTimeMillis as we prefer rather keeping the error-score in the resulting
        // dataset (albeit in possibly the wrong window) over discarding the error-score
        return System.currentTimeMillis();
    }
}
