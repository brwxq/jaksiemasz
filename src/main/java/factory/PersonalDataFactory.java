package factory;


import model.PersonalData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonalDataFactory {

    private final Logger LOGGER = Logger.getLogger(PersonalDataFactory.class.getName());

    public List<PersonalData> generate(int limit) throws IOException {

        //nie znalazłem URIBuildera który generuje parametry po przecinku
        URL url = new URL("https://www.randomuser.me/api/?inc=gender,name,nat,email,phone&results=" + Integer.toString(limit));

        String httpRequest = makeHttpRequest(url);

        List<JSONObject> jsons = parseToJSON(httpRequest);

        return createPersonalDataList(jsons);
    }

    private List<PersonalData> createPersonalDataList(List<JSONObject> jsons) {
        Random random = new Random();
        List<String> academys = Arrays.asList("AGH","UJ","UW","SGH");
        List<PersonalData> personalDataList = new LinkedList<>();

        for(JSONObject jo : jsons){

            JSONObject name = (JSONObject)jo.get("name");

            PersonalData data = PersonalData.builder()
                    .name((String)name.get("first"))
                    .surname((String)name.get("last"))
                    .email((String)jo.get("email"))
                    .gender(PersonalData.Gender.valueOf(((String)jo.get("gender")).toUpperCase()))
                    .nationality((String)jo.get("nat"))
                    .phone((String)jo.get("phone"))
                    .academy(academys.get(random.nextInt(academys.size())))
                    .build();

            personalDataList.add(data);
        }
        return personalDataList;
    }


    private List<JSONObject> parseToJSON(String httpRequest) {
        JSONParser parser = new JSONParser();
        List<JSONObject> people = new LinkedList<>();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(httpRequest);
            JSONArray results = (JSONArray)jsonObject.get("results");

            for (Object result : results) {
                people.add((JSONObject) result);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return people;
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url==null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if (urlConnection.getResponseCode()==200){
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                LOGGER.log(Level.SEVERE,"Error response code:" + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE,"Problem retrieving JSON results",e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}