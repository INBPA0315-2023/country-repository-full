# Implementing `SortingCountryQueries`

1. Returns a new list of countries ordered by:
    1. their populations in descending order

    ```java
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
    ```

    Comments:

    1. The easiest way of copying a `List` is passing it to the constructor of a new `ArrayList` implementation.
    1. Note that the exercises ask you to return a **new** list. The original ones are unmodifiable, so you cannot sort them.

1. Returns a new list of countries ordered by:
    1. the names of their capitals
    1. their population in descending order

    ```java
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
    ```

    Comments:

    * Similar to methods `Integer.compare()`, `Double.compare()`, and other ones you can use method `Objects.compare()` to invoke the `compareTo` method of the first parameter, passing the second object to it in the background.

1. Returns a new list of countries ordered by:
    1. the length of the names of their capitals
    2. their capitals

    ```java
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
    ```
