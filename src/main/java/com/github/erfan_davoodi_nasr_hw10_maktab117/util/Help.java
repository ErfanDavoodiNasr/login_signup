package com.github.erfan_davoodi_nasr_hw10_maktab117.util;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class Help {
    private static Random random;
    private static Gson gson;

    public static synchronized String generateRandomCode() {
        if (random == null) {
            random = new Random();
        }
        return String.format("%06d", random.nextInt(1_000_000));
    }

    public static synchronized Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void requestDispatcher(String path, String attributeName, String attributeValue, ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        req.setAttribute(attributeName, attributeValue);
        req.getRequestDispatcher(path).forward(req, resp);
    }

    public static void requestDispatcher(String path, String attributeName, List<String> attributeValue, ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        req.setAttribute(attributeName, attributeValue);
        req.getRequestDispatcher(path).forward(req, resp);
    }
}
