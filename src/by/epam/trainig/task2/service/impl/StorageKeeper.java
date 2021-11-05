package by.epam.trainig.task2.service.impl;

import java.util.List;

import by.epam.trainig.task2.util.ApplianceConstants;
import by.epam.trainig.task2.util.CommandConstants;
import by.epam.trainig.task2.bean.Appliance;
import by.epam.trainig.task2.dao.DAO;
import by.epam.trainig.task2.dao.DAOFactory;
import by.epam.trainig.task2.service.Service;

/** 
 * ��������� ������� ���������-���������� (storagekeeper).
 * @author Noname
 *
 */
public class StorageKeeper implements Service {
	
	DAO StorageDAO = DAOFactory.getDAO();
	
	/**
	 * ��������� ��������, ���������� � ������ commandStr.
	 * ������ �������� ������ � ������ Command.
	 * 
	 * �������������� ��������:
	 * - ���������� ������� �������
	 * add {[���], [������], [�������������], [����]}
	 * - ��������� ������ �������, ��������������� �������
	 * get {[���], [������], [�������������], [����]}
	 * ������� ������������ ������� ������� ���������� � ������ ApplianceComparator
	 * ���� ������ ������-���� ��������� ������ ������ '*', ������� ��������� ��������������
	 * - ����� �� ���������� 
	 * exit {}
	 * ��� ���� ��������� ������ ���� ����������� ��������� � xml-����.
	 * 
	 * ��������� ������, ���������� �������� � ����������.
	 * ���������� false, ���� ������ �������� �������� ������ �� ����������. 
	 * ����� ���������� true.
	 */
	public boolean executeCommand(String commandStr) {
		Command command = new Command(commandStr);
		if (!command.isValid()) {
			System.out.println("The command is not valid");
			return true;
		}
		Appliance sampleAppliance = new Appliance();
		switch(command.getType()) {
		case (CommandConstants.GET_APPLIANCE_LIST_BY_SAMPLE_TYPE):
			if (!strArrToAppliance(command.getArgList(), sampleAppliance)) {
				System.out.println("Wrong appliance attributes format. Try again");
				return true;
			}
			List<Appliance> applianceList = StorageDAO.getApplianceListBySample(sampleAppliance);
			if (applianceList.size() == 0) {
				System.out.println("There is no appliances matching your request");
				return true;
			}
			int i = 0;
			for (Appliance appliance : applianceList) {
				i ++;
				System.out.print(i + ".");
				System.out.println(appliance.getFullInfo());
			}
			break;
		case (CommandConstants.ADD_APPLIANCE_TYPE):
			if (!strArrToAppliance(command.getArgList(), sampleAppliance)) {
				System.out.println("Wrong appliance attributes format. Try again");
				return true;
			}
			StorageDAO.addAppliance(sampleAppliance);
			System.out.println("The appliance was added successfully");
			break;
		case (CommandConstants.EXIT_APP_TYPE): 
			StorageDAO.saveChanges();
			System.out.println("Changes were saved successfully. Bye.");
			return false;
		default:
			System.out.println("Unknown command type");				
		}		
		return true;
	}
	
	
	/**
	 * �������������� ��� ��������� ������� appliance ����������,
	 * ������������ � ������� attrList.
	 * ���������� true, ���� ������ �������� ���������� �������� �������:
	 * �������� 0-2 - ������������ ������ ��� ������ "������ �� ���������"
	 * ������� 3 - ������� ����� ��� ������� "������ �� ���������"
	 * (�������� "������ �� ���������" ���������� � ������ ApplianceConstants)
	 * 
	 * @param attrList
	 * @param appliance
	 * @return
	 */
	private boolean strArrToAppliance(String[] attrList, Appliance appliance) {
		if (attrList.length != ApplianceConstants.ATTR_COUNT) {
			return false;
		}
		double price;
		if (attrList[3].equals(ApplianceConstants.STR_IS_NOT_DEFINED)) {
			price = ApplianceConstants.PRICE_IS_NOT_DEFINED;
		} else {
			try {
				price = Double.parseDouble(attrList[3]);
			} catch (NumberFormatException e) {
				return false;
			}
		}
		appliance.reset(attrList[0], attrList[1], attrList[2], price);
		return true;
	}
	
}
