package util;

import java.io.BufferedReader;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;

public class ServicosHttp {

    public static String getPost(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
    
    public static JSONObject getPostJson(HttpServletRequest request){
        String post = getPost(request);
        JSONObject jObj = new JSONObject(post);
        return jObj;
    }

    public static void listPropsJson(String post) {
        JSONObject jObj = new JSONObject(post);
        Iterator<String> it = jObj.keys();

        while (it.hasNext()) {
            String key = it.next();
            Object o = jObj.get(key);
            System.out.println(key + " : " + o);
        }
    }

}
