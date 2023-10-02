import org.apache.http.util.EntityUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiTest {

    public static void main(String[] args) {
        String url = "https://www.google.com";

        // Создание HTTP клиента
        HttpClient httpClient = HttpClientBuilder.create().build();

        // Создание GET запроса
        HttpGet request = new HttpGet(url);

        try {
            // Выполнение запроса
            HttpResponse response = httpClient.execute(request);

            // Проверка кода состояния
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("Status Code: " + statusCode);

            // Проверка заголовков
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                System.out.println(header.getName() + ": " + header.getValue());
            }

            // Проверка содержимого ответа
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response Body: " + responseBody);

            // Проверка времени ответа
            String dateString = response.getFirstHeader("Date").getValue();
            DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
            Date responseTime = dateFormat.parse(dateString);
            Date requestTime = dateFormat.parse(request.getFirstHeader("Date").getValue());
            long elapsedTime = responseTime.getTime() - requestTime.getTime();
            System.out.println("Response Time: " + elapsedTime + " ms");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}