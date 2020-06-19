package org.vtb.web;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestWatcher implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext extensionContext)  {
        Method method = extensionContext.getRequiredTestMethod();
        if (extensionContext.getExecutionException().isPresent()) {
            makeScreenshotOnFailure(method.getName());
        }
    }

    private byte[] makeScreenshotOnFailure(String testName) {
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yy");
        String sDate = ft.format(new Date());
        byte[] screenshotBytes = new byte[0];
        try {
            File screenshot1 = new File("/home/galy4/нужное/work/PTC/New_PTC/SQA-050/SQA-050-01/screenshot/" + testName + sDate+".png");
            screenshot1.delete();
            screenshotBytes = ((TakesScreenshot) DriverFactory.driver).getScreenshotAs(OutputType.BYTES);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(screenshotBytes));
            ImageIO.write(image, "png", screenshot1);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotBytes;
    }
}
