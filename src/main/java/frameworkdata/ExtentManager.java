package frameworkdata;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

public class ExtentManager {

    public static ExtentReports extent;
    public static Properties prop;

    public static ExtentReports getInstance() {
        prop = new Properties();
        try {
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/config/config.properties");
            prop.load(fs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (extent == null) {
            Date d = new Date();
            String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".html";
            extent = new ExtentReports(System.getProperty("user.dir") + "/reports/" + fileName, true, DisplayOrder.NEWEST_FIRST);
        }
        extent.loadConfig(new File(System.getProperty("user.dir") + "/ReportsConfig.xml"));
        // optional
//        extent.addSystemInfo("Appium Version", "1.18.1").addSystemInfo(
//                "Environment", "QA").addSystemInfo("Platform", prop.getProperty("platformName"));

        return extent;
    }


}

