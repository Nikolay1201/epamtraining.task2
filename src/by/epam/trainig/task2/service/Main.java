package by.epam.trainig.task2.service;

import java.util.Scanner;

public class Main {
	/**
	 * ��������� ����� ���������.
	 * ������� ������� �������
	 * 
	 * - �������� ����� ������� ������� � ����������: 
	 * ��� - ����, ������ - abc123, ������������� - LG, ���� - 40$
	 * add {����, abc123, LG, 40}
	 * 
	 * - ����� ���� ������ abc123 ������������� LG ���������� 40$
	 * get {   ����, abc123, LG    ,    40}
	 * 
	 * - ����� ��� ����� ����� ������ ����������� LG ����� ���������
	 * get {����, *, LG, *}
	 * 
	 * - ������� ������ ���� ������ ������� 
	 * get {*,*,*,*}
	 * 
	 * - ����� �� ���������� � ��������� ��� ��������� � �����
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
