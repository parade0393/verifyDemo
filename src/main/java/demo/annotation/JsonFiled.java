package demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解的声明周期：定义在RetentionPolicy枚举中，有3种策略
 *  1. RetentionPolicy.RUNTIME-> 运行时有效最常用的一种策略，结合反射使用
 *  2. RetentionPolicy.SOURCE->  在源文件中有效，被编译器丢弃
 *  3. RetentionPolicy.CLASS-> 在编译器生成的字节码文件中有效，但在运行时会被处理类文件的JVM丢弃
 * 注解装饰的目标：定义在ElementType枚举中，主要的策略有ElementType.FIELD(成员变量)，ElementType.TYPE(类,接口,注解,枚举)<br/>
 *              ,ElementType.METHOD(方法)，ElementType.PARAMETER(参数),Element.ANNOTATION_TYPE(注解)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
public @interface JsonFiled {
    //这里使用参数名value是有原因的，它可以允许使用者提供一个无需指定名字的参数：例如@JsonFiled(value = "parade岁月")也可以@JsonFiled("parade岁月")
    //使用 default "" 允许使用者直接使用注解，而不组要指定参数名称和参数值。例如在属性上直接写@JsonFiled
    public String value() default "";
}
