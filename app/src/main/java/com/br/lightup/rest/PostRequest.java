package com.br.lightup.rest;

import android.os.AsyncTask;
import android.util.Log;

import com.br.lightup.interfaces.OnTaskCompleted;

import org.apache.commons.io.IOUtils;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostRequest extends AsyncTask<String, Void, String> {

    private OnTaskCompleted onTaskCompleted;
    private String mensagem;

    public PostRequest(OnTaskCompleted task) {

        this.onTaskCompleted = task;
    }

    @Override
    protected String doInBackground(String... strings) {

        InputStream dataInputStream = null;
        String retorno = "";

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();

            request.setRequestMethod("POST");
            request.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            //connection.setRequestProperty("Accept", "*/*");

            request.setDoOutput(true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(request.getOutputStream()));

            if (strings.length > 1) {

                writer.write(strings[1]);
                writer.close();
            }

            request.connect();

            int responseCode = 0;

            responseCode = request.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                dataInputStream = request.getInputStream();

                StringWriter Swriter = new StringWriter();
                IOUtils.copy(dataInputStream, Swriter, "UTF-8");

                mensagem = Swriter.toString();

                dataInputStream.close();
                Swriter.close();

            } else {

                Log.i(null, "Erro na requisição POST: " + url + " Erro: " + responseCode);

                mensagem = "Ocorreu um erro";
            }

        } catch (Exception e) {
            Log.e(e.toString(), "Deu ruim");
        }

        return null;
    }

    protected void onPostExecute(String mensagem) {

        onTaskCompleted.callBack(this.mensagem);
    }

}
