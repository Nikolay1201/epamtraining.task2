package by.epam.trainig.task2.service;

import java.util.Scanner;

public class Main {
	/**
	 * Начальный метод программы.
	 * Примеры входных комманд
	 * 
	 * - добавить новую единицу техники с атрибутами: 
	 * тип - утюг, модель - abc123, производитель - LG, цена - 40$
	 * add {утюг, abc123, LG, 40}
	 * 
	 * - найти утюг модели abc123 производителя LG стоимостью 40$
	 * get {   утюг, abc123, LG    ,    40}
	 * 
	 * - найти все утюги любой модели произодится LG любой стоимости
	 * get {утюг, *, LG, *}
	 * 
	 * - вывести список всех единиц техники 
	 * get {*,*,*,*}
	 * 
	 * - выйти из приложения и сохранить все изменения в файле
	 * exit {}
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Service service = ServiceFactory.getServiceInstance();
		Scanner sc = new Scanner(System.in);
		while (service.executeCommand(sc.nextLine())) {
			System.out.println();
		};
		sc.close();
	}
}
