package com.mengma.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class StaticTest {
    public static void main(String[] args) {

        Manager boss = new Manager(" Carl Cracker ", 80000, 4);
//        Method method = Manager.class.getMethod();


        /*Manager boss = new Manager(" Carl Cracker ", 80000, 4);
        boss.setBonus(50000);
        Employee[] staff = new Employee[3];
        staff[0] = boss;
        staff[1] = new Employee(" Dick ", 60000, 2);
        staff[2] = new Employee(" Harry ", 65000, 3);


//        int n = 0;
        for (Employee e : staff) {
//            n = Employee.nextId++;
//            e.setId();
            System.out.println(" name = " + e.getName() + " , id = " + e.getId() + " , salary = " + e.getSalary());
        }*/

        /*Manager[] managers = new Manager[10];
        Employee[] staffs = managers;
        staffs[0] = new Employee(" Carl Cracker ", 80000, 4);
        System.out.println(" name = " + staffs[0].getName() + " , id = " + staffs[0].getId() + " , salary = " + staffs[0].getSalary());*/
//        int n = Employee.getNextId();
//        System.out.println(" Next available id = " + n);
        /*Manager boss = new Manager(" Carl Cracker ", 80000, 4);
        *//*Class<?> clazz = boss.getClass().getSuperclass();
        System.out.println(clazz.getName());*//*
        try {
            *//*Manager manager = boss.getClass().newInstance();
            System.out.println("名称："+manager.getName());
            if(manager instanceof Manager){
                manager.setName("乱七八糟");
            }
            if(boss.getClass() == manager.getClass()){
                System.out.println("hhhhhhh");
            }
            System.out.println("名称："+manager.getName());*//*

            Class<? extends Manager> clazz = boss.getClass();
            Field[] field = clazz.getFields();

            *//*field.setAccessible(true);
            Object v = field.getDouble(boss);
            System.out.println(v);*//*
        } catch (Exception e) {
            System.out.println("异常");
        }*/

        /*boss.setBonus(50000);
        Employee[] staff = new Employee[3];
        staff[0] = boss;
        staff[0].nextId = 3;
        System.out.println(staff[0].nextIds);
        System.out.println(staff[0].nextId);*/
    }
}

abstract class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public abstract String getDescription();

    public String getName() {
        return name;
    }
}

class Employee extends Person {

    static {
        System.out.println(" Hello , World ");
    }

    //    public static final int nextId = 1;  //final修饰为常量
    public static int nextId = 2;  //静态属于类
    private String name;  //非静态属于对象
    private double salary;
    private int id;
    public static final int nextIds = 1;  //final修饰为常量

    public Employee() {
        super();
    }

    public Employee(String n, double s, int id) {
        super(n);
        this.name = n;
        this.salary = s;
        this.id = id;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Employee.nextId = nextId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        id = nextId;
        nextId++;
    }

    public static void main(String[] args) {
        Employee e = new Employee(" Harry ", 50000, 2);
        System.out.println(e.getName() + ":" + e.getSalary());
    }

    @Override
    public String getDescription() {
        return "这个是描述";
    }
}

class Manager extends Employee {

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    private double bonus;

    public Manager() {
        super();
    }

    public Manager(String n, double s, int id) {
        super(n, s, id);
        bonus = 0;
    }

    public double getSalary() {
        double baseSalary = super.getSalary();
        return baseSalary + this.bonus;
    }
}
