package com.example.websrcapper.services;

import com.example.websrcapper.entity.MsSetEntity;
import com.example.websrcapper.entity.TrTransactionEntity;
import com.example.websrcapper.model.HistoricalTradingModel;
import com.example.websrcapper.model.ResponseModel;
import com.example.websrcapper.repository.MsSetRepository;
import com.example.websrcapper.repository.TrTransactionRepository;
import com.example.websrcapper.transform.Transform;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class AppService {
    private final MsSetRepository msSetRepository;
    private final TrTransactionRepository trTransactionRepository;

    public AppService(MsSetRepository msSetRepository, TrTransactionRepository trTransactionRepository) {
        this.msSetRepository = msSetRepository;
        this.trTransactionRepository = trTransactionRepository;
    }

    public void insertData(){
        long startTime = System.currentTimeMillis();

        List<MsSetEntity> symbols = findSymbol();
        for (MsSetEntity x: symbols) {
            String json = fetchData(x.getSymbol());
            try {
                HistoricalTradingModel[] historicalTradingModels = jsonToObject(json,HistoricalTradingModel[].class);
                List<TrTransactionEntity> trTransactionEntities = Transform.transformModelToEntityList(historicalTradingModels);
                this.trTransactionRepository.saveAll(trTransactionEntities);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed Time : " + (endTime-startTime) + "ms");
    }
    public List<MsSetEntity> findSymbol() {

        try {
            // find data by symbol
            List<MsSetEntity> data = this.msSetRepository.findAll();
            return  data;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToObject(String jsonString, Class<T> valueType) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, valueType);
    }
    public String fetchData(String symbol){
        try {
            URL obj = new URL("https://www.set.or.th/api/set/stock/"+ symbol +"/historical-trading");
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //set GET methode
            con.setRequestMethod("GET");
            //setHeader
            con.setRequestProperty("Referer",
                    "https://www.set.or.th/th/market/product/stock/quote/" + symbol+"/historical-trading");

            int responseCode = con.getResponseCode();
           // System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                // print result
                //System.out.println(response.toString());
                return response.toString();
            } else {
                System.out.println("GET request did not work.");
                System.out.println("URL : " );
            }
        } catch (Exception e) {
            // TODO: handle exception
            log.info(e.getMessage());
        }
        return null;
    }

    private static String getUrlContents(String theUrl)
    {
        StringBuilder content = new StringBuilder();
        // Use try and catch to avoid the exceptions
        try
        {
            URL url = new URL(theUrl); // creating a url object
            URLConnection urlConnection = url.openConnection(); // creating a urlconnection object

            // wrapping the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // reading from the urlconnection using the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

    private static String setURL(List<MsSetEntity> data){

        StringBuilder url = new StringBuilder();
        String symbol = data.get(0).getSymbol();
        url.append("https://www.set.or.th/th/market/product/stock/quote/") ;
        url.append(symbol);
        url.append("/historical-trading");
        System.out.println("URL : " + url);
        return url.toString();
    }
}

