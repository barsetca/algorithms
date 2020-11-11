package lesson5.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackpackTask {

  private static Map<Integer, List<Thing>> map = new HashMap<>();

  public static void main(String[] args) {

    Thing thing1 = new Thing("Книга", 1, 3000);
    Thing thing2 = new Thing("Бинокль", 2, 5_000);
    Thing thing3 = new Thing("Аптечка", 2, 5_500);
    Thing thing4 = new Thing("Ноутбук", 4, 10_000);
    Thing thing5 = new Thing("Котелок", 1, 2500);
    List<Thing> fullList = new ArrayList<>();
    fullList.add(thing1);
    fullList.add(thing2);
    fullList.add(thing3);
    fullList.add(thing4);
    fullList.add(thing5);

    List<Thing> targetThings = getTargetListThings(fullList, 5);
    targetThings.forEach(System.out::println);
//    Thing{name='Аптечка', weight=2, cost=5500}
//    Thing{name='Книга', weight=1, cost=3000}
//    Thing{name='Бинокль', weight=2, cost=5000}
  }


  public static List<Thing> getTargetListThings(List<Thing> fullList, int maxWight) {
    if (maxWight <= 0) {
      return new ArrayList<>();
    }
    if (fullList.stream().mapToInt(Thing::getWeight).sum() <= maxWight) {
      return fullList;
    }
    getFill(1, maxWight, fullList);
    getFillMax(1, maxWight);

    return map.get(maxWight);
  }

  //предварительное заполнение map через рекурсию
  private static Map<Integer, List<Thing>> getFill(int minWight, int maxWight,
      List<Thing> fullList) {

    if (minWight > maxWight) {
      return map;
    }
    List<Thing> tempList = new ArrayList<>();
    for (Thing thing : fullList) {
      if (thing.getWeight() <= minWight) {
        tempList.add(thing);
      }
    }
    map.put(minWight, tempList);

    return getFill(minWight + 1, maxWight, fullList);
  }

  //динамическое заполнение матрицы на основе предварительной map через рекурсию
  private static Map<Integer, List<Thing>> getFillMax(int minWight, int maxWight) {

    if (minWight > maxWight) {
      return map;
    }
    //заполнения минимального рюкзака
    if (minWight == 1) {
      List<Thing> first = map.get(1);
      if (!first.isEmpty()) {
        if (first.size() > 1) {
          Thing max = first.get(0);
          for (Thing thing : first) {
            if (thing.getCost() > max.getCost()) {
              max = thing;
            }
          }
          first.clear();
          first.add(max);
          map.put(1, first);
        }
      }
    } else {
      //заполнение оставшихся рюкзаков
      fillBackpacks(minWight);
    }
    return getFillMax(minWight + 1, maxWight);

  }

  //поиск общей суммы рюкзака
  private static int getSumCostThings(List<Thing> things) {
    return things.stream().mapToInt(Thing::getCost).sum();
  }


  private static void fillBackpacks(int minWight) {
    //за стартовый максимальный набор принимаем максимальный предыдущий рюкзак
    List<Thing> max = new ArrayList<>(map.get(minWight - 1));
    //набор всех вещей походящих по размеру для текущего рюкзака из предварительной map
    List<Thing> current = map.get(minWight);
    if (!current.isEmpty()) {
      for (Thing thing : current) {
        // если вещь занимает весь рюкзак и стоит дороже уже существующего максимума в рюкзакеБ
        // то меняем содержимое максимального набора
        if (thing.getWeight() == minWight && thing.getCost() >= getSumCostThings(max)) {
          max.clear();
          max.add(thing);
        }
        // если вещь занимает меньше рюкзака и и не содержится в существующем максимальном наборе
        // то ищем максимум заполнения оставшегося пространства
        if (thing.getWeight() < minWight && !max.contains(thing)) {
          List<Thing> localMax = new ArrayList<>(map.get(minWight - thing.getWeight()));
          // если этот набор не содержит вещь то добавляем внего вещь и сранвиаем с ценой максимума
          // и если больше то меняем содержимое макисмального набора
          if (!localMax.contains(thing)) {
            localMax.add(thing);
            if (getSumCostThings(localMax) > getSumCostThings(max)) {
              max = localMax;
            }
          }
        }
      }
    }
    map.put(minWight, max);
  }

}
/*
Условие: Имеется рюкзак с ограниченной вместимостью по массе; также имеется набор вещей с
определенным весом и ценностью.
Необходимо подобрать такой набор вещей, чтобы он помещался в рюкзаке и
имел максимальную ценность (стоимость).
 Книга	 1	 600
 Бинокль	 2	 5000
 Аптечка	 4	 1500
 Ноутбук	 2	 40000
 Котелок	 1	 500

 */