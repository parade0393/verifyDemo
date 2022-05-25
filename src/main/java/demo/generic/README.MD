### 方法的签名

方法的签名包括方法名称和参数列表(方法的参数顺序和类型)组成

### 泛型

1. 泛型就是定义一种模板，由编译器针对类型做检查--针对应用的类型检查
2. 泛型的出现就是为了解决类型转换的问题:比如泛型类型<String>那么get的时候肯定是String,不用强转

### 使用泛型类型

1. 使用ArrayList时，如果不定义泛型类型时，泛型类型实际就是Object


   ```
   List list = new ArrayList();//虽然可以这样写，但是编译器会警告
   list.add(1);//因为泛型类型是Object，这样就失去了意义
   list.add("");
   //当我们定义泛型类型number后，就变成了强类型
   List<Number> list1 = new ArrayList<Number>()
   ```

2. 静态方法不能使用泛型类的泛型

   ```
   public class Pair<T> {
       private T first;
   
       public Pair(T first) {
           this.first = first;
       }
   
       public T getFirst() {
           return first;
       }
       //对于静态方法，我们可以改为静态放啊
       public static <K> Pair<K> create(K first){//静态方法的T和类的T其实已经没有了关系
           return new Pair<K>(first);
       }
   }
   ```

### PECS

1. 请记住PECS原则：生产者（Producer）使用extends，消费者（Consumer）使用super。

2. <? extends> extends 表明了类型的上界,表示参数化的类型可能是所指定的类型或者指定类型的子类

```java
public class TestJava {
 public static void main(String[] args) {
     /**
      * List<? extends Number>  是泛型，其真正的类型可能是ArrayList<Integer>、new ArrayList<Float>或者new ArrayList<Long>或者其他Number的子类
      * 总之其实际类型只有可能是上面实际类型中的一种，所以无法add，因为添加的类型不确定，但是可以get因为取到的类型肯定是Number类型
      */
     List<? extends Number> list = new ArrayList<Integer>();
//        List<? extends Number> list = new ArrayList<Float>();
//        List<? extends Number> list = new ArrayList<Long>();
 }
}
```

   List<? extends T> 是说 这个list放的是T或者T的子类型的对象，但是不能确定具体是什么类型，所以可以get（），不能add（）（可以add null值）
   编译时擦除到类型T，即用T类型代替类型参数

3. <? super> super 表面了类型的下届，表示参数化的类型可能是指定的类型或者其父类----是说这个list放的是至少是T类型的对象，所以我可以add T或者T的子类型，但是get得到的类型不确定，所以不能get

```java
public class TestJava {
    public static void main(String[] args) {
        /**
         *  表面了类型的下届，表示参数化的类型可能是指定的类型或者其父类
         * List<? super Number> 是泛型 其参数化类型可能是new ArrayList<Number>或者new ArrayList<Object>()
         * 总之实际类型只可能是上面中的一种，根据多态规则，所以add Number或者Number的子类型的数据肯定没问题，但是get就不确定是什么类型
         */
//        List<? super Number> list = new ArrayList<Number>();
        List<? super Object> list = new ArrayList<Object>();
    }
}
```

4. 生产者：如果需要提供一个容器提供T类型的元素(即从容器中读取T类型的元素)，需要声明容器的泛型<? extends T>--你获取的类型肯定是T类型

5. 消费者:如果一个容器需要使用T类型的元素(即你想把T类型的元素加到容器中)，需要声明容器的泛型<? super T>