package org.example.devCommand;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FullStackDev fullStackDev = new FullStackDev();
        BackendDev backendDev = new BackendDev();
        Developer dev =  new FrontendDev();

        List<Developer> devList = Arrays.asList(fullStackDev,backendDev,dev);

        devList.forEach(d -> {
            if (d instanceof FrontendDev){
                ((FrontendDev) d).screenFormProg();
            }
        });




//        fullStackDev.screenFormProg();
//        fullStackDev.serverCodeProg();

    }
}
//
//    Описать команду разработчиков. В команде разработчиков могут находиться бэкендеры,
//    которые в состоянии писать серверный код, фронтендеры, которые могут программировать экранные формы,
//    и фуллстэк разработчики, совмещающие в себе обе компетенции.
//        Реализовать класс фулстэк разработчика, создать экземпляр
//        и последовательно вызвать
//        все его методы.
interface Frontend{
    void screenFormProg();
}
interface Backend{
    void serverCodeProg();
}

interface Developer{
    void devWork();
}

class FullStackDev implements Frontend,Backend,Developer{

    @Override
    public void screenFormProg() {
        System.out.println("screen prog");
    }

    @Override
    public void serverCodeProg() {
        System.out.println("server code prog");
    }

    @Override
    public void devWork() {

    }
}

class BackendDev implements Backend,Developer{

    @Override
    public void serverCodeProg() {
        System.out.println("server crash");
    }

    @Override
    public void devWork() {

    }
}

class FrontendDev implements Frontend,Developer{

    @Override
    public void screenFormProg() {
        System.out.println("Draw window");
    }

    @Override
    public void devWork() {

    }
}

