��֤���
1.���ʽ��
	(1).����̳�ActionSupport�ࡣ
	(2).����ʵ��validate������
	(3).Ҫ��struts.xml�ļ������һ��name="input"��result��ǩ������ָ����֤��ͨ���󷵻ص�ҳ�档ע��type����д���ض���
	(4).Ҫ��name="input"��result��ǩ��ת��ҳ��д��struts��ǩ��<s:actionerror/>���ܴ�����Ϣ��
	���ԣ�http://localhost:8686/Struts2_day03/user/User1_login_login1
2.����ʽ��
	(1).��action���£�����һ���ļ����ļ���Ϊ����Ҫ��֤��action�ļ�-validation.xml��
	         �ļ�����:Դ�ļ�����xwork-validator-1.0.dtd�ļ����ƣ�
	         <!DOCTYPE validators PUBLIC 
	  		"-//Apache Struts//XWork Validator 1.0//EN"
	  		"http://struts.apache.org/dtds/xwork-validator-1.0.dtd">
	  	Ȼ�������¸�ʽд��
	  	<validators>
			<field name="username">   <!-- ָ��Ҫ��֤������ -->
				<field-validator type="requiredstring">
					<message>�û�������Ϊ��</message>
				</field-validator>
				<field-validator type="stringlength">
					<param name="maxLength">6</param>
					<param name="minLength">3</param>
					<message>�ַ���������3-6֮��</message>
				</field-validator>
			</field>
		</validators>
		�ο�Դ�ļ���com/opensymphony/xwork2/validator/validators/default.xml�ļ���
	(2)(3)��������(3)(4)����ͬ��
	���ԣ�http://localhost:8686/Struts2_day03/user/User2_login_login2