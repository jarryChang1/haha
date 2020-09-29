package com.jarry.demo1.java3y.ProxyMode.DesignMode.FacadePa;

/**
 * @BelongsProject: demo1
 * @BelongsPackage: com.jarry.demo1.java3y.ProxyMode.DesignMode.FacadePa
 * @Author: Jarry.Chang
 * @CreateTime: 2020-09-25 11:39
 */
//电闸
public class ElectricBrake {
    private Television television = new Television();
    private Television.Computer computer = television.new Computer();
    private Fridge fridge = new Fridge();

    //关闭所有电器
    public void turnOffAll() {
        television.turnOffTV();
        computer.turnOffCom();
        fridge.turnOff();
    }

}
