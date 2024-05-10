package org.algorithm.search;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h3>wsd-project</h3>
 * <p>kv 关联映射</p>
 *
 * @author : 王松迪
 * 2024-04-29 15:12
 **/
public class KVBMatcher {

    /**
     * 来源对应的行为映射，key
     */
    private final Map<String, BehaviourMap[]> sourceKv;

    private final Map<String, BehaviourSet[]> sourceKvSet;

    /**
     * 是否使用 set，用来做性能测试
     */
    private final boolean isSet;

    public static class Behaviour {

        /**
         * 行为标识
         */
        public String sign;

        /**
         * 行为的 kv 映射
         */
        public Map<String, List<String>> kvMaps;

    }


    /**
     *
     * @param sourceBehaviourMap key 为来源
     */
    public KVBMatcher(Map<String, Behaviour[]> sourceBehaviourMap, boolean isSet) {
        this.isSet = isSet;
        if(isSet) {

            HashMap<String, BehaviourSet[]> tempSet = new HashMap<>(sourceBehaviourMap.size());
            for (Map.Entry<String, Behaviour[]> entry : sourceBehaviourMap.entrySet()) {
                String source = entry.getKey();
                Behaviour[] behaviours = entry.getValue();
                BehaviourSet[] behaviourSets = new BehaviourSet[behaviours.length];
                for (int i = 0; i < behaviours.length; i++) {
                    Behaviour behaviour = behaviours[i];
                    String sign = behaviour.sign;
                    Map<String, List<String>> kvMaps = behaviour.kvMaps;
                    BehaviourSet behaviourSet = new BehaviourSet(sign, kvMaps);
                    behaviourSets[i] = behaviourSet;
                }
                tempSet.put(source, behaviourSets);
            }
            sourceKvSet = tempSet;

        } else {
            sourceKvSet = Collections.emptyMap();
        }

        HashMap<String, BehaviourMap[]> temp = new HashMap<>(sourceBehaviourMap.size());
        for (Map.Entry<String, Behaviour[]> entry : sourceBehaviourMap.entrySet()) {
            String source = entry.getKey();
            Behaviour[] behaviours = entry.getValue();
            BehaviourMap[] behaviourMaps = new BehaviourMap[behaviours.length];
            for (int i = 0; i < behaviours.length; i++) {
                Behaviour behaviour = behaviours[i];
                String sign = behaviour.sign;
                Map<String, List<String>> kvMaps = behaviour.kvMaps;
                BehaviourMap behaviourMap = new BehaviourMap(sign, kvMaps);
                behaviourMaps[i] = behaviourMap;
            }
            temp.put(source, behaviourMaps);
        }

        sourceKv = temp;
    }

    public String match(String source, Pair<String, String>[] kvPairs) {
        if(isSet) {

            if(!sourceKvSet.containsKey(source)) {
                return null;
            }

            BehaviourSet temp = null;
            Iterator<BehaviourSet> iterator = Arrays.stream(sourceKvSet.get(source)).iterator();
            while (iterator.hasNext()) {
                BehaviourSet next = iterator.next();
                if(next.matcher(kvPairs)){
                    temp = next;
                    break;
                }
            }

            return temp == null? null : temp.sign;
        } else {
            if(!sourceKv.containsKey(source)) {
                return null;
            }

            BehaviourMap temp = null;
            Iterator<BehaviourMap> iterator = Arrays.stream(sourceKv.get(source)).iterator();
            while (iterator.hasNext()) {
                BehaviourMap next = iterator.next();
                if(next.matcher(kvPairs)){
                    temp = next;
                    break;
                }
            }

            return temp == null? null : temp.sign;
        }
    }
}
class BehaviourSet{

    String sign;

    Map<String, Set<String>> kvMaps;

    public BehaviourSet(final String sign, final Map<String, List<String>> kvMaps) {
        this.sign = sign;
        this.kvMaps = kvMaps.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> new HashSet<>(e.getValue())));
    }

    public boolean matcher(Pair<String, String>[] kvPairs) {
        if (Objects.isNull(kvPairs) || kvPairs.length == 0 || !Stream.of(kvPairs).allMatch(kv -> kvMaps.containsKey(kv.getKey()))) {
            return false;
        }

        for (Pair<String, String> kvPair : kvPairs) {
            Set<String> valueSet = kvMaps.get(kvPair.getKey());
            if (valueSet.isEmpty() || !valueSet.contains(kvPair.getValue())) {
                return false;
            }
        }
        return true;
    }
}

