package com.huy.automationexercise.exceptions;

public class HeadlessNotSupportedException extends RuntimeException {
    public HeadlessNotSupportedException(String browser) {
        super("Trình duyệt " + browser + " hiện chưa hỗ trợ chế độ Headless.");
    }
}
