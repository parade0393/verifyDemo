package demo.closure;

import demo.Clickable;

import java.util.ArrayList;

class TestJava {
    private void closure(Clickable clickable){
        clickable.click();
    }

    public  void main(ArrayList<String> args) {
        int count = 0;
        closure(()->{
//            count+=1;//count必须设置为final才能访问，修改count只能把count声明为成员变量
        });
    }
}
