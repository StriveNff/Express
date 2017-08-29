package com.niuff.common.http;


/**
 * description: 通用转换器接口
 * author: linqiang
 * date:2017/6/19   19:45
 */

public interface Deserializer {
	Object display(Object response);
}
