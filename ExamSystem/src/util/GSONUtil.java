package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class GSONUtil {
      //创建一个json (gson) 对象
      private static Gson gson = new Gson();

      public GSONUtil(){
      }

      /**
       * 得到json
       */
      public static String getJson(HttpServletRequest request) throws IOException {
            StringBuilder stringBuilder = null;
            String jsonString = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            stringBuilder = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null) {
                  stringBuilder.append(str);
            }
            //获取到了json
            jsonString = stringBuilder.toString();
            reader.close();
            return jsonString;
      }


      /**
       * 将object对象转成json字符串
       * @param object
       * @retrun
       */
       public static String GsonToString(Object object){
            String gsonString = null;
             if(gson != null){
                   gsonString = gson.toJson(object);
             }
             return gsonString;
       }

      /**
       *将gsonString转成泛型bean
       */
      public  static <T> T GsonToBean(String gsonString, Class<T> cls){
            T t = null;
            if (gson != null){
                  t = gson.fromJson(gsonString,cls);
            }
            return t;
      }

      /**
       * 将json字符串转成list
       */
      public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
            List<T> list = null;
            if (gson != null) {
                  list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
                  }.getType());
            }
            return list;
      }

      /**
       * 将json字符串转成map
       */
      public static <T> Map<String, T> GsonToMap(String gsonString){
            Map<String, T> map = null;
            if (gson != null){
                  map = gson.fromJson(gsonString,new TypeToken<Map<String, T>>(){}.getType());
            }
            return map;
      }

      /**
       * 将对象转成json
       */
      public static String ObjToJson(Object object){
            return gson.toJson(object);
      }

}
