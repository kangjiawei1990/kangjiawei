package cn.gyyx.java.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import cn.gyyx.java.entity.ServerInfo;
import cn.gyyx.java.entity.User;

public class ServerDao extends BaseDao {
	private SqlSession session = null;

	public List<ServerInfo> queryListByGameId(int gameId) {
		String smtpId = "ServerInfo.queryListByGameId";
		List<ServerInfo> list = null;
		try {
			session = sessionFactory.openSession();
			list = session.selectList(smtpId, gameId);
			session.close();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ServerInfo> queryListById(int serverId) {
		String smtpId = "ServerInfo.queryById";
		List<ServerInfo> list = null;
		try {
			session = sessionFactory.openSession();
			list = session.selectList(smtpId, serverId);
			session.close();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}
		return list;
	}
	
	public void UpdateServerById(String serverName) {
		
		String smtpId = "ServerInfo.updateById";
		try {
			Map params = new HashMap();
			params.put("code", "1");
			params.put("serverName", serverName);
			session = sessionFactory.openSession();
			session.update(smtpId, params);
			session.commit();
			session.close();
			ServerInfo model=new ServerInfo();
			model.setCode(1);
			model.setName(serverName);
			MemcachedDao mem=new MemcachedDao();
			mem.SetInfo(model);
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}
		
	}
}
