����struts2

1.����jar����

2.����struts.xml�ļ���
  ��1��. Web App Libraries->struts2-core�е�struts-default���ҵ�����dtd�ļ�����䣺
	  <!DOCTYPE struts PUBLIC
	    "-//Apache Software Foundation//DTD Struts Configuration 2.5//EN"
	    "http://struts.apache.org/dtds/struts-2.5.dtd">
	    ���Ƶ�struts.xml�У�������������ļ������õı�ǩ��
  ��2��. ͨ������Web App Libraries->struts2-core->org.apache.struts2->default.properties
              �ļ�����struts.xml��ͨ��<constant name="" value="true"></constant>���Կ������������ԣ����޸�xml�ļ����Զ�������
  ��3��. ��дaction�ļ�
  ��4��. ����web.xml:
       <filter>
	  	 <filter-name>struts2</filter-name>
	  	 <filter-class>org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter</filter-class>
	   </filter>
	   <filter-mapping>
	  	 <filter-name>struts2</filter-name>
	  	 <url-pattern>/*</url-pattern>
	   </filter-mapping>  
	   ����<filter-class>��·���ǣ�Web App Libraries->struts2-core->org.apache.struts2.dispatcher.filter->StrutsPrepareAndExecuteFilter��·����

3.���ԣ�http://localhost:8686/Struts2_day01/mc/mc_save