package cn.gyyx.java.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import org.apache.ibatis.session.SqlSession;

import com.eaio.uuid.UUID;

import cn.gyyx.java.entity.GameInfo;
import cn.gyyx.java.entity.User;

public class UserServiceDao extends BaseDao {
	/*
	private SqlSession session = null;

	// 验证用户名和密码是否正确
	public boolean validLogin(String userName, String passWord) {
		String smtpId = "UserInfo.ValidLogin";
		List<User> list = null;
		try {
			Map params = new HashMap();
			params.put("userName", userName);
			params.put("passWord", passWord);
			session = sessionFactory.openSession();
			list = session.selectList(smtpId, params);
			session.close();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}
		if (list == null || list.size() == 0)
			return false;
		else {
			// 写入缓存信息
			return true;
		}
	}*/
}
