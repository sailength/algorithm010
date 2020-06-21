## 理论
####hashmap我认为是对于散列表的工程性实现，散列表结构如下

![image-20200621231025363](/Users/sailength-work/Library/Application Support/typora-user-images/image-20200621231025363.png)

通过这个例子，我们可以总结出这样的规律：散列表用的就是数组支持按照下标随机访问的时候，时间复杂度是 O(1) 的特性。我们通过散列函数把元素的键值映射为下标，然后将数据存储在数组中对应下标的位置。当我们按照键值查询元素时，我们用同样的散列函数，将键值转化数组下标，从对应的数组下标的位置取数据。

  由于散列函数，有时候会出现hash出现相同值的情况，然后就会出现散列冲突（开放寻址和链表法），hashmap使用了链表法，方法如下![image-20200621231514111](/Users/sailength-work/Library/Application Support/typora-user-images/image-20200621231514111.png)

主要是通过hash函数，计算bucket，然后放入slot中。

以上，就是从概念层面对于hashmap的理解

但是其中会有几个问题：

- 1.如何设计相对好的散列函数？

- 2.随着元素增多，bucket是有限的，如何防止散列冲突导致的性能下降？

- 3.上个问题的延伸，随着元素的增多，必然会扩容bucket，如何实现高效扩容和阈值判断呢？





##带着上面的三个问题，看下java的hashmap代码

#### 1、我们先看下hashmap的散列函数

```java
/**
 * Computes key.hashCode() and spreads (XORs) higher bits of hash
 * to lower.  Because the table uses power-of-two masking, sets of
 * hashes that vary only in bits above the current mask will
 * always collide. (Among known examples are sets of Float keys
 * holding consecutive whole numbers in small tables.)  So we
 * apply a transform that spreads the impact of higher bits
 * downward. There is a tradeoff between speed, utility, and
 * quality of bit-spreading. Because many common sets of hashes
 * are already reasonably distributed (so don't benefit from
 * spreading), and because we use trees to handle large sets of
 * collisions in bins, we just XOR some shifted bits in the
 * cheapest possible way to reduce systematic lossage, as well as
 * to incorporate impact of the highest bits that would otherwise
 * never be used in index calculations because of table bounds.
 */
static final int hash(Object key) {
    int h;
    return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
```

首先取key的hashCode然后和hashcode右移16位（高位用0补全）然后进行异或

#### 2、put函数





```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
               boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    //首先把table赋值给tab 检查是不是null 如果是null 那么初始化一下(resize)
    //饭后返回table的长度给n
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    //(n-1)&hash作为桶的标号
    //我们知道桶的数量是 2^n
    //2^n - 1 在二进制中表示就是全是1 按位与就比较平均的分配桶了
    //但是为什么不直接用hashcode作为hash呢?
    //由于使用桶数量-1作为掩码，那么注定高位的hashcode无法参与运算
    //我们把高16位与低16使用xor运算，让高位参与hash，减少一定的冲突
    //但是如果 桶的数量不是 2^n 那么这么做hash就不那么平均了
    //在这里判断是不是有节点，如果没有，那么证明这里没有Hash冲突直接创建一个节点放这里就好了
    if ((p = tab[i = (n - 1) & hash]) == null){
        //在这里创建一个节点赋值给第i个桶
        tab[i] = newNode(hash, key, value, null);        
    }
    else {
        //这里证明有Hash冲突了
        Node<K,V> e; K k;
        //判断当前的hash是不是相等，然后判断key相等
        //因为key是个对象，相对来讲equals运算时间更长，先比较hash，如果不相等就不用equals比较了
        //如果是相同的键，那么赋值给一个临时引用，先不覆盖
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        //这里判断当前桶里的头结点是不是TreeNode，如果是TreeNode，那就把这个节点插到红黑树里面
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            //如果不是Tree节点，那么说明现在还是个链表
            //开始遍历
            for (int binCount = 0; ; ++binCount) {
                //判断是不是尾节点
                if ((e = p.next) == null) {
                    //是尾节点那就直接插后面
                    p.next = newNode(hash, key, value, null);
                    //判断链表多长
                    //如果超过阈值，那就把链表变成二叉树
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                        break;
                    }
                //hash相等之后在进行equals判断如果相等，证明这个已经key已经存在了break
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        //e是key所在的节点如果是null那么证明是新插的，否则覆盖引用
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                //如果旧值是null或onlyIfAbsent==0那么覆盖值然后直接返回就可以了
                //因为并没有改变table结构或者链表什么的
                e.value = value;
            //这个是空的，需要子类覆盖一下
           	//但是绝不能改变table结构什么的
                afterNodeAccess(e);
                return oldValue;
            }
        }
    //更改迭代器
        ++modCount;
    //大于阈值 扩容
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
}
```





#### 3.扩容

```java
/**
     * Initializes or doubles table size.  If null, allocates in
     * accord with initial capacity target held in field threshold.
     * Otherwise, because we are using power-of-two expansion, the
     * elements from each bin must either stay at same index, or move
     * with a power of two offset in the new table.
     *
     * @return the table
     */
    final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        //如果是null那么说明是新创建的
        //下面的几个变量，Cap桶容量，Thr阈值
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;
        int newCap, newThr = 0;
        //先判断旧桶有没有
        if (oldCap > 0) {
            //如果旧的长度大于最大阈值，那么把桶扩大到最大Integer.MAX，然后不会发生resize了
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            //桶容量*2如果比最大容量小以及旧的大于默认桶容量
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                     oldCap >= DEFAULT_INITIAL_CAPACITY)
                newThr = oldThr << 1; // double threshold
        }
        //旧的没桶数量 但是有阈值 说明第一次创建
        else if (oldThr > 0) // initial capacity was placed in threshold
            newCap = oldThr;
        else {               // zero initial threshold signifies using defaults
            //没有阈值，没有旧桶容量，说明默认初始化的
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        if (newThr == 0) {
            //新阈值 为0 说明需要计算阈值
            float ft = (float)newCap * loadFactor;
            //cap*factor但是如果太大，那么就扩大到Integer.MAX_VALUE
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                      (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings({"rawtypes","unchecked"})
        //按照计算好的桶的数量扩容
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            //把旧的桶所有东西放在新桶里面
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    //先清空旧的桶那个位置
                    oldTab[j] = null;
                    //如果只有一个元素 直接放在新桶对应的位置
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                   	//如果是树节点
                    else if (e instanceof TreeNode)
                        //对应的放在新桶里面
                        ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                    else { // preserve order
                        //链表节点 遍历，然后对应的放新桶里面
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        //开始遍历链表
                        do {
                            next = e.next;
                            //这里挺有意思的
                            //判断 oldCap那个位置是0还是1
                            //如果是0 那么扩容后不变
                            //如果是1 那么扩容后桶是原来位置+原来桶的数量
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        //对应的链表放进去就行
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }
```

## 综上可以解答以上几个问题了

#### 1.hashmap采取了取模的方式进行hash

#### 2.如果单个entry的slot链表过长，超过阈值，默认是8，会进行树化操作，转成红黑树，使查询变为O(n)减少性能退化

#### 3.负载因子（非空闲的bucket/总bucket）大于0.75会进行扩容，从而减少hash冲突

#### 4.hashmap有几个问题

- a.多线程链表环导致cpu飙升

- b.非线程安全

- c.如果扩容会导致，单次的插入的性能升高，对于性能要求较高的系统是无法接受的=>redis对于hashmap有此类的解决、