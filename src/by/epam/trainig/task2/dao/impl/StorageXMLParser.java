package by.epam.trainig.task2.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import by.epam.trainig.task2.bean.Appliance;
import by.epam.trainig.task2.dao.DAO;
import by.epam.trainig.task2.util.XMLParserConstants;
import by.epam.trainig.task2.service.impl.ApplianceComparator;

/**
 * ����� ��������� ������� ������-������ xml-�����.
 * ������ xml-�����:
 * <?xml ... ?>
 * <appliacne_list>
 * <appliance attr1="val1", attr2="val2", ... />
 * <appliance attr1="val3", attr2="val4", ... />
 * ...
 * <appliance_list>
 * @author Noname
 *
 */
public class StorageXMLParser implements DAO {
	private DocumentBuilder builder;
	private Document StorageXMLDoc;
	String XMLPath;
	
	/**
	 * ��������� ��������� ������������� ��������, ����������� ��� ������ � xml-�������.
	 * ��������� ������, ���������� ���� � ������������ xml-�����.
	 * @param XMLPath
	 */
	public StorageXMLParser(String XMLPath) {
		this.XMLPath = XMLPath;
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("Parser config is failed");
		}
		try {
			StorageXMLDoc = builder.parse(new File(XMLPath));
		} catch (Exception IOe) {
			createEmptyXML();
		}
	}
	
	/** 
	 * ��������� ����� ������� ������� �� �������.
	 * �������� ������ ������ Appliance, �������� ������ ��������������� ��������� ������� �������.
	 * ���������� ������ ��������������� �������.
	 * ������� ������������ ���������� ����� matches ������ ApplianceComparator.
	 * @param sampleAppliance
	 * @return
	 */	
	public List<Appliance> getApplianceListBySample(Appliance sampleAppliance) {
		List<Appliance> applianceList = new ArrayList<Appliance>();
		NodeList applianceElemList = StorageXMLDoc.getElementsByTagName(XMLParserConstants.APPLIANCE_TAG_NAME);
		NamedNodeMap attrList;
		Appliance appliance;
		for (int i = 0; i < applianceElemList.getLength(); i ++) {
			attrList = applianceElemList.item(i).getAttributes();
			appliance = new Appliance(attrList.getNamedItem(XMLParserConstants.APPLIANCE_TYPE_ATTR_NAME).getNodeValue(),
					  attrList.getNamedItem(XMLParserConstants.APPLIANCE_MODEL_ATTR_NAME).getNodeValue(),
					  attrList.getNamedItem(XMLParserConstants.APPLIANCE_MANUFACTURER_ATTR_NAME).getNodeValue(),
					  Double.parseDouble(attrList.getNamedItem(XMLParserConstants.APPLIANCE_PRICE_ATTR_NAME).getNodeValue()));	
			if (ApplianceComparator.matches(sampleAppliance, appliance)) {
				applianceList.add(appliance);
			}
		}
		return applianceList;
	}
	
	/**
	 * ��������� ������� ������� � xml ��������.
	 * ����������� ������������� ��������������� ���������� ������� � ����������� �����������.
	 * ����� ������������� ������ ������ � xml-����� �� �����������.
	 * ��� ��������� ������ � ������� ������������ � ���� ��� ������ �� 
	 * ���������� � ������� �������� "exit {}"
	 * @param appliance
	 */	
	public void addAppliance(Appliance appliance) {
		Element newApplianceElem = StorageXMLDoc.createElement(XMLParserConstants.APPLIANCE_TAG_NAME);
		newApplianceElem.setAttribute(XMLParserConstants.APPLIANCE_TYPE_ATTR_NAME, appliance.getType());
		newApplianceElem.setAttribute(XMLParserConstants.APPLIANCE_MODEL_ATTR_NAME, appliance.getModel());
		newApplianceElem.setAttribute(XMLParserConstants.APPLIANCE_MANUFACTURER_ATTR_NAME, appliance.getManufacturer());
		newApplianceElem.setAttribute(XMLParserConstants.APPLIANCE_PRICE_ATTR_NAME, String.valueOf(appliance.getPrice()));
		StorageXMLDoc.getElementsByTagName(XMLParserConstants.APPLIANCE_LIST_TAG_NAME).item(0).appendChild(newApplianceElem);
	}
	

	/**
	 * ��������� ��� ����������� � ������� ������ addAppliance  ������ � ������� � xml-�����.
	 * ���� � xml-����� ��������� � ������ XMLParserConstants.
	 */
	public void saveChanges() {
		saveDocInXML(StorageXMLDoc);
	}
	
	private void createEmptyXML() {
		StorageXMLDoc = builder.newDocument();
		Element ApplianceListElem = StorageXMLDoc.createElement(XMLParserConstants.APPLIANCE_LIST_TAG_NAME);
		StorageXMLDoc.appendChild(ApplianceListElem);
		saveDocInXML(StorageXMLDoc);
	}
	
	/**
	 * ���������� �������� doc � xml-����, ����������� �� ����, ������������ ��������� XMLPath.
	 * ���� ���� ����������, ���������� ��� ����������.
	 * @param doc
	 */
	private void saveDocInXML(Document doc) {
		DOMSource source = new DOMSource(doc);
		FileWriter fileWriter = null;
		StreamResult streamResult;
		Transformer transformer;
		try {
			fileWriter = new FileWriter(new File(XMLPath));
		} catch (IOException e) {
			throw new RuntimeException("IO is failed (open FileWriter)");
		}
		streamResult = new StreamResult(fileWriter);
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, streamResult);
		} catch (Exception e) {
			throw new RuntimeException("Transformer is failed");
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				throw new RuntimeException("IO is failed (close FileWriter)");
			}
		}
	}
}
