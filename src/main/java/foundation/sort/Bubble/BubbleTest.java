package foundation.sort.Bubble;

/**
 * 冒泡排序
 * 第一轮---从第一个开始：第二个和第一个比，第三个和第二个比，第四个和第三个比.......
 * 第二轮---从第二个开始：第三个和第二个比.........
 * .......
 * 一共时length-1轮
 */
public class BubbleTest {
    public static void main(String[] args) {
        int[] arr = new int[]{43, 72, 76, -98, 0, 64, 33, -21, 32, 99};//10个
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1;j<arr.length;j++){
                //升序
                /*if (arr[j]<arr[i]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }*/
                //降序
                if (arr[j]>arr[i]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
