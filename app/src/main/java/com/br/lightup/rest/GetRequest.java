package com.br.lightup.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.br.lightup.interfaces.OnTaskCompleted;
import com.br.lightup.util.Constants;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest extends AsyncTask<String, Void, String>{

    private OnTaskCompleted onTaskCompleted;

    public GetRequest(OnTaskCompleted task) {

        this.onTaskCompleted = task;
    }

    @Override
    protected String doInBackground(String... strings) {

        URL urlRequest;
        HttpURLConnection urlConnection = null;
        InputStream dataInputStream = null;
        String retorno = "";

        try {

            urlRequest = new URL(strings[0]);

            HttpURLConnection request = (HttpURLConnection) urlRequest.openConnection();

            //Seto timeout de 3 segundos
            request.setConnectTimeout(Constants.REQUEST_TIMEOUT);
            request.setRequestMethod("GET");

            request.setDoInput(true);
            request.connect();

            int responseCode = 0;

            responseCode = request.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                dataInputStream = request.getInputStream();

                StringWriter writer = new StringWriter();
                IOUtils.copy(dataInputStream, writer, "UTF-8");

                retorno = writer.toString();

                dataInputStream.close();
                writer.close();

            } else {

                Log.i(null, "Erro na requisição GET " + responseCode);
                throw new Exception("Erro. " + responseCode);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return retorno;
    }

    protected void onPostExecute(String result) {

        onTaskCompleted.callBack(result);
    }
}
