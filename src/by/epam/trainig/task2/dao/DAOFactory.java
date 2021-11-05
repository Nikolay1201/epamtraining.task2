package by.epam.trainig.task2.dao;

import by.epam.trainig.task2.dao.impl.StorageXMLParser;
import by.epam.trainig.task2.util.XMLParserConstants;

public class DAOFactory {
	static DAO instance = new StorageXMLParser(XMLParserConstants.STD_XML_PATH);
		
	private DAOFactory() {};
	
	public static DAO getDAO() {
		return instance;
	}
}
