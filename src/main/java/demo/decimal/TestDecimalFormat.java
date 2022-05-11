package demo.decimal;

import java.text.DecimalFormat;

/**
 * DecimalFormat  格式化通过占位符  最常用的两个占位符是0和# 它俩都代表数字
 *  0占位符：
 *  实际数字比0占位符多的时候，就去要切掉，但是只切小数部分，同时被切掉的小数位会进行四舍五入处理
 *  实际数字比0占位符少的时候，就需要补位，小数部分和整数部分都会补
 *  #占位符：
 *  实际数字比#占位多的时候要，就要切掉，但是只切小数部分，同时被切掉的小数位会进行四舍五入处理
 *  实际数字比占位符少的时候，不做任何处理，不会补0也不会补#
 *  小数部分 #占位不包括0，如果小数点后非0数字后还有0就切掉
 *
 *  % 占位符 原来的数字乘以100并显示为百分数
 *  3.数字（1-9）：上面的分析不是#就是0，如果是其他的数值会怎样呢？
 * 上面的扩展很详细的说明这个问题。
 *  整数：若没有0或#，格式化数字在前面；若有0或#，格式化数字在后面--0和#组合起来在加上格式化数字
 * 小数：若没有0或#，格式化是什么就显示什么；若有0或#，找出所有的0或#拼在一起，加上格式化数字
 *
 * 发现一个规律，整数部分0不能在#左边 小数部分#不能在0左边  应该遵守 #0.0#这样顺序
 * 参考连接@see <a href="https://www.jianshu.com/p/b3699d73142e">DecimalFormat的使用</a>
 * @see DecimalFormat
 */
public class TestDecimalFormat {
    public static void main(String[] args) {
        double d = 10.36345;
        System.out.println(new DecimalFormat("000.0").format(d));//010.4
        System.out.println(new DecimalFormat("###.000").format(d));//10.363
//        System.out.println(new DecimalFormat("00#.##").format(d));//010.36 会报错
        System.out.println(new DecimalFormat("#00.##").format(d));//10.36
        System.out.println(new DecimalFormat("000.##").format(d));//010.36
        System.out.println(new DecimalFormat("000000.##%").format(d));//001036.35%

        double pi = 12.34;
        System.out.println(new DecimalFormat("6").format(pi));//612
        System.out.println(new DecimalFormat("60").format(pi));//612
        System.out.println(new DecimalFormat("06").format(pi));//126
        System.out.println(new DecimalFormat("00600").format(pi));//00126
        System.out.println(new DecimalFormat("#####60000").format(pi));//00126
//小数
        System.out.println(new DecimalFormat(".6").format(pi));//12.6
        System.out.println(new DecimalFormat(".06").format(pi));//12.36
        System.out.println(new DecimalFormat(".60").format(pi));//12.36
        System.out.println(new DecimalFormat(".0600").format(pi));//12.3406
        System.out.println(new DecimalFormat(".6000").format(pi));//12.3406
        System.out.println(new DecimalFormat(".600000##").format(pi));//12.340006
//        System.out.println(new DecimalFormat(".#0600000##").format(pi));

        double dig = 4.001230000;
        System.out.println(new DecimalFormat("0.####").format(dig));
        System.out.println(new DecimalFormat("0.0000").format(dig));
    }
}
