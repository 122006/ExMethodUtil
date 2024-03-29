package zircon.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import zircon.example.ExArray;
import zircon.example.ExCollection;
import zircon.example.ExComparable;
import zircon.example.ExObject;
import zircon.example.ExReflection;
import zircon.example.ExStream;
import zircon.example.ExString;


@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class ExMethodTest {
    @Test
    public void testTempString() {
        {
            assertEquals("class=ExMethodTest.class", $"class=${ExMethodTest.class.getSimpleName()}.class");
            assertEquals("listString=123,456", $"listString=${Arrays.asList("123",456).join(",")}");
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
            assertEquals("test", nullStr.nullOr("test").let(a->a.length()));
            assertFalse(this.isNull());
            assertEquals(this, this.nullOr(new ExMethodTest()));
            assertEquals("test1", "test".convert(a -> a += "1"));
            assertArrayEquals(new String[]{"test1"}, new String[]{"test"}.convert(a -> {
                a[0] += "1";
                return a;
            }));

            assertArrayEquals(new String[]{"test1"}, (new String[]{"test"}).let(a -> a[0] += "1"));
            assertArrayEquals(new int[]{2}, (new int[]{1}).let(a -> a[0] += 1));
            assertTrue("123".isInstanceOf(CharSequence.class));
            assertFalse("123".isNoInstanceOf(CharSequence.class));

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
            assertEquals(Arrays.asList("test3", "test2", "test1"), strings.sortBy(a -> 3 - a.regex("\\d").head()
                                                                                            .toInt()));
            final List<Integer> intList = Arrays.asList(3, 2, 1);
            assertEquals(Arrays.asList(1,2,3), intList.sort());
            assertEquals(Arrays.asList(1,2,3), intList.copy2List().let(it->it.sort(Comparator.comparingInt(a->a))));
            assertEquals(Arrays.asList(1,2,3), intList.sortBy(a->a));
            strings.sortBy(function);
            ExCollection.sortBy(strings, function);
            assertEquals(Arrays.asList("test1", "test2", "test3", "test1", "test2", "test3"), List
                    .create("test1", "test2", "test3").addVarargs(strings.toArray(new String[0])));
            strings.map(a -> Integer.parseInt(a.nullOr("123").substring(4)));
            assertThrowsExactly(NumberFormatException.class, () -> strings.map(a -> Integer.valueOf(a)));
            assertThrowsExactly(NumberFormatException.class, () -> strings.map(a -> Integer.parseInt(a)));
            ExCollection.map(strings, a -> a.substring(4)).map(Integer::parseInt);
            assertEquals(Arrays.asList(1, 2, 3), strings.map(a -> a.regex("\\d")).stream().flat().map(String::toInt)
                                                        .list());
            assertEquals(123, $throw(() -> Integer.valueOf("123")).get());
            assertEquals("123,456", Arrays.asList(123, 456).join(","));
            assertTrue("123".oneOf("123", "456"));
            assertEquals("1,2,3",
                    Arrays.asList("test1", "test2", "test3", "test1", "test2", "test3")
                          .groupBy(a -> a.regex("\\d").head())
                          .keySet().copy2List().sortBy(a->a).join(","));
            assertEquals("2,2,2",
                    Arrays.asList("test1", "test2", "test3", "test1", "test2", "test3")
                          .groupBy(a -> a.regex("\\d").head(), List::size)
                          .values().copy2List().sortBy(a->a).join(","));
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
            assertEquals("123", nullStr.nullOrEmpty("123"));
            assertEquals("123", "".nullOrEmpty("123"));
            assertEquals("123", "123".nullOrEmpty(""));
            final List<Integer> collect = Stream.of("1").map(String::toInteger).collect(Collectors.toList());
            assertEquals(Arrays.asList(1), collect);
            assertEquals(Arrays.asList("test1", "test2"), Stream.of("test\\d").map("test1test2"::regex).flat()
                                                                .collect(Collectors.toList()));
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

    @Test
    public void testReflection() {
        assertEquals("fv1", FatherReflectionTestClass.class.getStaticFieldValue("value"));
        assertEquals("fv2", FatherReflectionTestClass.class.getStaticFieldValue("value2"));
        assertEquals("fv3", FatherReflectionTestClass.class.getStaticFieldValue("fatherValue"));
        assertEquals((String) null, FatherReflectionTestClass.class.getStaticFieldValue("value3"));
        assertEquals("v1", FatherReflectionTestClass.ReflectionReflectionTestClass.class.getStaticFieldValue("value"));
        assertEquals("v2", FatherReflectionTestClass.ReflectionReflectionTestClass.class.getStaticFieldValue("value2"));
        assertEquals((String) null, FatherReflectionTestClass.ReflectionReflectionTestClass.class.getStaticFieldValue("fatherValue"));
        assertEquals((String) null, FatherReflectionTestClass.ReflectionReflectionTestClass.class.getStaticFieldValue("value3"));
        final FatherReflectionTestClass fatherTestClass = new FatherReflectionTestClass();
        assertEquals("fv3", fatherTestClass.reflectionFieldValue("fatherValue"));
        assertEquals("fv03", fatherTestClass.reflectionFieldValue("value3"));
        assertEquals((String) null, fatherTestClass.reflectionFieldValue("value4"));
        final FatherReflectionTestClass.ReflectionReflectionTestClass reflectionTestClass = new FatherReflectionTestClass.ReflectionReflectionTestClass();
        assertEquals("v4", reflectionTestClass.reflectionFieldValue("value4"));
        assertEquals("v03", reflectionTestClass.reflectionFieldValue("value3"));


        assertEquals("fsm", fatherTestClass.reflectionInvokeMethod("staticMethod"));
        assertEquals("fsm", fatherTestClass.getClass().invokeStaticMethod("staticMethod"));
        assertEquals("fm1", fatherTestClass.reflectionInvokeMethod("method1"));
        assertEquals("fm2", fatherTestClass.reflectionInvokeMethod("method2", "str", 12));
        assertEquals("fm22", fatherTestClass.reflectionInvokeMethod("method2", "str", "str2"));
        assertEquals((String) null, fatherTestClass.reflectionInvokeMethod("method2"));
        assertEquals("m3", reflectionTestClass.reflectionInvokeMethod("method3"));
        assertEquals((String) null, fatherTestClass.reflectionInvokeMethod("method4"));
        assertEquals((String) null, fatherTestClass.reflectionInvokeMethod("method2", "str", BigDecimal.ZERO));
        assertEquals("m5", reflectionTestClass.reflectionInvokeMethod("method5", reflectionTestClass));

    }

    @Test
    public void testShow() {

        //例1
        "java,Zircon,:)".split(",")
                        .find(a -> a.startsWith("Z"))             //为字符串数组增加find方法
                        .let(System.out::println);//print: Zircon //为Object增加流式处理函数，避免额外定义变量

        //例2
        final int num = "num is 1"
                .regex("\\d")   //为String增加快速正则匹配方法
                .head()         //为集合增加选取首个值的方法
                .nullOr("123")  //空安全方法，如果调用对象为null，不会空指针异常而是使用参数值（举例用，这里一定不为null可省略）
                .toInteger()       //为String增加快速转化int的方法
                .convert(i -> Math.abs(i + 1)); //为Object增加的流式转化函数，避免先定义i再修改导致无法修改final变量
        new Thread(() -> System.out.println(num)).start();// print 2 //lambda表达式中引用的方法变量必须为final。
    }
}

