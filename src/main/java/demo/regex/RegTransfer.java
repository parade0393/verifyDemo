package demo.regex;

/**
 * 转义理解
 * Java中\是转义字符，作用是转义后面一个字符(只转义后面第一个字符)，把它当作普通字符
 * ”“包裹起来表示字符串 "\"表示一个"和一个普通的"，所以这样写是会报错的
 * \\\\ 会输出\\ 可以这样理解 第一个\把第二个\转义了，两个最终输出一个普通的\ 第3个\把第四个\转义了，最终输出一个普通的\ 整体最后输出两个普通的\\
 * \\r\\ 第一个\把第二个\转义 第3个\把第4个\转义 最终输出字符串 \r\
 *
 * 正则当中\也是转义字符 作用后边的一个字符，整体变得特殊 例如 d在正则里就是普通的d \d在正则里就代表数字
 * 所以正则匹配时要写\\d 因为\\d最终代表\d，在正则里才代表数字
 */
public class RegTransfer {
    public static void main(String[] args) {
        System.out.println("\\");// \
        System.out.println("\\\\");// \\
        System.out.println("\\r\\");// \r\
        System.out.println("234".matches("\\d"));

    }
}
