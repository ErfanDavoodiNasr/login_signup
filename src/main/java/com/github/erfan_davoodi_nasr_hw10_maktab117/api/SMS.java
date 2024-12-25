package com.github.erfan_davoodi_nasr_hw10_maktab117.api;

import com.google.gson.Gson;
import lombok.Builder;
import lombok.Data;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class SMS {
    private static Random random;
    private static Gson gson = new Gson();

    public static String[] sendSms(String phoneNumber) {
        String randomCode = generateRandomCode();
        try (HttpClient client = HttpClient.newHttpClient()) {

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
    }

    private static synchronized String generateRandomCode() {
        if (random == null) {
            random = new Random();
        }

        return String.format("%06d", random.nextInt(1_000_000));
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