package by.epam.trainig.task2.service.impl;

import by.epam.trainig.task2.bean.Appliance;
import by.epam.trainig.task2.util.ApplianceConstants;

/**
 * Реализует проверку на соответствие единицы техники технике-образцу.
 * Техника соответствует технике-образцу, если все ее строковые атрибуты начинаются с
 * соответстсующих атрибутов техники-образца и цена в точности совпадает с ценой техники-образца.
 * Если какой-либо из атрибутов техники-образца считается неопределенным, то его сравнение пропускается.
 * Значение, указывающие на неопределенность атрибута, определены в классе ApplianceConstants.
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