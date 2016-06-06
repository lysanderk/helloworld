package kf.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BasePojo {

	public String toString(){
		return ReflectionToStringBuilder.toString(this,ToStringStyle.SIMPLE_STYLE);
	}
}