class BehaviourMap {

    String sign;

    Map<String, ValueCompat> kvMaps;

    public BehaviourMap(final String sign, final Map<String, List<String>> kvMaps) {
        this.sign = sign;
        this.kvMaps = kvMaps.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> new ValueCompat(e.getValue().toArray(new String[0]))));

    }

    public boolean matcher(Pair<String, String>[] kvPairs) {
        if (Objects.isNull(kvPairs) || !Stream.of(kvPairs).allMatch(kv -> kvMaps.containsKey(kv.getKey()))) {
            return false;
        }

        boolean machResult = false;
        for (Pair<String, String> kvPair: kvPairs) {
            machResult = kvMaps.get(kvPair.getKey()).matcher(kvPair.getValue());
            if(!machResult){
                break;
            }
        }
        return machResult;
    }
}

class ValueCompat{

    int[] valueHashCodeIndex;

    ValueMatcher[] valueArray;

    public ValueCompat(final String[] inputValue) {
        //计算 value 的 hashcode，valueHashCodeIndex 按照 valueHashCode 值，升序排序，如果 hashCOde 重复则放在 FuzzyValue中，不冲突则放在 ExactValue中
        Map<Integer, ValueMatcher> temp = new HashMap<>(inputValue.length);

        //三种情况 1. 初始化，肯定没有冲突，过程中，可能会有冲突；

        for (String s : inputValue) {
            int valueHashCode = s.hashCode();
            if(temp.containsKey(valueHashCode)) {
                //冲突，放入 FuzzyValue 中 ,注意 原 ExtraValue 的内存释放问题，容易引起内存移除
                ValueMatcher valueMatcher = temp.get(valueHashCode);
                if(valueMatcher.matcherType() == ValueMatcher.MatcherType.EXACT) {
                    ExactValue exactValue = (ExactValue) valueMatcher;
                    FuzzyValue fuzzyValue = new FuzzyValue(new String[]{exactValue.value, s});
                    temp.put(valueHashCode, fuzzyValue);
                    //防止内存泄露问题
                    exactValue = null;
                } else{
                    FuzzyValue fuzzyValue = (FuzzyValue) valueMatcher;
                    fuzzyValue.valueArray = Arrays.copyOf(fuzzyValue.valueArray, fuzzyValue.valueArray.length + 1);
                    fuzzyValue.valueArray[fuzzyValue.valueArray.length - 1] = s;
                }
            } else {
                //没有冲突，放入 ExactValue 中
                temp.put(valueHashCode, new ExactValue(s));
            }
        }

        ArrayList<Integer> hashCodeSort = new ArrayList<>(temp.keySet());
        Collections.sort(hashCodeSort);
        valueHashCodeIndex = new int[hashCodeSort.size()];
        this.valueArray = new ValueMatcher[hashCodeSort.size()];
        for (int i = 0; i < hashCodeSort.size(); i++) {
            valueHashCodeIndex[i] = hashCodeSort.get(i);
            valueArray[i] = temp.get(hashCodeSort.get(i));
        }
    }

    /**
     * 二分查找
     * @param value value 的匹配
     * @return 匹配结果
     */
    public boolean matcher(final String value) {
        int low = 0;
        int high = valueHashCodeIndex.length - 1;
        while (low <= high) {
            int mid = ((high - low) >>> 1) + low;
            int valueHashCode = valueHashCodeIndex[mid];
            ValueMatcher valueMatcher = valueArray[mid];
            if (valueHashCode == value.hashCode()) {
                //精确匹配
                return valueMatcher.matcher(value);
            } else if (valueHashCode < value.hashCode()) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

}

class ExactValue implements ValueMatcher{

    final String value;

    public ExactValue(final String value) {
        this.value = value;
    }

    @Override
    public MatcherType matcherType() {
        return MatcherType.EXACT;
    }

    @Override
    public boolean matcher(String value) {
        return this.value.equals(value);
    }
}

class FuzzyValue implements ValueMatcher{

    String[] valueArray;

    public FuzzyValue(final String[] valueArray) {
        this.valueArray = valueArray;
    }

    @Override
    public MatcherType matcherType() {
        return MatcherType.FUZZY;
    }

    @Override
    public boolean matcher(final String value) {
        for (String v : valueArray) {
            if (v.equals(value)) {
                return true;
            }
        }
        return false;
    }
}

interface ValueMatcher {
    enum MatcherType {
        EXACT,
        FUZZY,
        ;
    }

    MatcherType matcherType();

    boolean matcher(String value);
}
