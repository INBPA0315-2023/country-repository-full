# Implementing `CollectionCountryQueries`

1. Returns the name of each country.

    ```java
    @Override
    public Set<String> getCountryNames() {
        Set<String> names = new HashSet<>();
        for (Country country : getAll()) {
            names.add(country.getName());
        }
        return names;
    }
    ```

    Comments:

    * You can use both `TreeSet` and `HashSet` for the type if the exercise doesn't require a specific type. However, you should consider the following:
        * `HashSet` can be only used if the elements implement the `hashCode()` method.
        * `TreeSet` can be only used if the elements implement the `Comparable` interface or you specify a `Comparator` in the constructor.

1. Returns the capital of each country in ascending order.

    ```java
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
    ```

    Comments:

    * There are two ways to sort a `List` using a `Comparator` object:
        * You can invoke the instance-level `sort()` method.
        * You can invoke the `Collections.sort()` method.
    * Note that the instance-level approach should always get a `Comparator` object. Thus, you can use the static method `Comparator.naturalOrder()` to specify a `Comparator` for the natural order of the elements.

1. Returns the capital of each country in descending order.

    ```java
    @Override
    public List<String> getCapitalsOrderByNameDesc() {
        List<String> capitals = new ArrayList<>();
        for (Country country : getAll()) {
            capitals.add(country.getCapital());
        }
        capitals.sort(Comparator.reverseOrder());
        return capitals;
    }
    ```

    Comments:

    * Similar to static method `Comparator.naturalOrder()`, you can use the static method `Comparator.reverseOrder()` for specifying the reversed order.

1. Returns the name of each European country.

    ```java
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
    ```

1. Returns the name of each country which is located in the given continent.

    ```java
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
    ```

    Comments:

    * Try to implement method `getNamesOfEuropeanCountries()` by invoking this method.


1. Returns the set of countries which population is less than the given limit.

    ```java
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
    ```

1. Returns all the population values that belong to the given continent.

    ```java
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
    ```

1. Returns each country which has the given population.

    ```java
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
    ```

1. Returns each country which has a population which is between the given bounds (inclusive).

    ```java
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
    ```
