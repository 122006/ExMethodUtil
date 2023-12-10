package zircon.example;

import org.junit.jupiter.api.Test;
import zircon.data.ThrowFunction;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class ExMethodTest {
    @Test
    public void testTempString() {
        {
            assertEquals("class=ExMethodTest.class", $"class=${ExMethodTest.class.getSimpleName()}.class");
        }
    }

    @Test
    public void testExArray() {
        String[] arr = new String[]{"1", "2", "3"};
        {
            final String[] add = arr.add("4");
            assertEquals("4", add[3]);
        }
        {
            final String add = arr.get(2);
            assertEquals("3", add);
        }
        {
            assertEquals(Arrays.asList("1", "2", "3"), arr.toList());
        }
        {
            String[] arr2 = new String[]{"1", "2", "3"};
            assertArrayEquals(new String[]{"1", "2", "3", "1", "2", "3"}, arr.add(arr2));
        }
        {
            assertArrayEquals(new String[]{"2"}, arr.findAll(a -> a.eq("2")));
            assertEquals("2", arr.find(a -> a.eq("2")));
            assertArrayEquals(new String[]{"2"}, arr.filter(a -> a.eq("2")));
        }
        {
            int[] arr2 = new int[]{1, 2, 3};
            assertArrayEquals(new int[]{2}, arr2.findAll(a -> a == 2));
            assertEquals(2, arr2.find(a -> a == 2, 3));
            assertArrayEquals(new int[]{2}, arr2.filter(a -> a == 2));
        }
        {
            assertArrayEquals(arr, arr.copy());
            int[] arr2 = new int[]{1, 2, 3};
            assertArrayEquals(arr2, arr2.copy());
        }
        {
            assertArrayEquals(new String[3], String.class.createObjectArray(3));
            assertArrayEquals(new Integer[3], Integer.class.createObjectArray(3));
        }
    }

    @Test
    public void testExStream() {
        {
            final Stream<String> test2 = Stream.of("test2", "test2", "test1");
            final List<String> list = test2.list();
            assertEquals(Arrays.asList("test2", "test2", "test1"), list);
        }
        {
            final Stream<String> test2 = Stream.of("test2", "test2", "test1");
            List<String> list = test2.distinctByKey(a -> a).list();
            assertEquals(Arrays.asList("test2", "test1"), list);
        }
        {
            final Stream<String> test2 = Stream.of("test2", "test2", "test1");
            Set<String> set = test2.set();
            final HashSet<String> strings = new HashSet<String>();
            strings.add("test2");
            strings.add("test1");
            assertEquals(strings, set);

        }
        {
            String[] strings = new String[]{"test2", "test2", "test1"};
            final Stream<String> test2 = strings.stream();
            test2.forEachIndex((str, index) -> {
                assertEquals(strings[index], str);
            });
        }
    }

    @Test
    public void testExObject() {
        {
            String nullStr = null;
            assertTrue(nullStr.isNull());
            assertEquals("test", nullStr.nullOr("test"));
            assertFalse(this.isNull());
            assertEquals(this, this.nullOr(new ExMethodTest()));
            assertEquals("test1", "test".convert(a -> a += "1"));
            assertArrayEquals(new String[]{"test1"}, new String[]{"test"}.convert(a -> {
                a[0] += "1";
                return a;
            }));

            assertArrayEquals(new String[]{"test1"}, (new String[]{"test"}).let(a -> a[0] += "1"));
            assertArrayEquals(new int[]{2}, (new int[]{1}).let(a -> a[0] += 1));
        }
    }

    @Test
    public void testExCollection() {
        {
            List<String> strings = Arrays.asList("test1", "test2", "test3");
            assertEquals("test1", strings.subList(0, 1).find(a -> a.endsWith("1")));
            assertEquals(Arrays.asList("test1"), strings.findAll((a) -> a.endsWith("1")));
            strings.forEachIndex((str, index) -> {
                assertEquals(strings.get(index), str);
            });
            Function<String, Integer> function = a -> 3 - a.regex("\\d").head().toInt();
            assertEquals(Arrays.asList("test3", "test2", "test1"), strings.sortBy(a -> 3 - a.regex("\\d").head().toInt()));
            ExCollection.sortBy(strings, function);
            assertEquals(Arrays.asList("test1", "test2", "test3", "test1", "test2", "test3"), List.create("test1", "test2", "test3").addVarargs(strings.toArray(new String[0])));
            strings.map(a -> Integer.parseInt(a.nullOr("123").substring(4)));
            assertThrowsExactly(NumberFormatException.class, () -> strings.map(a -> Integer.valueOf(a)));
            assertThrowsExactly(NumberFormatException.class, () -> strings.map(a -> Integer.parseInt(a)));
            ExCollection.map(strings, a -> a.substring(4)).map(Integer::parseInt);
            assertEquals(Arrays.asList("1", "2", "3"), strings.map(a -> a.regex("\\d")).stream().flat().list());
            assertEquals(123,$throw(()-> Integer.valueOf("123")).get());
        }
    }

    @Test
    public void testExString() {
        {
            String nullStr = null;
            assertTrue(nullStr.isEmpty());
            assertTrue("".isEmpty());
            assertEquals(Arrays.asList("test1", "test2"), "test1test2".regex("test\\d"));
            assertEquals(1, "1".toInteger());
            assertEquals(1, "1".toInt());
            final List<Integer> collect = Stream.of("1").map(String::toInteger).collect(Collectors.toList());
            assertEquals(Arrays.asList(1), collect);
            assertEquals(Arrays.asList("test1", "test2"), Stream.of("test\\d").map("test1test2"::regex).flat().collect(Collectors.toList()));
        }
    }

    @Test
    public void testExComparable() {
        {
            String v1 = "123";
            assertTrue(v1.lt("456"));
            assertTrue(v1.le("123"));
            assertFalse(v1.lt("123"));
            assertTrue(v1.ge("123"));
            assertFalse(v1.gt("123"));
            assertTrue(v1.gt("023"));
            assertTrue(v1.in("123", "223"));
            assertFalse(v1.in("023", "123"));
        }

        {
            BigDecimal bigDecimal = new BigDecimal("123");
            assertTrue(bigDecimal.lt("456".toBigDecimal(2)));
            assertTrue(bigDecimal.le("123".toBigDecimal(2)));
            assertFalse(bigDecimal.lt("123".toBigDecimal(2)));
            assertTrue(bigDecimal.ge("123".toBigDecimal(2)));
            assertFalse(bigDecimal.gt("123".toBigDecimal(2)));
            assertTrue(bigDecimal.gt("023".toBigDecimal(2)));
            assertTrue(bigDecimal.eq("123".toBigDecimal(2)));
        }
    }
}
