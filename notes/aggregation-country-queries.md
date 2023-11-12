# Implementing `AggregationCountryQueries`

1. Returns a dictionary which maps each country code to the corresponding country.

    Sample solution:

    ```java
    @Override
    public Map<String, Country> getCountriesByCodes() {
        Map<String, Country> result = new HashMap<>();
        for (Country country : getAll()) {
            result.put(country.getCode(), country);
        }
        return result;
    }
    ```

    Comments:

    * You can use both `TreeMap` and `HashMap` for the type if the exercise doesn't require a specific type. However, you should consider the following:
        * `HashMap` can be only used if the keys implement the `hashCode()` method.
        * `TreeMap` can be only used if the keys implement the `Comparable` interface or you specify a `Comparator` in the constructor.

1. Returns a dictionary which maps each region to the count of its countries.

    Sample solution #1:

    ```java
    @Override
    public Map<Country.Region, Long> getCountOfCountriesByRegions() {
        Map<Country.Region, Long> result = new EnumMap<>(Country.Region.class);
        for (Country country : getAll()) {
            if (!result.containsKey(country.getRegion())) {
                result.put(country.getRegion(), 1L);
            } else {
                result.put(
                        country.getRegion(),
                        result.get(country.getRegion()) + 1
                );
            }
        }
        return result;
    }
    ```

    Sample solution #2:

     ```java
    @Override
    public Map<Country.Region, Long> getCountOfCountriesByRegions() {
        Map<Country.Region, Long> result = new EnumMap<>(Country.Region.class);
        for (Country country : getAll()) {
            if (!result.containsKey(country.getRegion())) {
                result.put(country.getRegion(), 0L);
            }
            result.put(
                    country.getRegion(),
                    result.get(country.getRegion()) + 1
            );
        }
        return result;
    }
    ```

     Sample solution #3:

     ```java
    @Override
    public Map<Country.Region, Long> getCountOfCountriesByRegions() {
        Map<Country.Region, Long> result = new EnumMap<>(Country.Region.class);
        for (Country country : getAll()) {
            result.put(
                    country.getRegion(),
                    result.getOrDefault(country.getRegion(), 0L) + 1
            );
        }
        return result;
    }
    ```

    Comments:

    * Java offers a special `EnumMap` for enum keys.

1. Returns a dictionary which maps each region to its countries.

    Sample solution #1:

    ```java
    @Override
    public Map<Country.Region, Set<Country>> getCountriesByRegions() {
        Map<Country.Region, Set<Country>> result = new EnumMap<>(Country.Region.class);
        for (Country country : getAll()) {
            if (!result.containsKey(country.getRegion())) {
                result.put(country.getRegion(), new HashSet<>());
            }
            result.get(country.getRegion()).add(country);
        }
        return result;
    }
    ```

    Sample solution #2:
    
    ```java
    @Override
    public Map<Country.Region, Set<Country>> getCountriesByRegions() {
        Map<Country.Region, Set<Country>> result = new EnumMap<>(Country.Region.class);
        for (Country.Region region : Country.Region.values()) {
            result.put(region, new HashSet<>());
        }
        for (Country country : getAll()) {
            result.get(country.getRegion()).add(country);
        }
        return result;
    }
    ```

1. Returns a dictionary which maps each region to its most populous country.

    Sample solution:

    ```java
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
    ```

1. Returns a dictionary which maps each region the list of its countries ordered by their capital names to their continent.

    Sample solution #1:

    ```java
    @Override
    public Map<Country.Region, List<Country>> getCountriesByRegionsOrderByCapitals() {
        Map<Country.Region, List<Country>> result = new EnumMap<>(Country.Region.class);
        for (Map.Entry<Country.Region, Set<Country>> entry : getCountriesByRegions().entrySet()) {
            result.put(entry.getKey(), new ArrayList<>(entry.getValue()));
            result.get(entry.getKey()).sort(new Comparator<Country>() {
                @Override
                public int compare(Country o1, Country o2) {
                    return Objects.compare(o1.getCapital(), o2.getCapital(), Comparator.naturalOrder());
                }
            });
        }
        return result;
    }
    ```

    Sample solution #2:

    ```java
    @Override
    public Map<Country.Region, List<Country>> getCountriesByRegionsOrderByCapitals() {
        Map<Country.Region, List<Country>> result = new EnumMap<>(Country.Region.class);
        for (Country country: getAll()) {
            if(!result.containsKey(country.getRegion())) {
                result.put(country.getRegion(), new ArrayList<>());
            }
            result.get(country.getRegion()).add(country);
        }
        return result;
    }
    ```

    Sample solution #3:

    ```java
    @Override
    public Map<Country.Region, List<Country>> getCountriesByRegionsOrderByCapitals() {
        Map<Country.Region, List<Country>> result = new EnumMap<>(Country.Region.class);
        for (Country.Region region : Country.Region.values()) {
            result.put(region, new ArrayList<>());
        }
        for (Country country : getAll()) {
            result.get(country.getRegion()).add(country);
        }
        return result;
    }
    ```

1. Returns a dictionary which maps each region to the corresponding countries which population is between the given bounds (inclusive) to their continent.

    Sample solution:

    ```java
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
    ```

1. Returns a dictionary which maps each region to the corresponding country codes, then each country code to the corresponding country.

    Sample solution:

    ```java
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
    ```

1. Returns a dictionary which maps region to the letters that occur in the first place of corresponding country names, then each letter to the corresponding countries.

    Sample solution:

    ```java
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
    ```

1. Returns a dictionary which maps the letters that occur in the first place of corresponding country names to regions, then each letter to the corresponding countries.

    Sample solution:

    ```java
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
    ```
    

    