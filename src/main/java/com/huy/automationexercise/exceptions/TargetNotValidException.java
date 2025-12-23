package com.huy.automationexercise.exceptions;

import io.qameta.allure.Allure;

/**
 * Exception này được ném ra khi giá trị Target hoặc Browser truyền vào
 * không hợp lệ hoặc chưa được định nghĩa trong Framework.
 */
public class TargetNotValidException extends RuntimeException {

    public TargetNotValidException(String target) {
        // Gửi thông báo lỗi vào Constructor của RuntimeException để in ra Console
        super("Target hoặc Browser không hợp lệ: " + target);

        // Đẩy thông báo lỗi trực tiếp lên Allure Report để dễ dàng tracking khi xem báo cáo
        // (Dòng này sẽ tự động chạy nếu bạn đã cài Allure, nếu chưa cài nó sẽ báo đỏ -
        // bạn có thể comment lại cho đến khi setup Allure xong)
        Allure.addAttachment("Error Details", "Giá trị truyền vào không khớp với Enum: " + target);
    }

    public TargetNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}