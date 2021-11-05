package by.epam.trainig.task2.service.impl;

import by.epam.trainig.task2.bean.Appliance;
import by.epam.trainig.task2.util.ApplianceConstants;

/**
 * ��������� �������� �� ������������ ������� ������� �������-�������.
 * ������� ������������� �������-�������, ���� ��� �� ��������� �������� ���������� �
 * ��������������� ��������� �������-������� � ���� � �������� ��������� � ����� �������-�������.
 * ���� �����-���� �� ��������� �������-������� ��������� ��������������, �� ��� ��������� ������������.
 * ��������, ����������� �� ���������������� ��������, ���������� � ������ ApplianceConstants.
 * 
 * @author Noname
 *
 */
public class ApplianceComparator {
	public static boolean matches(Appliance sampleAppliance, Appliance appliance) {
		return ( sampleAppliance.getType().equals(ApplianceConstants.STR_IS_NOT_DEFINED) || appliance.getType().startsWith(sampleAppliance.getType()) )
				&& ( sampleAppliance.getModel().equals(ApplianceConstants.STR_IS_NOT_DEFINED) || appliance.getModel().startsWith(sampleAppliance.getModel()) )
				&& ( sampleAppliance.getManufacturer().equals(ApplianceConstants.STR_IS_NOT_DEFINED) || appliance.getManufacturer().startsWith(sampleAppliance.getManufacturer()) )
				&& ( (sampleAppliance.getPrice() == ApplianceConstants.PRICE_IS_NOT_DEFINED) || (appliance.getPrice() == sampleAppliance.getPrice()));
	}
}