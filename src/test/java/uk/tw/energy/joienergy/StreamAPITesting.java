package uk.tw.energy.joienergy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StreamAPITesting {

  List<Product> productList = Arrays.asList(new Product(23, "potatoes"),
      new Product(14, "orange"), new Product(13, "lemon"),
      new Product(23, "bread"), new Product(13, "sugar"));


  Map<String, List<Product>> productByCompany = new HashMap<>();

  @BeforeEach
  void setUp() {
    productByCompany.put("BigC", Arrays.asList(
        new Product(10, "soap"),
        new Product(33, "rice")));

    productByCompany.put("Lotus", Arrays.asList(
        new Product(20, "soap"),
        new Product(31, "rice")));

    productByCompany.put("Makro", Arrays.asList(
        new Product(22, "soap"),
        new Product(29, "rice")));
  }

  @Test
  void getName() {
    final List<String> names = productList.stream().map(Product::getName)
        .collect(Collectors.toList());

    names.forEach(System.out::println);
  }

  @Test
  void getName_joiner() {
    final String namesJoiner = productList.stream().map(Product::getName)
        .collect(Collectors.joining(",", "[", "]"));

    System.out.println(namesJoiner);
  }

  @Test
  void getAveraging() {
    final Double avg = productList.stream().collect(Collectors.averagingInt(Product::getPrice));
    System.out.println(avg);
  }

  @Test
  void groupBy() {
    final Map<Integer, List<Product>> collect = productList.stream()
        .collect(Collectors.groupingBy(Product::getPrice));

    collect.forEach((key, value) -> System.out.println(key + ":" + value.size()));
  }


  @Test
  void summing_min_max() {
    final Integer sum = productList.stream().collect(Collectors.summingInt(Product::getPrice));
    final Integer min = productList.stream()
        .collect(Collectors.minBy(Comparator.comparing(Product::getPrice)))
        .map(Product::getPrice).orElse(0);
    final Integer max = productList.stream().map(Product::getPrice).max(Integer::compareTo)
        .orElse(0);

    System.out.println("done");
  }

  @Test
  void statistic() {
    final IntSummaryStatistics summaryStatistics = productList.stream()
        .collect(Collectors.summarizingInt(Product::getPrice));

    System.out.println(summaryStatistics.getMax());
    System.out.println(summaryStatistics.getMin());
    System.out.println(summaryStatistics.getAverage());
    System.out.println(summaryStatistics.getCount());
    System.out.println(summaryStatistics.getSum());
  }


  @Test
  void partitionBy() {
    final Map<Boolean, List<Product>> collect = productList.stream()
        .collect(Collectors.partitioningBy(product -> product.getPrice() > 15));

    collect.forEach((aBoolean, products) -> System.out.println(
        aBoolean.toString() + " : " + products.stream().map(Product::getName)
            .collect(Collectors.joining(",", "[", "]"))));
  }

  @Test
  void findBestCheapestRiceStore() {
    final String bestRicePriceCompany = findCheapestStore("soap");
    System.out.println(bestRicePriceCompany);
  }

  @Test
  void name() {
  }

  private String findCheapestStore(String productName) {
    final Product minRicePrice = productByCompany.values().stream()
        .flatMap(List::stream)
        .filter(product -> product.getName().equals(productName))
        .min(Comparator.comparing(Product::getPrice))
        .get();

    return productByCompany.entrySet().stream()
        .filter(stringListEntry -> stringListEntry.getValue().contains(minRicePrice))
        .map(Entry::getKey)
        .findFirst().orElse("NOT FOUND");
  }
}
