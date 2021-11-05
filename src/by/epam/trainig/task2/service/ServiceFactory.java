package by.epam.trainig.task2.service;

import by.epam.trainig.task2.service.impl.StorageKeeper;

public class ServiceFactory {
	static Service service = new StorageKeeper();
	
	private ServiceFactory() {};
	
	public static Service getServiceInstance() {
		return service;
	}
}
