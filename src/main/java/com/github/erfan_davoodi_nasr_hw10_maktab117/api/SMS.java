package com.github.erfan_davoodi_nasr_hw10_maktab117.api;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

import static com.github.erfan_davoodi_nasr_hw10_maktab117.util.Help.generateRandomCode;
import static com.github.erfan_davoodi_nasr_hw10_maktab117.util.Help.getGson;

public class SMS {
    private static final String API_KEY = "rbCCtKZdFG7WnPnzA4h6fVupyOdUgH4X45V1sTn93pR9qdqd";
    private static final Integer TEMPLATE_ID = 561658;
    private static final String API_URL = "https://api.sms.ir/v1/send/verify";

    public static String[] sendSms(String phoneNumber) {
        String randomCode = generateRandomCode();
        Gson gson = getGson();
        HttpClient client = HttpClient.newHttpClient();

        Parameter[] parameter = {new Parameter("Code", randomCode)};
        BodyRequest bodyRequest = BodyRequest.builder()
                .parameters(parameter)
                .mobile(phoneNumber)
                .templateId(TEMPLATE_ID)
                .build();

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(bodyRequest)))
                .header("Content-Type", "application/json")
                .header("Accept", "text/plain")
                .header("x-api-key", API_KEY)
                .build();

        try {
            HttpResponse<String> resp = client.send(req,
                    HttpResponse.BodyHandlers.ofString());
            ResponseBody responseBody = gson.fromJson(resp.body(), ResponseBody.class);
            return new String[]{randomCode, responseBody.getMessage()};
        } catch (Exception e) {
            return null;
        }
    }


    @Data
    @Builder
    public static class BodyRequest {
        private String mobile;
        private Integer templateId;
        private Parameter[] parameters;
    }

    @Builder
    public static class Parameter {
        private String name;
        private String value;

        public Parameter(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    @Data
    @Builder
    public static class ResponseBody {
        private Integer status;
        private String message;
    }
}