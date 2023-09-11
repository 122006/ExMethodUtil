package zircon.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ExMethodTest {
    @Test
    public void testExArray() {
        final Class<ExMethodTest> clazz = ExMethodTest.class;
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
    }

    @Test
    public void testExStream() {
        final Class<ExStream> clazz = ExStream.class;
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
    }

    @Test
    public void testExObject() {
        final Class<ExObject> clazz = ExObject.class;
        {
            String nullStr = null;
            assertTrue(nullStr.isNull());
            assertEquals("test", nullStr.nullOr("test"));
        }
    }

    @Test
    public void testExCollection() {
        final Class<ExCollection> clazz = ExCollection.class;
        {
            List<String> strings = Arrays.asList("test1", "test2", "test3");
            assertEquals("test1", strings.subList(0, 1).find((a) -> a.endsWith("1")));
            assertEquals(Arrays.asList("test1"), strings.findAll((a) -> a.endsWith("1")));
        }
    }

    @Test
    public void testExString() {
        final Class<ExString> clazz = ExString.class;
        {
            String nullStr = null;
            assertTrue(nullStr.isEmpty());
            assertTrue("".isEmpty());
            assertEquals(Arrays.asList("test1", "test2"), "test1test2".regex("test\\d"));
        }
    }
}
