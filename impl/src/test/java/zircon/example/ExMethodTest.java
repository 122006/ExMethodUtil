package zircon.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


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
            final List<String> list = test2.distinctByKey(a -> a).list();
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
            String[] strings=new String[]{"test2", "test2", "test1"};
            final Stream<String> test2 =strings.stream();
            test2.forEachIndex((str,index)->{
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
            assertEquals(this, this.nullOr("12"));
        }
    }

    @Test
    public void testExCollection() {
        {
            List<String> strings = Arrays.asList("test1", "test2", "test3");
            assertEquals("test1", strings.subList(0, 1).find((a) -> a.endsWith("1")));
            assertEquals(Arrays.asList("test1"), strings.findAll((a) -> a.endsWith("1")));
            strings.forEachIndex((str,index)->{
                assertEquals(strings.get(index), str);
            });
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
//            Stream.of("1").map("123"::toInteger).collect(Collectors.toList());

            assertEquals(Arrays.asList(1), collect);

        }
    }
    @Test
    public void testExComparable() {
        {
            String v1="123";
            assertTrue(v1.lt("456"));
            assertTrue(v1.le("123"));
            assertFalse(v1.lt("123"));
            assertTrue(v1.ge("123"));
            assertFalse(v1.gt("123"));
            assertTrue(v1.gt("023"));
        }

        {
            BigDecimal bigDecimal=new BigDecimal("123");
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
