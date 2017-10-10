package com.ypy.mystruts.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ypy.mystruts.model.ActionModel;
import com.ypy.mystruts.utils.ActionMappingManager;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/mvc/*")    //�������д���mvc/�ķ��ʵ�ַ
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActionMappingManager manager;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * ��ʼ�����������������ļ�
	 */
	public void init(ServletConfig config) throws ServletException {
		manager = new ActionMappingManager();
		manager.init();
	}

	/**
	 * ��ȡ���󣬽�ȡ�����е��ַ������õ�action��method��������ҵ��
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI();
		String actionName = path.substring(path.lastIndexOf("/")+1);   //�����һ��/��ʼ��ȡ
		//��ȡ������
		String[] array = actionName.split("_");
		String methodName = array[1];
		ActionModel action = manager.getActionModel(actionName);
		//��ȡ����ҵ��������·��
		String actionClass = action.getClasses();
		try {
			//�����ಢʵ��������
			Object obj = Class.forName(actionClass).newInstance();
			//��ȡ���崦������ķ���
			Method mothod = obj.getClass().getMethod(methodName);
			//ִ�з������õ����ص�result��name
			String res = (String) mothod.invoke(obj);
			//���ݷ��ص�result��name�õ�result�ķ���·��
			String resPath = action.getResultMap().get(res).getPath();
			//��ת����
			request.getRequestDispatcher(resPath).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
