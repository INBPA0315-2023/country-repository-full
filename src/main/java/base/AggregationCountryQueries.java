package base;

import countries.Country;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Defines aggregation queries for {@link Country} objects.
 */
public interface AggregationCountryQueries {

    /**
     * Returns a dictionary which maps each country code to the corresponding country.
     *
     * @return the dictionary
     */
    Map<String, Country> getCountriesByCodes();

    /**
     * Returns a dictionary which maps each region to the count of its countries.
     *
     * @return the dictionary
     */
    Map<Country.Region, Long> getCountOfCountriesByRegions();

    /**
     * Returns a dictionary which maps each region to its countries.
     *
     * @return the dictionary
     */
    Map<Country.Region, Set<Country>> getCountriesByRegions();

    /**
     * Returns a dictionary which maps each region to its most populous country.
     *
     * @return the dictionary
     */
    Map<Country.Region, Optional<Country>> getMostPopulousCountryByRegions();

    /**
     * Returns a dictionary which maps each region the list of its countries ordered by their capital names to their continent.
     *
     * @return the dictionary
     */
    Map<Country.Region, List<Country>> getCountriesByRegionsOrderByCapitals();

    /**
     * Returns a dictionary which maps each region to the corresponding countries which population is between the given bounds (inclusive) to their continent.
     *
     * @param lowerBound the lower bound
     * @param upperBound the upper bound
     * @return the dictionary
     */
    Map<Country.Region, Set<Country>> getCountriesByRegionsFilterByPopulation(
            long lowerBound,
            long upperBound);

    /**
     * Returns a dictionary which maps each region to the corresponding country codes, then each country code to the corresponding country.
     *
     * @return the dictionary
     */
    Map<Country.Region, Map<String, Country>> getCountriesByRegionsAndCodes();

    /**
     * Returns a dictionary which maps region to the letters that occur in the first place of corresponding
     * country names, then each letter to the corresponding countries.
     *
     * @return the dictionary
     */
    Map<Country.Region, Map<String, Set<Country>>> getCountriesByRegionsAndFirstLetters();

    /**
     * Returns a dictionary which maps the letters that occur in the first place of corresponding
     * country names to regions, then each letter to the corresponding countries.
     *
     * @return the dictionary
     */
    Map<String, Map<Country.Region, Set<Country>>> getCountriesByFirstLettersAndRegions();
}
