package zircon.example;

import org.junit.jupiter.api.Test;
import zircon.example.ExArray;
import zircon.example.ExBigDecimal;
import zircon.example.ExCollection;
import zircon.example.ExCollection.SumBigDecimal;
import zircon.example.ExCollection.SumDouble;
import zircon.example.ExCollection.SumInteger;
import zircon.example.ExComparable;
import zircon.example.ExMap;
import zircon.example.ExObject;
import zircon.example.ExReflection;
import zircon.example.ExStream;
import zircon.example.ExString;
import zircon.example.ExThread;
import zircon.example.extendsobj.ChildChildObj;
import zircon.example.extendsobj.ChildObj;
import zircon.example.extendsobj.FatherObj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static java.util.Objects.isNull;


@SuppressWarnings({"ArraysAsListWithZeroOrOneArgument"})
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
        assertEquals(Arrays.asList(1, 2, 3), new int[]{1, 2, 3}.toList());
        assertEquals(Arrays.asList(1f, 2f, 3f), new float[]{1f, 2f, 3f}.toList());
        assertEquals(Arrays.asList(1d, 2d, 3d), new double[]{1d, 2d, 3d}.toList());
        assertEquals(Arrays.asList(true, false, true), new boolean[]{true, false, true}.toList());
        assertEquals(Arrays.asList(1L, 2L, 3L), new long[]{1L, 2L, 3L}.toList());
        assertEquals(Arrays.asList('1', '2', '3'), new char[]{'1', '2', '3'}.toList());
        assertEquals(Arrays.asList((byte) 1, (byte) 2, (byte) 3), new byte[]{(byte) 1, (byte) 2, (byte) 3}.toList());
        assertEquals(Arrays.asList((short) 1, (short) 2, (short) 3), new short[]{(short) 1, (short) 2, (short) 3}.toList());
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
            assertEquals("test", nullStr.orElse("test"));
            assertEquals("test", nullStr.orElse("test").with(a -> a.length()));
            assertFalse(this.isNull());
            assertEquals(this, this.orElse(new ExMethodTest()));
            assertEquals("test1", "test".convert(a -> a += "1"));
            assertArrayEquals(new String[]{"test1"}, new String[]{"test"}.convert(a -> {
                a[0] += "1";
                return a;
            }));

            assertArrayEquals(new String[]{"test1"}, (new String[]{"test"}).with(a -> a[0] += "1"));
            assertArrayEquals(new int[]{2}, (new int[]{1}).with(a -> a[0] += 1));
            assertTrue("123".isInstanceOf(CharSequence.class));
            assertTrue("123".equals("123"));
            assertFalse("123".noEquals("123"));
            assertFalse("123".isNoInstanceOf(CharSequence.class));
            assertTrue(getStackTrace().is(ExMethodTest.class, "testExObject"));
            ((Runnable) () -> {
                assertTrue(getStackTrace().is(ExMethodTest.class, "testExObject"));
            }).run();
            ((Runnable) new Runnable() {
                @Override
                public void run() {
                    final StackTraceElement stackTrace = getStackTrace();
                    assertTrue(stackTrace.is(ExMethodTest.class, "testExObject"));
                    assertTrue(getStackTrace(0, false).getClassName().contains("ExMethodTest"));
                }
            }).run();
            assertEquals("123", Optional.ofNullable(nullStr).orElse("123"));
            try {
                assertEquals("123", Optional.ofNullable(nullStr).orElseThrow(() -> {
                    throw new RuntimeException();
                }));
                String a = Optional.ofNullable(nullStr).orElseThrow(RuntimeException::new);
                String b = String.format(Optional.ofNullable(nullStr).orElseThrow(RuntimeException::new));
                assertEquals("123", Optional.ofNullable(nullStr).orElseThrow(RuntimeException::new));
            } catch (RuntimeException e) {
            }
            assertEquals("123", nullStr.orElse("123"));
            assertEquals("123", "".orElse("123"));
            assertEquals("123", Arrays.asList().orElse(Arrays.asList("123")).head());
            assertEquals("123", new String[0].orElse(Arrays.asList("123").toArray(String.class)).get(0));
        }
        {
            assertTrue(isNull(null));
        }

        {
            int a = 0;
            a++;
            assertEquals(BigDecimal.valueOf(2), BigDecimal.valueOf(2).with(a, (v, b) -> v.add(b)));
        }
    }

    @Test
    public void testExBigDecimal() {
        {
            assertEquals(BigDecimal.valueOf(10.1), BigDecimal.valueOf(10).add(0.1));
            assertEquals(BigDecimal.valueOf(10.23), "10.125".toBigDecimal(2, RoundingMode.HALF_UP).add(0.1));
            assertEquals(BigDecimal.valueOf(10.03), "10.125".toBigDecimal(2, RoundingMode.HALF_UP).subtract(0.1));
            assertEquals(BigDecimal.valueOf(1.013), "10.125".toBigDecimal(2, RoundingMode.HALF_UP).multiply(0.1));
            assertEquals(BigDecimal.ZERO, "10.125".toBigDecimal(2, RoundingMode.HALF_UP)
                    .divide(0, RoundingMode.HALF_UP));
            assertEquals(BigDecimal.valueOf(1.02), "10.175".toBigDecimal(2, RoundingMode.HALF_UP).divide(10));
            assertEquals(BigDecimal.ZERO, "10.175".toBigDecimal(2, RoundingMode.HALF_UP).divide(0));
            assertEquals("10.20", "10.2".toBigDecimal().toString(2, RoundingMode.HALF_UP));

        }
    }

    @Test
    public void testExMath() {
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
            final List<Integer> intList = Arrays.asList(3, 2, 1);
            assertEquals(Arrays.asList(1, 2, 3), intList.sort());
            assertEquals(Arrays.asList(1, 2, 3), intList.copy2List()
                    .with(it -> it.sort(Comparator.comparingInt(a -> a))));
            assertEquals(Arrays.asList(1, 2, 3), intList.sortBy(a -> a));
            strings.sortBy(function);
            ExCollection.sortBy(strings, function);
            assertEquals(Arrays.asList("test1", "test2"), strings.filterContains(Arrays.asList(1, 2), (a, b) -> a
                    .regex("\\d").head().toInt() == b));
            assertEquals(Arrays.asList("test2"), strings.findAllEquals((a) -> a.regex("\\d").head().toInt(), 2));
            assertEquals(Arrays.asList("test1", "test2", "test3", "test1", "test2", "test3"), List
                    .create("test1", "test2", "test3").addVarargs(strings.toArray(new String[0])));
            strings.map(a -> Integer.parseInt(a.orElse("123").substring(4)));
            assertThrowsExactly(NumberFormatException.class, () -> strings.map(a -> Integer.valueOf(a)));
            assertThrowsExactly(NumberFormatException.class, () -> strings.map(a -> Integer.parseInt(a)));
            ExCollection.map(strings, a -> a.substring(4)).map(Integer::parseInt);
            assertEquals(Arrays.asList(1, 2, 3), strings.map(a -> a.regex("\\d")).stream().flat().map(String::toInt)
                    .list());
            assertArrayEquals(strings.toArray(),strings.toArray(String.class));
            assertArrayEquals(strings.toArray(),strings.toArray(new String[0]));
            assertEquals(123, $throw(() -> Integer.valueOf("123")).get());
            assertEquals("123,456", Arrays.asList(123, 456).join(","));
            assertTrue("123".oneOf("123", "456"));
            assertEquals("1,2,3", Arrays.asList("test1", "test2", "test3", "test1", "test2", "test3")
                    .groupBy(a -> a.regex("\\d").head()).keySet().copy2List().sortBy(a -> a)
                    .join(","));
            assertEquals("2,2,2", Arrays.asList("test1", "test2", "test3", "test1", "test2", "test3")
                    .groupBy(a -> a.regex("\\d").head(), List::size).values().copy2List()
                    .sortBy(a -> a).join(","));
            assertEquals(Arrays.asList(1, 2, 3, 4), Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 4)).flat()
                    .sortBy(a -> a.intValue()));
            assertArrayEquals(Arrays.asList(1, 2, 3).toIntArray(), new int[]{1, 2, 3});
            assertArrayEquals(Arrays.asList(1f, 2f, 3f).toFloatArray(), new float[]{1f, 2f, 3f});
            assertArrayEquals(Arrays.asList(1d, 2d, 3d).toDoubleArray(), new double[]{1d, 2d, 3d});
            assertArrayEquals(Arrays.asList(true, false, true).toBooleanArray(), new boolean[]{true, false, true});
            assertArrayEquals(Arrays.asList(1L, 2L, 3L).toLongArray(), new long[]{1L, 2L, 3L});
            assertArrayEquals(Arrays.asList('1', '2', '3').toCharArray(), new char[]{'1', '2', '3'});
            assertArrayEquals(Arrays.asList((byte) 1, (byte) 2, (byte) 3)
                    .toByteArray(), new byte[]{(byte) 1, (byte) 2, (byte) 3});
            assertArrayEquals(Arrays.asList((short) 1, (short) 2, (short) 3)
                    .toShortArray(), new short[]{(short) 1, (short) 2, (short) 3});
            assertEquals("2,2,2", Arrays.asList("test1", "test2", "test3", "test1", "test2", "test3")
                    .groupBy(a -> a.regex("\\d").head()).map2List((a, b) -> b.size())
                    .join(","));
            assertEquals("2,3,4", Arrays.asList("test1", "test2", "test3", "test1", "test2", "test3")
                    .groupBy(a -> a.regex("\\d").head()).map((a, b) -> a.toInt() + 1).values()
                    .join(","));

            assertEquals(new int[]{1, 2, 3, 4, 5, 6}.sum(), 21);
            assertEquals(new int[]{1, 2, 3, 4, 5, 6}.wrap().sum(), 21);
            assertEquals(new int[]{1, 2, 3, 4, 5, 6}.wrap().map(BigDecimal::new).sum(), BigDecimal.valueOf(21));
            assertEquals(new double[]{1.0, 2.1, 3.2, 4.3, 5.4, 6.5}.sum(), 22.5);
            assertEquals(new double[]{1.0, 2.1, 3.2, 4.3, 5.4, 6.5}.wrap().sum(), 22.5);
            assertEquals(new double[]{1.0, 2.1, 3.2, 4.3, 5.4, 6.5}.wrap().map(BigDecimal::new).map(a -> a.setScale(2, 4)).sum(), BigDecimal.valueOf("22.50"));
            assertEquals(new double[]{1.0, 2.1, 3.2, 4.3, 5.4, 6.5}.wrap().map(String::valueOf).map(BigDecimal::new).sum(), BigDecimal.valueOf(22.5));
            assertEquals(new int[]{1, 2, 3, 4, 5, 6}.wrap().reduce(0, (a, b) -> a + b), 21);
            assertEquals(new int[]{1, 2, 3, 4, 5, 6}.wrap().map(BigDecimal::new).reduce(BigDecimal.ZERO, (a, b) -> a.add(b)), BigDecimal.valueOf(21));
            assertArrayEquals(new String[]{"1", "2", "3", "4", "5", "6"}.mapToInt(String::toInt), new int[]{1, 2, 3, 4, 5, 6});
            assertArrayEquals(new String[]{"1", "2", "3", "4", "5", "6"}.map(new Integer[0], String::toInt), new int[]{1, 2, 3, 4, 5, 6}.wrap().toArray(Integer.class));
            assertArrayEquals(new String[]{"1", "2", "3", "4", "5", "6"}.map(Integer.class, String::toInt), new int[]{1, 2, 3, 4, 5, 6}.wrap2Array());
            assertArrayEquals(new String[]{"1", "2", "3", "4", "5", "6"}.map(String::toInt), new int[]{1, 2, 3, 4, 5, 6}.wrap2Array());
            assertTrue(strings.filter(String.class).noEmpty());
            assertTrue(new ArrayList<FatherObj>().addVarargs(new ChildChildObj())
                    .filter(ChildObj.class).noEmpty());


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
            assertEquals("123", nullStr.orElse("123"));
            assertEquals("123", "".orElse("123"));
            assertEquals("123", "123".orElse(""));
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
        String nullStr = Math.random() > 0.5 ? "1" : Math.random() > 0.5 ? "" : null;
        {
            //例1：解析字符串"11,12,13,14,15"，找到其中的全部偶数
            final String str = "11,12,13,14,15";
            //=================使用for循环=========================
            List<String> list = new ArrayList<>();
            for (String s : str.split(",")) {
                if (Integer.parseInt(s) % 2 == 0) list.add(s);
            }
            final String[] v0 = list.toArray(new String[0]);
            //=================使用java的Stream筛选=================
            final String[] v1 = Arrays.stream(str.split(",")).filter(a -> Integer.parseInt(a) % 2 == 0).toArray(String[]::new);
            //=================使用zircon筛选=======================
            final String[] v3 = str.split(",").findAll(a -> a.toInt() % 2 == 0);
        }
        {
            //例2：字符串判空
            if (nullStr == null || nullStr.isEmpty()) {
                //...
            }
            if (nullStr.nullOrEmpty()) {
                //...
            }
        }


        //例1
        "java,Zircon,:)".split(",").find(a -> a.startsWith("Z"))             //为字符串数组增加find方法
                .with(System.out::println);//print: Zircon //为Object增加流式处理函数，避免额外定义变量

        //例2
        final int num = "num is 1".regex("\\d")   //为String增加快速正则匹配方法
                .head()         //为集合增加选取首个值的方法
                .orElse("123")  //空安全方法，如果调用对象为null，不会空指针异常而是使用参数值（举例用，这里一定不为null可省略）
                .toInteger()       //为String增加快速转化int的方法
                .convert(i -> Math.abs(i + 1)); //为Object增加的流式转化函数，避免先定义i再修改导致无法修改final变量
        new Thread(() -> System.out.println(num)).start();// print 2 //lambda表达式中引用的方法变量必须为final。
    }

    @Test
    public void expectedCompileFail() {
//        ArrayList<ArrayList<String>> list = new ArrayList<>();
//        List<String> flat2 = list.flatTest();
//        List<String> flat3 = new ArrayList<>().flat();
//        final TestM<TData> getA = (TData::getA);
    }

    public <M> M self(M t) {
        return t;
    }


    static interface TestM<T> extends Serializable {
        Object get(T source) throws Exception;
    }

    static class TData {
        String a = "123";

        public String getA() {
            return a;
        }
    }

}

