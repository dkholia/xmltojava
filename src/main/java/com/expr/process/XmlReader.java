/**
 * 
 */
package com.expr.process;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.expr.model.CustomFieldValue;
import com.expr.model.Defect;
import com.expr.model.DefectEvent;
import com.expr.model.Defects;
import com.expr.model.HistoricalEvent;
import com.expr.model.ObjectFactory;

public class XmlReader {

	private static final String FILENAME = "defects_ned.xlsx";

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws JAXBException, IllegalArgumentException, IllegalAccessException {
		JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);

		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		Defects defects = (Defects)unmarshaller.unmarshal(ClassLoader.getSystemResourceAsStream("ned.xml"));

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Defects");

		/** Parse defects on the xml file**/
		int record = 0;
		Row row = sheet.createRow(record++);
		Field[] fields =  defects.getDefect().get(0).getClass().getFields();
		int columnNum =0;
		Field[] myFields =   null;
		Cell cell = null;

		/** First create the header row */
		for( Field currField : fields){
			Class<?>[] intfs =  currField.getType().getInterfaces();
			String simpleName="";
			if(intfs!=null && intfs.length>0)
				simpleName = intfs[0].getSimpleName();
			if(currField.getName()=="defectEvent" || currField.getName()=="customFieldValue")
				continue;
			if(currField.getType().getName() != "java.lang.String" && currField.getType().getName() != "java.math.BigInteger" && !simpleName.equals("User")  ){
				myFields =  currField.getType().getFields();
			}else{
				myFields=null;
			}
			if(myFields!=null && myFields.length > 0){
				for ( Field myField : myFields){
					cell =  row.createCell(columnNum++);
					cell.setCellValue(currField.getName() + "_"+ myField.getName());
				}
			}else{
				cell = row.createCell(columnNum++);
				cell.setCellValue(currField.getName());
			}
		}

		Map<Key, List<CustomFieldValue>> customFieldMap = new HashMap<Key, List<CustomFieldValue>>();
		Map<Key, List<DefectEvent>> defectEventMap = new HashMap<Key, List<DefectEvent>>();
		Map<Key, List<HistoricalEvent>> historicalEventMap = new HashMap<Key, List<HistoricalEvent>>();
		for( Defect defect:  defects.getDefect()) {
			row = sheet.createRow(record++);

			int colNum = 0;
			for( Field currField : fields){
				myFields =   null;
				String simpleName="";
				Object value = null;
				Class<?>[] intfs =  currField.getType().getInterfaces();
				if(intfs!=null && intfs.length>0)
					simpleName = intfs[0].getSimpleName();
				if(currField.getType().getName() == "java.lang.String" || currField.getType().getName() == "java.math.BigInteger" || simpleName.equals("User") ){
					cell = row.createCell(colNum++);
					value = currField.get(defect);
					cell.setCellValue(null!=value? value.toString(): "");
				}else{
					Class<?> clazz = currField.getType();
					if(currField.getName()=="customFieldValue"){
						myFields =  clazz.getFields();
						Key k = new Key(defect.getDefectNumber(), defect.getSummary());
						List<CustomFieldValue> ll = new ArrayList<CustomFieldValue>();
						ll.addAll(defect.getCustomFieldValue());
						customFieldMap.put(k, ll);
					}else{
						if( clazz.getName() == "java.util.List"){
							List<?> list = (List<?>) currField.get(defect);
							if(currField.getName()=="defectEvent"){
								List<DefectEvent> defectEventList = new ArrayList<DefectEvent>();
								if(null == defectEventMap.get(defect.getDefectNumber())){
									try {
										defectEventList.addAll((Collection<? extends DefectEvent>) currField.get(defect));
									} catch (Exception e) {}
								}else{
									defectEventList = defectEventMap.get(defect.getDefectNumber());
									defectEventList.add((DefectEvent) currField.get(defect));
								}
								defectEventMap.put(new Key( defect.getDefectNumber() , defect.getSummary()), defectEventList);
								continue;
							}else if(currField.getName()=="historicalEvent"){
								List<HistoricalEvent> historicalEventList = new ArrayList<HistoricalEvent>();
								if(null == historicalEventMap.get(defect.getDefectNumber())){
									try {
										historicalEventList.addAll((Collection<? extends HistoricalEvent>) currField.get(defect));
									} catch (Exception e) {}
								}else{
									historicalEventList = historicalEventMap.get(defect.getDefectNumber());
									historicalEventList.add((HistoricalEvent) currField.get(defect));
								}
								historicalEventMap.put(new Key( defect.getDefectNumber() , defect.getSummary()), historicalEventList);
								continue;
							}
							for ( int i=0 ;i<list.size() ;i++){  
								Field[] listFields =  list.get(i).getClass().getFields();
								for(Field f : listFields){
									try {
										cell =  row.createCell(colNum++);
										Object val = f.get(list.get(i))!=null?f.get(list.get(i)):"";
										cell.setCellValue(val.toString());
									} catch (Exception e) {
										System.out.println("Exception for " + f.getName());
									}
								}
							}
							continue;
						}else{
							if(clazz.getName() != "java.lang.String" && clazz.getName() != "java.math.BigInteger"  ){
								myFields =  clazz.getFields();
							}else{
								myFields = null;
							}
						}
						try {
							if(myFields!=null && myFields.length > 0){
								for ( Field myField : myFields){
									cell =  row.createCell(colNum++);
									Object val = myField.get(currField.get(defect));
									cell.setCellValue(null!=val ? val.toString():"");
								}
							}else{
								cell = row.createCell(colNum++);
								value = currField.get(defect);
								cell.setCellValue(value.toString());
							}
						} catch (Exception e) {
							cell.setCellValue("");
						}
					}	
				}
			}
		}

