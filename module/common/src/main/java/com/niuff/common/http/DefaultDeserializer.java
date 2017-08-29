package com.niuff.common.http;


import java.util.HashMap;
import java.util.List;

/**
 * description:转换器实现
 * author: linqiang
 * date:2017/6/19   19:49
 */

public class DefaultDeserializer implements Deserializer {
	@Override
	public List<HashMap<String, String>> display(Object response) {
		/*try{
			ModelData data= (ModelData) response.data;
			List<List<LinkedTreeMap<String,Object>>> list=data.getRowList();
			List<HashMap<String,String>> result =new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				HashMap<String,String> hashMap =new HashMap<>();
				for (int i1 = 0; i1 < list.get(i).size(); i1++) {
					LinkedTreeMap<String,Object> link=list.get(i).get(i1);
					try{
						hashMap.put(link.get("name").toString(),link.get("value").toString());
					}catch (Exception e){
						e.printStackTrace();
						hashMap.put(link.get("name").toString(),"");
					}

				}
				result.add(hashMap);
			}
			return result;
		}catch (Exception e){
			e.printStackTrace();
			return new ArrayList<HashMap<String, String>>();
		}*/
		return (List<HashMap<String, String>>) response;
	}

}
