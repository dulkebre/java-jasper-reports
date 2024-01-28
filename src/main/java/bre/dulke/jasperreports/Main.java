package bre.dulke.jasperreports;

import static bre.dulke.jasperreports.SimpleReportFiller.REPORT_ROOT;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws IOException {
		logger.info("Lets get busy!");
		SimpleReportFiller simpleReportFiller = new SimpleReportFiller();

		simpleReportFiller.setReportFileName("helloCarReport.jrxml");
		simpleReportFiller.compileReport();

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("title", "Sample Jasper Reports");
		parameters.put("companyName", "Hello Car");
		parameters.put("clientName", "My first client");

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL url = classloader.getResource("logo.jpg");

		parameters.put("logo", url.getPath());

		List<Map<String, ?>> reportData = new ArrayList<Map<String, ?>>();
		reportData.add(createNewRowData(1, "First item description", 3.0, createDate("2023-12-28"), 17500));
		reportData.add(createNewRowData(2, "Second item description", 4.0, createDate("2023-01-29"), 0.33));
		reportData.add(createNewRowData(2, "Third item description", 42.0, null, 131));

		JRMapCollectionDataSource hardcodedDataSource = new JRMapCollectionDataSource(reportData);
		simpleReportFiller.setParameters(parameters);
		simpleReportFiller.setDataSource(hardcodedDataSource);
		simpleReportFiller.fillReport();

		SimpleReportExporter simpleExporter = new SimpleReportExporter();
		simpleExporter.setJasperPrint(simpleReportFiller.getJasperPrint());

		simpleExporter.exportToPdf(REPORT_ROOT + "helloCarReport.pdf", "Author - Nikola");
		simpleExporter.exportToXlsx(REPORT_ROOT + "helloCarReport.xlsx", "Sheet - Test");
		simpleExporter.exportToCsv(REPORT_ROOT + "helloCarReport.csv");
		simpleExporter.exportToHtml(REPORT_ROOT + "helloCarReport.html");

		logger.info("Voila - reports have been created");

	}

	private static Map<String, Object> createNewRowData(int orderItem, String description, double quantity, Date date, double price) {
		Map<String, Object> result = new HashMap<>();
		result.put("order-item", Integer.valueOf(orderItem));
		result.put("order-item-description", description);
		result.put("order-item-quantity", Double.valueOf(quantity));
		result.put("order-date", date);
		result.put("order-item-total-price", Double.valueOf(price));
		return result;
	}
	
	public static Date createDate(String date) {
	     try {
	         return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	     } catch (ParseException e) {
	         return null;
	     }
	  }
}
