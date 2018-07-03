package SS18.PROG2.FHVerwaltung;
import java.util.List;

public class JsonHelper {
	
	public static <T extends JsonFormat> String getJsonArray(List<T> list){
		String json = "";
		boolean first = true;
		for(T item : list){
			if(!first) json += ",";
			first = false;
			json += String.format("\n%s", item.toJsonFormat());
		}
		return json;
	}
	
	public static String getStringAttribute(String json, String attribute){
		return json.split(attribute + "\":[ ]*\"")[1].split("\"")[0];
	}

	public static int getIntAttribute(String json, String attribute){
		return Integer.parseInt(json.split(attribute + "\":[ ]*")[1].split("[,}]")[0]);
	}
}
