package by.epam.trainig.task2.dao;

import java.util.List;
import by.epam.trainig.task2.bean.Appliance;

public interface DAO {
	List<Appliance> getApplianceListBySample(Appliance sampleAppliance);
	void addAppliance(Appliance appliance);
	void saveChanges();
}
