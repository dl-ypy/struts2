package com.ypy.mystruts.utils;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ypy.mystruts.model.ActionModel;
import com.ypy.mystruts.model.ResultModel;

public class ActionMappingManager {
	private Map<String, ActionModel> actionMap = new HashMap<String, ActionModel>();
	
	public void init() {
		//��ȡSAXReaderʵ��
		SAXReader reader = new SAXReader();
		//��ȡ�ļ�
		URL url = ActionMappingManager.class.getResource("/struts.xml");
		File file = new File(url.getPath());
		//��ȡdocument�ĵ�����
		try {
			Document document = reader.read(file);
			//��ȡ���ڵ�
			Element root = document.getRootElement();
			//��ȡ�ӽڵ�
			List<Element> actionNodes = root.elements();
			//����action�ڵ�
			for (Element actionEle : actionNodes) {
				String actionName = actionEle.attributeValue("name");
				String classes = actionEle.attributeValue("class");
				String method = actionEle.attributeValue("method");
				List<Element> resultNodes = actionEle.elements();
				Map<String, ResultModel> resultMap = new HashMap<String, ResultModel>();
				//����result�ڵ�
				for (Element resultEle : resultNodes) {
					String resultName = resultEle.attributeValue("name");
					String type = resultEle.attributeValue("type");
					String path = resultEle.getText();
					ResultModel result = new ResultModel(resultName, type, path);
					resultMap.put(resultName, result);       //��reslut�ڵ����Map
				}
				ActionModel action = new ActionModel(actionName, classes, method, resultMap);
				actionMap.put(actionName, action);     //��action�ڵ����Map
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	//����keyֵ����һ����ƥ���actionmodel
	public ActionModel getActionModel(String name) {
		return actionMap.get(name);
	}
}