		/** create separate worksheet for defectEvent **/
		XSSFSheet defectEvents = workbook.createSheet("Defect Events");
		TreeMap<Key, List<DefectEvent>> sortedMap = new TreeMap<Key, List<DefectEvent>>(defectEventMap);

		Key firstRecord =  (Key) sortedMap.keySet().toArray()[0];
		fields =  sortedMap.get(firstRecord).get(0).getClass().getFields();
		record = 0;
		columnNum = 0;
		row = defectEvents.createRow(record++);
		cell = row.createCell(columnNum++);
		cell.setCellValue("defectNumber");
		cell = row.createCell(columnNum++);
		cell.setCellValue("summary");
		for( Field currField : fields){
			if(!currField.getName().equals("notes"))
				continue;
			cell = row.createCell(columnNum++);
			cell.setCellValue(currField.getName());
		}
		for( Key summary :  sortedMap.keySet()){
			List<DefectEvent> events = sortedMap.get(summary);
			row = defectEvents.createRow(record++);
			for(DefectEvent defectEvent : events){
				int colNum = -1;

				cell = row.createCell(++colNum);
				cell.setCellValue(summary.getDefectNumber().toString());
				cell = row.createCell(++colNum);
				cell.setCellValue(summary.getSummary());

				for( Field currField : fields){
					if(!currField.getName().equals("notes"))
						continue;
					int next = ++colNum;
					if(null!=row.getCell(next)){
						cell = row.getCell(next);
					}else{
						cell = row.createCell(next);
					}
					try {
						Object value = currField.get(defectEvent);
						String prevVal = cell.getStringCellValue();
						String currVal = (null!=value?value.toString():"") + "\n";
						cell.setCellValue(prevVal + currVal);
					} catch (Exception e) {
						cell.setCellValue("");
					}
				}
			}
		}

