package demo.regex;

public class CommonUseRegex {
    /**
     * 识别超链接
     * 1.这是一个超链接：https://www.example.com/地防护多少分 https://www.baidu.com 懂法守法 利用正则可识别两个超链接
     * 2."这事一个超链接http://pyapi.dongben.cc:9090/Html/oa/6447/5.2.3.61/cwb/index.html#/receive?govId=6447&token=e89593e7c1ef4facb364b7af08b98cee&mobile=13703937803&html_version=202108111145此功能的只有濮阳有"
     */
    public static final String WEB_URL = "((http[s]{0,1})://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9#\\&%_\\./-~-]*)?";

}
