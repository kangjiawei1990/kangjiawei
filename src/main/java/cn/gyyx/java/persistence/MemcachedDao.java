package cn.gyyx.java.persistence;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;

import com.eaio.uuid.UUID;
import cn.gyyx.java.entity.*;

public class MemcachedDao {
	private static MemcachedDao uniqueInstance = null;  
	   
    private MemcachedDao() {  
       // Exists only to defeat instantiation.  
    }  
   
    public static MemcachedDao getInstance() {  
       if (uniqueInstance == null) {  
           uniqueInstance = new MemcachedDao();  
       }  
       return uniqueInstance;  
    }  
	
	public Boolean SetInfo(ServerInfo model)
	{
		try {
			//MemcachedClientBuilder builder = new XMemcachedClientBuilder(
			//		AddrUtil.getAddresses("localhost:11211"));
			//MemcachedClient client = builder.build();
			MemcachedClient client=new XMemcachedClient("192.168.6.195",10001);
			Object value = client.get(model.getCode()+"");
			if (value != null) {
				client.delete(model.getCode()+"");
			}
			client.set(model.getCode()+"", 20, model.getName());
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	public String GetInfo(String code)
	{
		try {
			//MemcachedClientBuilder builder = new XMemcachedClientBuilder(
			//		AddrUtil.getAddresses("localhost:11211"));
			//MemcachedClient client = builder.build();
			MemcachedClient client=new XMemcachedClient("192.168.6.195",10001);
			Object value = client.get(code);
			
			
			return value.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

}
