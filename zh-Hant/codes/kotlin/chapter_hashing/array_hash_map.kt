/**
 * File: array_hash_map.kt
 * Created Time: 2024-01-25
 * Author: curtishd (1023632660@qq.com)
 */

package chapter_hashing

/* 鍵值對 */
class Pair(
    var key: Int,
    var value: String
)

/* 基於陣列實現的雜湊表 */
class ArrayHashMap {
    private val buckets = arrayOfNulls<Pair>(100)

    /* 構造方法 */
    init {
        // 初始化陣列，包含 100 個桶
        for (i in 0..<100) {
            buckets[i] = null
        }
    }

    /* 雜湊函式 */
    fun hashFunc(key: Int): Int {
        val index = key % 100
        return index
    }

    /* 查詢操作 */
    fun get(key: Int): String? {
        val index = hashFunc(key)
        val pair = buckets[index] ?: return null
        return pair.value
    }

    /* 新增操作 */
    fun put(key: Int, value: String) {
        val pair = Pair(key, value)
        val index = hashFunc(key)
        buckets[index] = pair
    }

    /* 刪除操作 */
    fun remove(key: Int) {
        val index = hashFunc(key)
        // 置為 null ，代表刪除
        buckets[index] = null
    }

    /* 獲取所有鍵值對 */
    fun pairSet(): MutableList<Pair> {
        val pairSet = ArrayList<Pair>()
        for (pair in buckets) {
            if (pair != null) pairSet.add(pair)
        }
        return pairSet
    }

    /* 獲取所有鍵 */
    fun keySet(): MutableList<Int> {
        val keySet = ArrayList<Int>()
        for (pair in buckets) {
            if (pair != null) keySet.add(pair.key)
        }
        return keySet
    }

    /* 獲取所有值 */
    fun valueSet(): MutableList<String> {
        val valueSet = ArrayList<String>()
        for (pair in buckets) {
            pair?.let { valueSet.add(it.value) }
        }
        return valueSet
    }

    /* 列印雜湊表 */
    fun print() {
        for (kv in pairSet()) {
            val key = kv.key
            val value = kv.value
            println("${key}->${value}")
        }
    }
}

/* Driver Code */
fun main() {
    /* 初始化雜湊表 */
    val map = ArrayHashMap()

    /* 新增操作 */
    // 在雜湊表中新增鍵值對 (key, value)
    map.put(12836, "小哈")
    map.put(15937, "小囉")
    map.put(16750, "小算")
    map.put(13276, "小法")
    map.put(10583, "小鴨")
    println("\n新增完成後，雜湊表為\nKey -> Value")
    map.print()

    /* 查詢操作 */
    // 向雜湊表中輸入鍵 key ，得到值 value
    val name: String? = map.get(15937)
    println("\n輸入學號 15937 ，查詢到姓名 $name")

    /* 刪除操作 */
    // 在雜湊表中刪除鍵值對 (key, value)
    map.remove(10583)
    println("\n刪除 10583 後，雜湊表為\nKey -> Value")
    map.print()

    /* 走訪雜湊表 */
    println("\n走訪鍵值對 Key->Value")
    for (kv in map.pairSet()) {
        println("${kv.key} -> ${kv.value}")
    }
    println("\n單獨走訪鍵 Key")
    for (key in map.keySet()) {
        println(key)
    }
    println("\n單獨走訪值 Value")
    for (value in map.valueSet()) {
        println(value)
    }
}
