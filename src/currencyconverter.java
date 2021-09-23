import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import java.io.InputStreamReader;

public class currencyconverter {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        HashMap<Integer, String> currencycodes = new HashMap<Integer, String>();
        // to insert your currency codes

        currencycodes.put(1, "INR");
        currencycodes.put(2, "USD");
        currencycodes.put(3, "CAD");
        currencycodes.put(4, "HKD");
        currencycodes.put(5, "EUR");
        currencycodes.put(6, "CNY");
        currencycodes.put(7, "JPY");
        currencycodes.put(8, "KRW");
        currencycodes.put(9, "OMR");
        currencycodes.put(10, "RUB");


        String fromCode, toCode;
        double amount;

        System.out.println("WELCOME TO THE CURRENCY CONVERTER");

        System.out.println("Enter the currency to be converted from : ");
        System.out.println("1:INR \t 2:USD \t 3:CAD \t 4:HKD \t 5:EUR \t 6:CNY \t 7:JPY \t 8:KRW \t 9:OMR \t 10.RUB \t");
        fromCode = currencycodes.get(sc.nextInt());


        System.out.println("Enter the currency to be converted to : ");
        System.out.println("1:INR \t 2:USD \t 3:CAD \t 4:HKD \t 5:EUR \t 6:CNY \t 7:JPY \t 8:KRW \t 9:OMR \t 10.RUB \t");
        toCode = currencycodes.get(sc.nextInt());


        System.out.println("Enter the amount you wish to convert : ");
        amount = sc.nextFloat();

        sendHTTPrequest(fromCode, toCode, amount);

        System.out.println("Thank you for using the currency converter");
    }

        private static void sendHTTPrequest(String fromCode, String toCode, double amount) throws IOException {

            String GET_URL = "http://api.exchangeratesapi.io/v1/latest?access_key=ed6f2b638095f2773a2afc80b9a885dd&base="+toCode+"&symbols="+fromCode+"&format=1";
            URL url = new URL(GET_URL);

            HttpURLConnection httpURLConnection = (java.net.HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){//success
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }in.close();

                JSONObject obj = new JSONObject(response.toString());
                Double exchangerate = obj.getJSONObject("rates").getDouble(fromCode);
                System.out.println(obj.getJSONObject("rates"));
                System.out.println(exchangerate);
                System.out.println();
                System.out.println(amount +" " + fromCode + "=" + amount/exchangerate +" "+ toCode);
            }
            else{
                System.out.println("Invalid");
            }
        }
    }
