ģ���struts���

˼·��
���������ļ�struts.xml������Ӧ�Ľڵ�����ActionModel��ResultModel��
��������->Servlet��
��Servlet�н�ȡ������Ϣ��
ͨ������ActionMappingManager�еķ������������ļ�struts.xml�е���Ϣ��
���һ��ActionModel����ͨ������ִ�ж�Ӧ��action�еĶ�Ӧ�������õ�Ҫ��ת��·����
����ҳ����ת��

�����
file->emport->java->Runnable jar file��

ʹ�ã�
����jar�����룬��дstruts.xml����Ӧaction��jsp�ļ���
����web.xml�ļ�:
	  <servlet>
	  	<servlet-name>mystruts</servlet-name>
	  	<servlet-class>com.ypy.mystruts.servlet.ControllerServlet</servlet-class>
	  </servlet>
	  <servlet-mapping>
	  	<servlet-name>mystruts</servlet-name>
	  	<url-pattern>/mvc/*</url-pattern>
	  </servlet-mapping>

ִ�У�
http://localhost:�˿ں�/��Ŀ��/mvc/action��name����