		if(!historicalEventMap.isEmpty()){
			/** create separate worksheet for historicalEvents **/
			XSSFSheet historicalEvents = workbook.createSheet("Historical Events");
			TreeMap<Key, List<HistoricalEvent>> sortedMap1 = new TreeMap<Key, List<HistoricalEvent>>(historicalEventMap);

			firstRecord =  (Key) sortedMap.keySet().toArray()[0];
			
			fields =  HistoricalEvent.class.getFields();
			record = 0;
			columnNum = 0;
			row = historicalEvents.createRow(record++);
			cell = row.createCell(columnNum++);
			cell.setCellValue("defectNumber");
			cell = row.createCell(columnNum++);
			cell.setCellValue("summary");
			for( Field currField : fields){
				if(!currField.getName().equals("information"))
					continue;
				cell = row.createCell(columnNum++);
				cell.setCellValue(currField.getName());
			}
			for( Key summary :  sortedMap1.keySet()){
				List<HistoricalEvent> events = sortedMap1.get(summary);
				if(events.isEmpty())
					continue;
				row = historicalEvents.createRow(record++);
				for(HistoricalEvent historicalEvent : events){
					int colNum = -1;

					cell = row.createCell(++colNum);
					cell.setCellValue(summary.getDefectNumber().toString());
					cell = row.createCell(++colNum);
					cell.setCellValue(summary.getSummary());

					for( Field currField : fields){
						if(!currField.getName().equals("information"))
							continue;
						int next = ++colNum;
						if(null!=row.getCell(next)){
							cell = row.getCell(next);
						}else{
							cell = row.createCell(next);
						}
						try {
							Object value = currField.get(historicalEvent);
							String prevVal = cell.getStringCellValue();
							String currVal = (null!=value?value.toString():"") + " updated by " 
									+ historicalEvent.getLastName() +","+historicalEvent.getFirstName() + " on " + historicalEvent.getDate() +  "\n";
							cell.setCellValue(prevVal + currVal);
						} catch (Exception e) {
							cell.setCellValue("");
						}
					}
				}
			}
		}
		/** create separate worksheet for Custom Fields **/
		XSSFSheet customFields = workbook.createSheet("Custom Fields");
		record = 0;
		columnNum = 0;
		row = customFields.createRow(record++);
		cell = row.createCell(columnNum++);
		cell.setCellValue("defectNumber");
		cell = row.createCell(columnNum++);
		cell.setCellValue("summary");
		cell = row.createCell(columnNum++);
		cell.setCellValue("customFieldName");
		cell = row.createCell(columnNum++);
		cell.setCellValue("customFieldValue");
		cell = row.createCell(columnNum++);
		cell.setCellValue("multiLineText");
		cell = row.createCell(columnNum++);
		cell.setCellValue("multiSelectValue");
		TreeMap<Key, List<CustomFieldValue>> sortedMap2 = new TreeMap<Key, List<CustomFieldValue>>(customFieldMap);

		for( Key summary :  sortedMap2.keySet()){
			List<CustomFieldValue> customFieldValueList = sortedMap2.get(summary);

			for(CustomFieldValue fieldValue : customFieldValueList){
				columnNum =0;
				row = customFields.createRow(record++);

				cell = row.createCell(columnNum++);
				cell.setCellValue(summary.getDefectNumber().toString());

				cell = row.createCell(columnNum++);
				cell.setCellValue(summary.getSummary());

				cell = row.createCell(columnNum++);
				cell.setCellValue(fieldValue.getFieldName());

				cell = row.createCell(columnNum++);
				cell.setCellValue(fieldValue.getFieldValue());

				cell = row.createCell(columnNum++);
				cell.setCellValue(fieldValue.getMultiLineText().getContent());

				cell = row.createCell(columnNum++);
				cell.setCellValue(fieldValue.getMultiSelect().getMultiSelectValue());
			}

		}

		/** Finally write to the excel sheet**/


		try {
			FileOutputStream outputStream = new FileOutputStream(FILENAME);
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Key implements Comparable<Key> {

	private BigInteger defectNumber;
	private String summary;

	public Key(BigInteger defectNumber, String summary) {
		super();
		this.defectNumber = defectNumber;
		this.summary = summary;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public BigInteger getDefectNumber() {
		return defectNumber;
	}
	public void setDefectNumber(BigInteger defectNumber) {
		this.defectNumber = defectNumber;
	}
	public int compareTo(Key o) {
		return this.getDefectNumber().intValue() - o.getDefectNumber().intValue();
	}
}
