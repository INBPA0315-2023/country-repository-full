# Implementing `BasicCountryQueries`

1. Returns the value of the maximum population.

    ```java
    List<Country> countries = getAll();
    long max = countries.get(0).getPopulation();
    // long max = getAll().get(0).getPopulation();
    for (int i = 1; i < countries.size(); i++) {
        if (countries.get(i).getPopulation() > max) {
            max = countries.get(i).getPopulation();
        }
    }
    return max;
    ```

    Comments

    * You can decide whether you declare a new reference for the list or invoke the `getAll()` method multiple times. Note that the method always returns a brand new, unmodifiable list.

1. Returns the value of the average population.

    ```java
    @Override
    public double getAveragePopulation() {
        long sum = 0;
        for (Country country : getAll()) {
            sum += country.getPopulation();
        }
        return (double) (sum / getAll().size());
    }
    ```

    Comments

    * Don't forget that at least one operand should have the type of `double`.

1. Returns the count of European countries.

    ```java
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
    ```

    Comments

    * You can use operands `==` and `!=` in the case of enum types.

1. Returns the count of countries that are located in the given continent.

    ```java
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
    ```

    Comments:

    * The previous `getCountOfEuropeanCountries()` method can be simplyfied invoking this method. Try it!

1. Returns the population of the given continent.

    ```java
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
    ```

1. Returns whether a country exists having the given population.

    ```java
    @Override
    public boolean isPopulationExists(long population) {
        for (Country country : getAll()) {
            if (country.getPopulation() == population) {
                return true;
            }
        }
        return false;
    }
    ```

1. Returns the country which has the given code.
    ```java
    @Override
    public Country getCountryByCode(@NonNull String code) {
        for (Country country : getAll()) {
            if (Objects.equals(country.getCode(), code)) {
                return country;
            }
        }
        return null;
    }
    ```

1. Returns the country which has the given code.
    
    ```java
    @Override
    public Optional<Country> getOptionalCountryByCode(@NonNull String code) {
        for (Country country : getAll()) {
            if (Objects.equals(country.getCode(), code)) {
                return Optional.of(country);
            }
        }
        return Optional.empty();
    }
    ```

    Comments:

    * Type `Optional` was introduced in Java 8. It is the recommended way to represent a value that can be missing. Note that `Optional` objects are immutable.

1. Returns the country which has the greatest population of its continent.
    
    ```java
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
    ```

1. Returns the first country which name starts with the given letter.
    
    ```java
    @Override
    public Optional<Country> getFirstCountryByStartingLetter(char letter) {
        for (Country country : getAll()) {
            if (country.getName().charAt(0) == letter) {
                return Optional.of(country);
            }
        }
        return Optional.empty();
    }
    ```
