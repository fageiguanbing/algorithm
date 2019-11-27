/**
 * 方法一：构造新的对象存储数据，实现 Comparable 接口，优先队列在插入时即可保证顺序
 * 时间复杂度：O(n)
 * 执行时间：6ms
 */
class Solution {
    
    private PriorityQueue<Word> minHeap = new PriorityQueue<>();

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Word> hashMap = new HashMap<>();
        LinkedList<String> res = new LinkedList<>();
        for (String w : words) {
            if (hashMap.containsKey(w)) {
                Word word = hashMap.get(w);
                word.increCount();
            } else {
                Word word = new Word(w);
                hashMap.put(w, word);
            }
        }
        for (Word w : hashMap.values()) {
            minHeap.offer(w);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (res.size() < k) {
            res.addFirst(minHeap.poll().str);
        }
        return res;
    }

    class Word implements Comparable<Word> {
        public String str;
        private int count;

        public Word(String str) {
            this.str = str;
            this.count = 1;
        }

        public void increCount() {
            this.count += 1;
        }

        @Override
        public int compareTo(Word word) {
            int k = this.count - word.count;
            if (k == 0) {
                return -this.str.compareTo(word.str);
            }
            return k;
        }
    }
}

/**
 * 方法二：先把所有数据用 map 保存，然后再进行排序
 * 时间复杂度：O(nlogn)
 * 执行时间：9ms
 */
class Solution {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        List<String> candidates = new ArrayList(count.keySet());
        
        Collections.sort(candidates, (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
                w1.compareTo(w2) : count.get(w2) - count.get(w1));

        return candidates.subList(0, k);
    }
}
