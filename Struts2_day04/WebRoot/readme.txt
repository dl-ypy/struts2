获取web对象的方式：
1.非IOC:
	直接方式:通过ServletActionContext
	间接方式:通过ActionContext
	       (放入Map集合中):只获取数据时使用，因为本质是Map集合。
2.IOC（控制反转）:实现相关接口，如：ServletRequestAware,ServletResponseAware