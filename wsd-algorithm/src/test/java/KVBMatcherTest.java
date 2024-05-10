import com.google.common.collect.Lists;
import javafx.util.Pair;
import org.algorithm.search.KVBMatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;

public class KVBMatcherTest {

    private KVBMatcher kvbMatcher;

    @BeforeEach
    public void setUp() {

        Map<String, KVBMatcher.Behaviour[]> sourceBehaviourMap = new HashMap<>();
        // Create sample behavior map for testing
        KVBMatcher.Behaviour behaviour1 = new KVBMatcher.Behaviour();
        behaviour1.sign = "behavior1";
        Map<String, List<String>> kvMap1 = new HashMap<>();

        //behaviour1，测试样本为 10000 * 1000个；
        kvMap1.put("ab", Lists.newArrayList("Aa", "BB"));
        List<String> collect = new Random().ints(0, 10000).distinct().limit(10000).mapToObj(b -> "key" + b).collect(Collectors.toList());
        for (String key : collect) {
            kvMap1.put(key, new Random().ints(0, 1000).distinct().limit(1000).mapToObj(a -> "value_" + a).collect(Collectors.toList()));
        }
        behaviour1.kvMaps = kvMap1;

        KVBMatcher.Behaviour behaviour2 = new KVBMatcher.Behaviour();
        behaviour2.sign = "behavior2";
        Map<String, List<String>> kvMap2 = new HashMap<>();
        kvMap2.put("key1", new Random().ints(0, 100).distinct().limit(100).mapToObj(a -> "value_key1_" + a).collect(Collectors.toList()));
        kvMap2.put("key2", new Random().ints(0, 100).distinct().limit(100).mapToObj(a -> "value_key2_" + a).collect(Collectors.toList()));
        kvMap2.put("key3", new Random().ints(0, 100).distinct().limit(100).mapToObj(a -> "value_key3_" + a).collect(Collectors.toList()));
        kvMap2.put("key4", new Random().ints(0, 100).distinct().limit(100).mapToObj(a -> "value_key4_" + a).collect(Collectors.toList()));
        kvMap2.put("key5", new Random().ints(0, 100).distinct().limit(100).mapToObj(a -> "value_key5_" + a).collect(Collectors.toList()));;
        behaviour2.kvMaps = kvMap2;

        sourceBehaviourMap.put("source1", new KVBMatcher.Behaviour[]{behaviour1, behaviour2});

        kvbMatcher = new KVBMatcher(sourceBehaviourMap, false);
    }

    @Test
    public void testMatch_HappyPath() {
        String source = "source1";
        Pair<String, String>[] kvPairs = new Random().ints(0, 99).distinct().limit(80).mapToObj(a -> new Pair<>("key" + a, "value_" + a)).toArray(Pair[]::new);
        long startTime = System.currentTimeMillis();
        String result = kvbMatcher.match(source, kvPairs);
        System.out.println("testMatch_HappyPath Time taken: " + (System.currentTimeMillis() - startTime) + " ms");
        assertEquals("behavior1", result);
    }

    @Test
    public void testFuzzyMatch_HappyPath() {
        String source = "source1";
        Pair<String, String>[] kvPairs = new Pair[]{new Pair<>("ab", "Aa")};
        long startTime = System.currentTimeMillis();
        String result = kvbMatcher.match(source, kvPairs);
        long endTime = System.currentTimeMillis();
        System.out.println("testFuzzyMatch_HappyPath Time taken: " + (endTime - startTime) + " ms");
        assertEquals("behavior1", result);
    }

    @Test
    public void testMatch_SourceNotFound() {
        String source = "source2";
        Pair<String, String>[] kvPairs = new Pair[]{new Pair<>("key1", "value1")};
        long startTime = System.currentTimeMillis();
        String result = kvbMatcher.match(source, kvPairs);
        long endTime = System.currentTimeMillis();
        System.out.println("testMatch_SourceNotFound Time taken: " + (endTime - startTime) + " ms");
        assertNull(result);
    }

    @Test
    public void testMatch_NoMatchingBehavior() {
        String source = "source1";
        long startTime = System.currentTimeMillis();
        Pair<String, String>[] kvPairs = new Pair[]{new Pair<>("key2", "value_key2_101")};
        String result = kvbMatcher.match(source, kvPairs);
        long endTime = System.currentTimeMillis();
        System.out.println("testMatch_NoMatchingBehavior Time taken: " + (endTime - startTime) + " ms");
        assertNull(result);
    }

    @Test
    public void testMatch_EmptyKVPairs() {
        String source = "source1";
        Pair<String, String>[] kvPairs = new Pair[0];
        long startTime = System.currentTimeMillis();
        String result = kvbMatcher.match(source, kvPairs);
        long endTime = System.currentTimeMillis();
        System.out.println("testMatch_EmptyKVPairs Time taken: " + (endTime - startTime) + " ms");
        assertNull(result);
    }

    @Test
    public void testMatch_NullKVPairs() {
        String source = "source1";
        long startTime = System.currentTimeMillis();
        String result = kvbMatcher.match(source, null);
        long endTime = System.currentTimeMillis();
        System.out.println("testMatch_NullKVPairs Time taken: " + (endTime - startTime) + " ms");
        assertNull(result);
    }





}
