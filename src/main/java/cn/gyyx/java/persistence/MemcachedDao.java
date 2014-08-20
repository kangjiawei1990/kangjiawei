package cn.gyyx.java.persistence;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import com.eaio.uuid.UUID;
import cn.gyyx.java.entity.*;

public class MemcachedDao {
	private static MemcachedClient uniqueInstance = MemcachedClientFactory.createClient();  
	    
    
	
	public Boolean SetInfo(ServerInfo model)
	{
		try {
			Object value = uniqueInstance.get(model.getCode()+"");
			if (value != null) {
				uniqueInstance.delete(model.getCode()+"");
			}
			uniqueInstance.set(model.getCode()+"", 20, model.getName());
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public String GetInfo(String code)
	{
		try {
			Object value = uniqueInstance.get(code);
					
			return value.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

}
