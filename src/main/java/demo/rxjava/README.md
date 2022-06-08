## 创建操作符
创建操作符包括:`create,just,fromArray,fromIterable`,`defer,timer,interval,intervalRange,range,rangeLong`,`never,empty,error`
从操作符的名称大概能明白其具体的功能含义
创建即发送:`create,just,fromArray,fromIterable`
延迟发送:`defer,timer,interval,intervalRange,range,rangeLong`
## 变换操作符
变化操作符包括:`map,flatMap,contactMap,switchMap`

map:只能转换原始事件序列中事件的数据类型

flatMap、contactMap、switchMap都是改变原始事件序列，把原始事件序列中的每个事件都转换成一个新的事件序列(Observable)，然后再合并成一个新的事件序列
异同点:
1. 在同一线程中:三个的效果一样
2. 不同线程中：flatMap是无序的，contactMap是有序的，switchMap的观察者只会接收到最新的数据
## 组合操作符
组合多个被观察者或者合并需要发送的事件

组合操作符包括
1. `contact,merge,contactArray,mergeArray,contactDelayError,mergeDelayError,concatArrayDelayError,mergeArrayDelayError`
2. `zip,combineLatest,combineLatestDelayError`
3. `reduce,collect`
4. `startWith,startWithArray`
5. `count`

### 辅助操作符
可分为线程调度，错误处理，重复处理等
1. `delay,delaySubscription` 
2. `onErrorReturn,onErrorReturnItem`
3. `ErrorResumeNext,ErrorResumeWith`
4. `retry系列 retryWhen,retryUtil`
5. `do系列`
6. `repeat系列`


