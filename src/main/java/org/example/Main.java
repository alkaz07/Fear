package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Mouse mikky = new Mouse("Микки");
        Cat cat1 = new Cat("Том");
        Dog dog = new Dog("Плуто");
       /* mikky.say();
        cat1.say();
        dog.say();*/
        mikky.getScared(cat1);
        dog.getScared(cat1);

        cat1.getScared(dog);

        dog.getScared(()->{return "СВИСТ";});
        cat1.getScared(()->{
            Scanner scanner = new Scanner(System.in);
            System.out.println("введите строковый шум");
            String noise = scanner.nextLine();
            return noise;
        });
    }
}

//Сделать интерфейс Шумное
interface Noisy {
//	объявить метод шуметь
    String makeNoise();
}
//
//Сделать абстрактный класс Зверюшка
abstract class Zverushka {
//с аттрибутом Имя
    String name;

    public Zverushka(String name) {
        this.name = name;
    }
//абстрактный метод убегать
    public abstract void runAway();
//абстрактный метод говорить
    public abstract void say();
//общий метод: зверюшку может что-то Шумное напугать, напуганная зверюшка что-то говорит и убегает
    public void getScared(Noisy trigger){
        String s = trigger.makeNoise();
        say();
        System.out.println(this+ " услышала "+s);
        runAway();
    }
}
//
//Сделать конкретные классы: Мышка, Кошка, Собачка
//
//Кошка и собачка должны реализовывать интерфейс Шумное
class Cat extends Zverushka implements Noisy{
    @Override
    public String toString() {
        return "Cat{" +
                "'" + name + '\'' +
                '}';
    }

    public Cat(String name) {
        super(name);
    }

    @Override
    public String makeNoise() {
        return "МЯ-ААА-УУУУ";
    }

    @Override
    public void runAway() {
        System.out.println("уносится галопом в поисках дерева");
    }

    @Override
    public void say() {
        System.out.println("мяу");
    }
}

class Dog extends Zverushka implements Noisy{

    @Override
    public String toString() {
        return "Dog{" +
                "'" + name + '\'' +
                '}';
    }

    public Dog(String name) {
        super(name);
    }

    @Override
    public String makeNoise() {
        return "ГАВ-ГАВ";
    }

    @Override
    public void runAway() {
        System.out.println("отбегает, но недалеко");
    }

    @Override
    public void say() {
        System.out.println( makeNoise());
    }
}

class Mouse extends Zverushka {

    public Mouse(String name) {
        super(name);
    }

    @Override
    public void runAway() {
        System.out.println("прячется в ближайшую норку");
    }

    @Override
    public void say() {
        System.out.println("пи-пи-пи");
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "'" + name + '\'' +
                '}';
    }
}
//
//Таким образом, мы можем продемонстрировать, что кошка пугает мышку, собачка пугает кошку, мышку или другую собачку
//
//Показать как собака может быть напугана неким алгоритмом, реализующим Шумное