package Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import Enum.Week;
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomerAnnotation {
	//���������value
	String value(); //��ʹ��ע��ʱlvaue=��ʡ�ԣ��������������
	//�ַ������� 
	String StrName() default "customerAnnotation"; //Ĭ��ֵ��ʹ��ע��ʱ���Բ������ø�����
	//��������(ֻ����ԭʼ����,String,Class���⼸��)
	int [] members() default {1,2,3};
	Week myWeek() default Week.MON;
	// java ������
	Class myClass() default String.class;
	//Ԫע��(ע���ע��)����
  MetaAnnotation customerMetaAnnotation() default @MetaAnnotation ("metaAnnotation");
}
