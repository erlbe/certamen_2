package cl.telematica.android.certamen2.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Erlend on 30.09.2016.
 */

public class HttpServerConnection {

    public String connectToServer(String myUrl, int timeOut) {
        try {
            URL url = new URL(myUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(timeOut);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();

            InputStream is = conn.getInputStream();
            return readInputStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * @param is
     * @return String of JSON object in the InputStream is.
     * @throws IOException
     */
    private String readInputStream(InputStream is) throws IOException {
        Reader reader = null;
        StringBuilder inputStringBuilder = new StringBuilder();

        reader = new InputStreamReader(is, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = bufferedReader.readLine();
        while(line != null){
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }

        return inputStringBuilder.toString();
    }
}
