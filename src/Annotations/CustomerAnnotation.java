package Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import Enum.Week;
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerAnnotation {
	//特殊的类型value
	String value(); //在使用注解时lvaue=可省略，这是这种特殊的
	//字符串类型 
	String StrName() default "customerAnnotation"; //默认值在使用注解时可以不用设置该属性
	//数组类型(只能是原始类型,String,Class等这几种)
	int [] members() default {1,2,3};
	Week myWeek() default Week.MON;
	// java 类类型
	Class myClass() default String.class;
	//元注解(注解的注解)类型
  MetaAnnotation customerMetaAnnotation() default @MetaAnnotation ("metaAnnotation");
}
