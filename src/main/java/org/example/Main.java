package org.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) {
        String apiKey = "033f19189a8ab629d8ac47ab098e86ef"; // Replace with your API key
        String city = "Dehli";        // Replace with your city
        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

        try {
            // Connect to the API
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonObject main = jsonResponse.getAsJsonObject("main");
            double temperature = main.get("temp").getAsDouble() - 273.15; // Convert Kelvin to Celsius

            System.out.println("Weather in " + city + ":");
            System.out.println("Temperature: " + String.format("%.2f", temperature) + "°C");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
