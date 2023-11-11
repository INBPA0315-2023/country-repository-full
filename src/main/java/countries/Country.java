package countries;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Represents a country.
 */
@Builder
@Value
@With
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Country {

    @EqualsAndHashCode.Include
    String code;
    String name;
    String capital;
    Region region;
    long population;
    double area;

    /**
     * Represents regions.
     */
    public enum Region {

        /**
         * Represents Africa.
         */
        @JsonProperty("Africa") AFRICA,

        /**
         * Represents Americas.
         */
        @JsonProperty("Americas") AMERICAS,

        /**
         * Represents Asia.
         */
        @JsonProperty("Asia") ASIA,

        /**
         * Represents Europe.
         */
        @JsonProperty("Europe") EUROPE,

        /**
         * Represents Oceania.
         */
        @JsonProperty("Oceania") OCEANIA,

        /**
         * Represents Polar.
         */
        @JsonProperty("Polar") POLAR
    }
}
