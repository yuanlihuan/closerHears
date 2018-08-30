package cn.com.world.hears.account.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.reflect.Type;

import org.apache.ibatis.reflection.ExceptionUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * 对象转成json
 * @author liulai
 */
public class GsonUtils {
	
	/**
     * gson
     */
    private static Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateAdapter())
            .registerTypeAdapter(Timestamp.class, new TimestampAdapter()).create();
    
    /**
     * 从对象转换成json字符串
     * 
     * @param obj
     *            待转换对象
     * @return String
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }
    
    /**
     * 将json字符串转换为指定类型的对象
     * 
     * @param <T>
     *            对象类型泛型
     * @param json
     *            json字符串
     * @param classOfT
     *            对象类型
     * @return 转换后的对象
     * @throws JsonParseException
     *             jsonparse异常
     */
    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonParseException {
        return gson.fromJson(json, classOfT);
        
    }
    
    /**
     * main
     * 
     * @param args
     *            args
     */
    public static void main(String[] args) {
        
    }
}
/**
 * DateAdapter
 * 
 * @author liulai
 * 
 */
class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    
    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null) {
        	return JsonNull.INSTANCE;
        }
        return new JsonPrimitive(src.getTime());
    }
    
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        if (json.isJsonNull()) {
            return null;
        }
        if (!json.isJsonPrimitive() || !json.getAsJsonPrimitive().isNumber()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                return formatter.parse(json.getAsString());
            } catch (ParseException e) {
                ExceptionUtil.unwrapThrowable(e);
            }
        }
        
        return new Date(json.getAsLong());
    }
}

/**
 * TimestampAdapter
 * 
 * @author liulai
 * 
 */
class TimestampAdapter implements JsonSerializer<Timestamp>, JsonDeserializer<Timestamp> {
    
    @Override
    public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
        if (json.isJsonNull()) {
            return null;
        }
        if (!json.isJsonPrimitive() || !json.getAsJsonPrimitive().isNumber()) {
              return Timestamp.valueOf(json.getAsString());
        }
        return new Timestamp(json.getAsLong());
    }
    
    @Override
    public JsonElement serialize(Timestamp src, Type typeOfSrc, JsonSerializationContext context) {
        if (src == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonPrimitive(src.getTime());
    }
    
}