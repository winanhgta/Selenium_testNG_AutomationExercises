package com.huy.automationexercise.utils;

import com.huy.automationexercise.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;

public class WebUI {
    public static void removeAds() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            // 1. Tìm và xóa các thẻ iframe/ins của Google Ads
            String script = "var ads = document.getElementsByClassName('adsbygoogle'); " +
                    "for (var i = 0; i < ads.length; i++) { ads[i].remove(); } " +
                    "var vignettes = document.getElementsByClassName('google-vignette'); " +
                    "for (var i = 0; i < vignettes.length; i++) { vignettes[i].remove(); } " +
                    "var overlay = document.querySelector('.grippy-host'); " + // Nút kéo quảng cáo thường có class này
                    "if(overlay) { overlay.remove(); } " +
                    "document.body.style.overflow = 'auto';"; // Mở khóa thanh cuộn nếu bị quảng cáo chặn
            js.executeScript(script);
        } catch (Exception e) {
            // Không có quảng cáo thì bỏ qua, không làm fail test
        }
    }
}
