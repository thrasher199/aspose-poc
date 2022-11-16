package com.mircolink.aspose.service;

import com.aspose.words.Document;
import com.mircolink.aspose.dao.MyDao;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class MyReportService {

    private final MyDao myDao;
    private final ResourceLoader resourceLoader;

    public MyReportService(MyDao myDao, ResourceLoader resourceLoader) {
        this.myDao = myDao;
        this.resourceLoader = resourceLoader;
    }

    /*public void generateReport() throws Exception {
        var customerDataTable = myDao.getCustomerData();
        var customerLoanDataTable = myDao.getCustomerDetail();
        var documentStream = resourceLoader.getResource("classpath:/templates/docx/customer_mergefield2.docx").getInputStream();
        Document document = new Document(documentStream);
        document.getMailMerge().executeWithRegions(customerDataTable);
        document.getMailMerge().executeWithRegions(customerLoanDataTable);

        document.save("D:\\playground\\aspose\\src\\main\\resources\\templates\\output\\test_output.docx");
    }*/

    public void generateReport(int i) throws Exception {

        var customerDataTable = myDao.getCustomerData(i);
        var customerLoanDataTable = myDao.getCustomerDetail(i);
        var documentStream = resourceLoader.getResource("classpath:/templates/docx/customer_mergefield2.docx").getInputStream();
        Document document = new Document(documentStream);
        document.getMailMerge().executeWithRegions(customerDataTable);
        document.getMailMerge().executeWithRegions(customerLoanDataTable);

        document.save("D:\\playground\\aspose\\src\\main\\resources\\templates\\output\\test_output" + "_" + i + ".pdf");
    }
}
