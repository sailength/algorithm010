### JavaDeque

```java public class DequeTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        Deque<String> deque = new LinkedList<String>();
        deque.add("d");
        deque.add("e");
        deque.add("f");
        
        //从队首取出元素，不会删除
        System.out.println("队首取出元素:"+deque.peek());
        System.out.println("队列为:"+deque);
        
        //从队首加入元素(队列有容量限制时用，无则用addFirst)
        deque.offerFirst("c");
        System.out.println("队首加入元素后为："+deque);
        //从队尾加入元素(队列有容量限制时用，无则用addLast)
        deque.offerLast("g");
        System.out.println("队尾加入元素后为："+deque);
        
        //队尾加入元素
        deque.offer("h");
        System.out.println("队尾加入元素后为："+deque);
        
        //获取并移除队列第一个元素,pollFirst()也是，区别在于队列为空时,removeFirst会抛出NoSuchElementException异常，后者返回null
        deque.removeFirst();
        System.out.println("获取并移除队列第一个元素后为:"+deque);
        
        //获取并移除队列第一个元素,此方法与pollLast 唯一区别在于队列为空时,removeLast会抛出NoSuchElementException异常，后者返回null
        deque.removeLast();
        System.out.println("获取并移除队列最后一个元素后为:"+deque);
        
        //获取队列第一个元素.此方法与 peekFirst 唯一的不同在于：如果此双端队列为空，它将抛出NoSuchElementException，后者返回null
        System.out.println("获取队列第一个元素为:"+deque.getFirst());
        System.out.println("获取队列第一个元素后为:"+deque);
        
        //获取队列最后一个元素.此方法与 peekLast 唯一的不同在于：如果此双端队列为空，它将抛出NoSuchElementException，后者返回null
        System.out.println("获取队列最后一个元素为:"+deque.getLast());
        System.out.println("获取队列第一个元素后为:"+deque);
        
        //循环获取元素并在队列移除元素
        while(deque.size()>0){
            System.out.println("获取元素为："+ deque.pop()+" 并删除");
        }
        System.out.println("队列为:"+deque);
    }

}
```

### Java Queue源码理解
#### FIFO数据结构,主要提供如下几个方法
- add(E e);添加元素
- offer(E e);添加元素
- E remove();移除元素
- E poll();移除元素
- E element();查看头部元素
- E peek();查看头部元素

#### 明细
- 1、add()和offer()区别:
add()和offer()都是向队列中添加一个元素。一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，调用 add() 方法就会抛出一个 unchecked 异常，而调用 offer() 方法会返回 false。因此就可以在程序中进行有效的判断！
- 2、poll()和remove()区别：
remove() 和 poll() 方法都是从队列中删除第一个元素。如果队列元素为空，调用remove() 的行为与 Collection 接口的版本相似会抛出异常，但是新的 poll() 方法在用空集合调用时只是返回 null。因此新的方法更适合容易出现异常条件的情况。
- 3、element() 和 peek() 区别：
element() 和 peek() 用于在队列的头部查询元素。与 remove() 方法类似，在队列为空时， element() 抛出一个异常，而 peek() 返回 null。



### java priorityQueue理解
- peek()//返回队首元素
- poll()//返回队首元素，队首元素出队列
- add()//添加元素
- size()//返回队列元素个数
- isEmpty()//判断队列是否为空，为空返回true,不空返回false
#### 明细
- 1.需要实现比较器实现优先级比较
- 2.通过优先级，保证队列基于优先级是有序
- 3.队列插入和删除基于此特性会有所牺牲。插入和删除的时间复杂度是O(log)。其余和和queue一样。
- 4.底层实现是通过完全二叉树实现的小顶堆。




### 总结：
- 1.视频内容有点长，内容完全掌握可能需要10个小时左右，自己可以适当采取合适的适合自己的方法学习。
- 2.对于算法的一些内容，还是要在课后花时间去消化，不是一遍两遍就能理解的
- 3.关于算法的3sum和栈算圆柱的体积，这样的做法，还是需要去看一下证明，为什么这样写是合理的。不是说只要背算法，强迫症使我想要知道为什么这样做是可以求解的不会漏掉可能的解。
- 4.最后，这块，比想象中的花时间要多。还是要多花点时间把。