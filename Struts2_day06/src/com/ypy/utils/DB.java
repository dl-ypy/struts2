package com.ypy.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * date:2017��8��24��16:43:41
 * @author ypy
 * target:����jdbc��ʵ��DML��DQL���ķ�װ����������ɾ���� �� ���ܲ�ѯ��
 */
public class DB {
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static final String SQL_START = "select t1.* from (";
	private static final String SQL_END = ") t1 where num>?";
	
	/**
	 * �������ݿ�
	 * @return
	 */
	public static Connection getConnection() {
		try {
			DataSource  dataSource = DataSourceConfig.getDataSource();
			conn = dataSource.getConnection();
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �ر����ݿ⣨DML��
	 * @param conn
	 * @param ps
	 */
	public static void closeDriver(Connection conn, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * �ر����ݿ⣨DQL��
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void closeDriver(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * DML����װ
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int DML(String sql, Object...params) {
		int n = 0;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			if (params.length != 0) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			n = ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDriver(conn, ps);
		}
		return -1;
	}
	
	/**
	 * ���ܲ�ѯ(DQL����װ)
	 * @param sql sql���
	 * @param cls ʵ�����class
	 * @param params SQL����еĲ�������Σ�
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> selectAll(String sql, @SuppressWarnings("rawtypes") Class cls, Object...params) {
		List<Object> list = new ArrayList<>();
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			if (params.length != 0) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			rs = ps.executeQuery();
			//��ȡ����
			int columnCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				//�½�����
				Object instance = cls.newInstance();
				for (int i=1; i<=columnCount; i++) {
					//�õ�����
					Object obj = rs.getObject(i);
					//�жϵõ��������Ƿ�Ϊ��
					if (obj == null) {
						continue;
					}
					//�õ��ֶ���������ת��ΪСд
					String columnName = rs.getMetaData().getColumnName(i).toLowerCase();
					//�ж��Ƿ��и�����
					if (hasField(cls, columnName)) {
						//��������
						Field f = cls.getDeclaredField(columnName);
						//����Ȩ��
						f.setAccessible(true);
						if (rs.getMetaData().getColumnTypeName(i).equals("NUMBER")) {
							BigDecimal val = (BigDecimal) obj;
							if (f.getType().getName().equals("int")) {
								f.set(instance, val.intValue());
							} else {
								f.set(instance, val.doubleValue());
							}
						} else {
							//���õ����ݿ���������õ���Ӧ�����������
							f.set(instance, obj);
						}
					}
				}
				list.add(instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDriver(conn, ps, rs);
		}
		return (List<T>) list;
	}

	/**
	 * �ж�cls�����Ƿ�������columnName
	 * @param cls
	 * @param columnName
	 * @return
	 */
	private static boolean hasField(@SuppressWarnings("rawtypes") Class cls, String columnName) {
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equals(columnName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ��ѯ�ܼ�¼��
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int queryForCount(String sql, Object...params) {
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			if (params.length != 0) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);//�����ܼ�¼��
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDriver(conn, ps, rs);
		}
		return 0;
	}
	
	/**
	 * ��ҳ��ѯ
	 * @param otherSql
	 * @param countSql
	 * @param whereSql
	 * @param querySql
	 * @param currentPage
	 * @param pageSize
	 * @param clas
	 * @param params
	 * @return
	 */
	/*@SuppressWarnings("unchecked")
	public static <T>Page<T> queryPage(StringBuffer otherSql, StringBuffer countSql, StringBuffer whereSql, StringBuffer querySql, int currentPage, int pageSize, Class<T> clas, @SuppressWarnings("rawtypes") List params) {
		//����ܼ�¼��
		String sql = countSql.append(whereSql).toString();
		int totalCount = queryForCount(sql, params.toArray());
		//������ҳ��
		int pageCount = (totalCount-1)/pageSize+1;
		//���ÿҳ��ʼ������½ű�
		int startIndex = pageSize*(currentPage-1);
		int endIndex = pageSize*currentPage;
		params.add(0, endIndex);
		params.add(startIndex);
		if (currentPage == pageCount) {
			endIndex = totalCount;
		}
		//ƴ�ӷ�ҳ��ѯ�����
		sql = querySql.append(whereSql).append(otherSql).insert(0, SQL_START).append(SQL_END).toString();
		System.out.println(sql);
		List<T> list = selectAll(sql, clas, params.toArray());
		return new Page<T>(pageSize, currentPage, totalCount, pageCount, startIndex, endIndex, list);
	}*/
}
