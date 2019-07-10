package nl.mmelsen.triggerservice.sensordata.boundary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.Map;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Indicates when the value was measured
     */
    private Long timestamp;
    private String installationId;
    private String assetId;
    private Map<String, Double> data;
    private long partition;
    private long offset;

}
