package by.epam.trainig.task2.service.impl;

import by.epam.trainig.task2.util.CommandConstants;

/** ������������ ��� �������� � ��������� �������, �������� � ����������.
 * �������� ��������� ��������, ���� ������������� �������:
 * [���_��������] {[��������1], [��������2],...}.
 * ��� ������ [..] ������ ���� �������.
 * 
 * @author Noname
 *
 */
public class Command {

	private String type;
	private String[] args;
	private boolean isValid;

	/**
	 * ������������ �������� �� ���������� �������� commandStr.
	 * �������� ��������� ��������, ���� ������������� �������, ���������� � �������� ������.
	 * @param commandStr - ����������� ��������
	 */
	public Command (String commandStr) {
		int openContainerCharIndex = commandStr.indexOf(CommandConstants.OPEN_ARG_LIST_CHAR);
		int closeContainerCharIndex = commandStr.indexOf(CommandConstants.CLOSE_ARG_LIST_CHAR);
		if (openContainerCharIndex == -1 || closeContainerCharIndex == -1) {
			isValid = false;
			return;
		}
		type = commandStr.substring(0, openContainerCharIndex - 1).trim();
		args = commandStr.substring(openContainerCharIndex + 1, closeContainerCharIndex).split(",");	
		if (args.length == 0) {
			isValid = false;
			return;
		}
		for (int i = 0; i < args.length; i ++) {
			args[i] = args[i].trim();
		}
		isValid = true;
	}
	
	public String getType() {
		return type;
	}

	public String[] getArgList() {
		return args;
	}

	public boolean isValid() {
		return isValid;
	}
}
