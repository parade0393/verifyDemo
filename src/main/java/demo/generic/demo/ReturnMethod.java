package demo.generic.demo;

import java.util.ArrayList;
import java.util.List;

public class ReturnMethod<T> {
    /**
     * @return TypeVariable
     */
    public <T> T getT(T parameter){
        return parameter;
    }

    /**
     * 返回值是class
     */
    public void normalMethod(){
        System.out.println("12");
    }

    /**
     * 返回值是Class
     */
    public String getName(){
        return "parade0393";
    }

    /**
     * @return ParameterizedType,实参类型T是TypeVariable
     */
    public List<T> getListT(){
        return new ArrayList<>();
    }

    /**
     * @return ParameterizedType,实参类型? extends Number是WildcardType
     */
    public List<? extends Number> getNum(){
        return new ArrayList<Integer>();
    }

    /**
     * @return ParameterizedType 实参类型是Class
     */
    public List<String> getRe(){
        return new ArrayList<String>();
    }
}
