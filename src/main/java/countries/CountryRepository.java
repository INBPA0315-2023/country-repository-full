package countries;

import base.*;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.*;

public final class CountryRepository
        extends Repository<Country>
        implements BasicCountryQueries, CollectionCountryQueries, SortingCountryQueries, AggregationCountryQueries {

    public CountryRepository() throws IOException {
        super(Country.class);
    }

    @Override
    public long getMaximumPopulation() {
        List<Country> countries = getAll();
        long max = countries.get(0).getPopulation();
        // long max = getAll().get(0).getPopulation();
        for (int i = 1; i < countries.size(); i++) {
            if (countries.get(i).getPopulation() > max) {
                max = countries.get(i).getPopulation();
            }
        }
        return max;
    }

    @Override
    public double getAveragePopulation() {
        long sum = 0;
        for (Country country : getAll()) {
            sum += country.getPopulation();
        }
        return sum / (double) getAll().size();
    }

    @Override
    public long getCountOfEuropeanCountries() {
        long count = 0;
        for (Country country : getAll()) {
            if (country.getRegion() == Country.Region.EUROPE) {
                count++;
            }
        }
        return count;
    }

    @Override
    public long getCountOfCountriesFilterByRegion(Country.@NonNull Region region) {
        long count = 0;
        for (Country country : getAll()) {
            if (country.getRegion() == region) {
                count++;
            }
        }
        return count;
    }

    @Override
    public long getPopulationByRegion(Country.@NonNull Region region) {
        long sum = 0;
        for (Country country : getAll()) {
            if (country.getRegion() == region) {
                sum += country.getPopulation();
            }
        }
        return sum;
    }

    @Override
    public boolean isPopulationExists(long population) {
        for (Country country : getAll()) {
            if (country.getPopulation() == population) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Country getCountryByCode(@NonNull String code) {
        for (Country country : getAll()) {
            if (Objects.equals(country.getCode(), code)) {
                return country;
            }
        }
        return null;
    }

    @Override
    public Optional<Country> getOptionalCountryByCode(@NonNull String code) {
        for (Country country : getAll()) {
            if (Objects.equals(country.getCode(), code)) {
                return Optional.of(country);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Country> getMostPopulousCountryByRegion(Country.@NonNull Region region) {
        /*
        Country selected = null;
        for (Country country : getAll()) {
            if (country.getRegion() != region) {
                continue;
                ;
            }
            if (selected == null || country.getPopulation() > selected.getPopulation()) {
                selected = country;
            }
        }
        return Optional.ofNullable(selected);
         */

        Optional<Country> selected = Optional.empty();
        for (Country country : getAll()) {
            if (country.getRegion() == region && (selected.isEmpty() || country.getPopulation() > selected.get().getPopulation())) {
                selected = Optional.of(country);
            }
        }
        return selected;
    }

    @Override
    public Optional<Country> getFirstCountryByStartingLetter(char letter) {
        for (Country country : getAll()) {
            if (country.getName().charAt(0) == letter) {
                return Optional.of(country);
            }
        }
        return Optional.empty();
    }

    @Override
    public Set<String> getCountryNames() {
        Set<String> names = new HashSet<>();
        for (Country country : getAll()) {
            names.add(country.getName());
        }
        return names;
    }

    @Override
    public List<String> getCapitalsOrderByName() {
        List<String> capitals = new ArrayList<>();
        for (Country country : getAll()) {
            capitals.add(country.getCapital());
        }
        // Collections.sort(capitals);
        capitals.sort(Comparator.naturalOrder());
        return capitals;
    }

    @Override
    public List<String> getCapitalsOrderByNameDesc() {
        List<String> capitals = new ArrayList<>();
        for (Country country : getAll()) {
            capitals.add(country.getCapital());
        }
        capitals.sort(Comparator.reverseOrder());
        return capitals;
    }

    @Override
    public Set<String> getNamesOfEuropeanCountries() {
        Set<String> names = new TreeSet<>();
        for (Country country : getAll()) {
            if (country.getRegion() == Country.Region.EUROPE) {
                names.add(country.getName());
            }
        }
        return names;
    }

    @Override
    public Set<String> getNamesOfCountriesFilterByContinent(Country.@NonNull Region region) {
        Set<String> names = new TreeSet<>();
        for (Country country : getAll()) {
            if (country.getRegion() == region) {
                names.add(country.getName());
            }
        }
        return names;
    }

    @Override
    public Set<Country> getCountriesBelowPopulationLimit(int limit) {
        Set<Country> countries = new HashSet<>();
        for (Country country : getAll()) {
            if (country.getPopulation() < limit) {
                countries.add(country);
            }
        }
        return countries;
    }

    @Override
    public Set<Long> getPopulationsByRegion(Country.@NonNull Region region) {
        Set<Long> populations = new HashSet<>();
        for (Country country : getAll()) {
            if (country.getRegion() == region) {
                populations.add(country.getPopulation());
            }
        }
        return populations;
    }

    @Override
    public Set<Country> getCountriesByPopulation(long population) {
        Set<Country> countries = new HashSet<>();
        for (Country country : getAll()) {
            if (country.getPopulation() == population) {
                countries.add(country);
            }
        }
        return countries;
    }

    @Override
    public Set<Country> getCountriesByPopulation(long lowerBound, long upperBound) {
        Set<Country> countries = new HashSet<>();
        for (Country country : getAll()) {
            if (country.getPopulation() >= lowerBound && country.getPopulation() <= upperBound) {
                countries.add(country);
            }
        }
        return countries;
    }

    @Override
    public List<Country> getCountriesOrderByPopulationDesc() {
        List<Country> countries = new ArrayList<>(getAll());
        countries.sort(new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                return -Long.compare(o1.getPopulation(), o2.getPopulation());
            }
        });
        return countries;
    }

    @Override
    public List<Country> getCountriesOrderByLengthOfCapitalThenByPopulationDesc() {
        List<Country> countries = new ArrayList<>(getAll());
        countries.sort(new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                if (o1.getCapital().length() != o2.getCapital().length()) {
                    return Integer.compare(o1.getCapital().length(), o2.getCapital().length());
                }
                return Objects.compare(o1.getPopulation(), o2.getPopulation(), Comparator.reverseOrder());
            }
        });
        return countries;
    }

    @Override
    public List<Country> getCountriesOrderByLengthOfCapitalThenByCapital() {
        List<Country> countries = new ArrayList<>(getAll());
        countries.sort(new Comparator<Country>() {
            @Override
            public int compare(Country o1, Country o2) {
                if (o1.getCapital().length() != o2.getCapital().length()) {
                    return Integer.compare(o1.getCapital().length(), o2.getCapital().length());
                }
                return Objects.compare(o1.getCapital(), o2.getCapital(), Comparator.naturalOrder());
            }
        });
        return countries;
    }

    @Override
    public Map<String, Country> getCountriesByCodes() {
        Map<String, Country> result = new HashMap<>();
        for (Country country : getAll()) {
            result.put(country.getCode(), country);
        }
        return result;
    }

    @Override
    public Map<Country.Region, Long> getCountOfCountriesByRegions() {
        Map<Country.Region, Long> result = new EnumMap<>(Country.Region.class);
        for (Country country : getAll()) {
            /*
            if (!result.containsKey(country.getRegion())) {
                result.put(country.getRegion(), 1L);
            } else {
                result.put(
                        country.getRegion(),
                        result.get(country.getRegion()) + 1
                );
            }
             */

            /*
            if (!result.containsKey(country.getRegion())) {
                result.put(country.getRegion(), 0L);
            }
            result.put(
                    country.getRegion(),
                    result.get(country.getRegion()) + 1
            );
             */

            result.put(
                    country.getRegion(),
                    result.getOrDefault(country.getRegion(), 0L) + 1
            );
        }
        return result;
    }

    @Override
    public Map<Country.Region, Set<Country>> getCountriesByRegions() {
        /*
        Map<Country.Region, Set<Country>> result = new EnumMap<>(Country.Region.class);
        for (Country country : getAll()) {
            if (!result.containsKey(country.getRegion())) {
                result.put(country.getRegion(), new HashSet<>());
            }
            result.get(country.getRegion()).add(country);
        }
        return result;
         */

        Map<Country.Region, Set<Country>> result = new EnumMap<>(Country.Region.class);
        for (Country.Region region : Country.Region.values()) {
            result.put(region, new HashSet<>());
        }
        for (Country country : getAll()) {
            result.get(country.getRegion()).add(country);
        }
        return result;
    }

    @Override
    public Map<Country.Region, Optional<Country>> getMostPopulousCountryByRegions() {
        Map<Country.Region, Optional<Country>> result = new EnumMap<>(Country.Region.class);

        for (Country.Region region : Country.Region.values()) {
            result.put(region, Optional.empty());
        }
        for (Country country : getAll()) {
            if (result.get(country.getRegion()).isEmpty() || result.get(country.getRegion()).get().getPopulation() < country.getPopulation()) {
                result.put(country.getRegion(), Optional.of(country));
            }
        }
        return result;
    }

    @Override
    public Map<Country.Region, List<Country>> getCountriesByRegionsOrderByCapitals() {
        Map<Country.Region, List<Country>> result = new EnumMap<>(Country.Region.class);

        /*
        for (Map.Entry<Country.Region, Set<Country>> entry : getCountriesByRegions().entrySet()) {
            result.put(entry.getKey(), new ArrayList<>(entry.getValue()));
            result.get(entry.getKey()).sort(new Comparator<Country>() {
                @Override
                public int compare(Country o1, Country o2) {
                    return Objects.compare(o1.getCapital(), o2.getCapital(), Comparator.naturalOrder());
                }
            });
        }
         */

        /*
        for (Country country: getAll()) {
            if(!result.containsKey(country.getRegion())) {
                result.put(country.getRegion(), new ArrayList<>());
            }
            result.get(country.getRegion()).add(country);
        }
         */

        for (Country.Region region : Country.Region.values()) {
            result.put(region, new ArrayList<>());
        }
        for (Country country : getAll()) {
            result.get(country.getRegion()).add(country);
        }
        return result;
    }

    @Override
    public Map<Country.Region, Set<Country>> getCountriesByRegionsFilterByPopulation(long lowerBound, long upperBound) {
        Map<Country.Region, Set<Country>> result = new EnumMap<>(Country.Region.class);

        for (Country.Region region : Country.Region.values()) {
            result.put(region, new HashSet<>());
        }
        for (Country country : getAll()) {
            if (country.getPopulation() >= lowerBound && country.getPopulation() <= upperBound) {
                result.get(country.getRegion()).add(country);
            }
        }
        return result;
    }

    @Override
    public Map<Country.Region, Map<String, Country>> getCountriesByRegionsAndCodes() {
        Map<Country.Region, Map<String, Country>> result = new EnumMap<>(Country.Region.class);

        for (Country country : getAll()) {
            if (!result.containsKey(country.getRegion())) {
                result.put(country.getRegion(), new TreeMap<>());
            }
            result.get(country.getRegion())
                    .put(country.getCode(), country);
        }
        return result;
    }

    @Override
    public Map<Country.Region, Map<String, Set<Country>>> getCountriesByRegionsAndFirstLetters() {
        Map<Country.Region, Map<String, Set<Country>>> result = new EnumMap<>(Country.Region.class);

        for (Country country : getAll()) {
            if (!result.containsKey(country.getRegion())) {
                result.put(country.getRegion(), new TreeMap<>());
            }
            String firstLetter = country.getName().substring(0, 1);
            if (!result.get(country.getRegion()).containsKey(firstLetter)) {
                result.get(country.getRegion())
                        .put(firstLetter, new HashSet<>());
            }
            result.get(country.getRegion())
                    .get(firstLetter)
                    .add(country);
        }
        return result;
    }

    @Override
    public Map<String, Map<Country.Region, Set<Country>>> getCountriesByFirstLettersAndRegions() {
        Map<String, Map<Country.Region, Set<Country>>> result = new TreeMap<>();

        for (Country country : getAll()) {
            String firstLetter = country.getName().substring(0, 1);
            if (!result.containsKey(firstLetter)) {
                result.put(firstLetter, new EnumMap<>(Country.Region.class));
            }
            if (!result.get(firstLetter).containsKey(country.getRegion())) {
                result.get(firstLetter)
                        .put(country.getRegion(), new HashSet<>());
            }
            result.get(firstLetter)
                    .get(country.getRegion())
                    .add(country);
        }
        return result;
    }

    @SneakyThrows
    public static void main(String[] args) {
        final var repo = new CountryRepository();
        System.out.println(repo);

        // TODO: test your queries here
    }
}
