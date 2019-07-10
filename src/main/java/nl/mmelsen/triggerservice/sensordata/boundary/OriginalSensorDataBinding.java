package nl.mmelsen.triggerservice.sensordata.boundary;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface OriginalSensorDataBinding {
    @SuppressWarnings("squid:S1214") // disable sonar check on moving constants to class
            String OSD_IN = "original-sensor-data-in";

    @Input(OSD_IN)
    KStream osdIn();
}