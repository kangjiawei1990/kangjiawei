package cn.gyyx.java.persistence;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
public class MemcachedClientFactory {
	private static MemcachedClient memcachedClient;  
	  
	public static MemcachedClient createClient()
	{  
	if(memcachedClient==null)
	{  
		try {
			memcachedClient= new XMemcachedClient("192.168.6.195",10001); 
	    	} 
		catch (Exception ex) 
		{
	    			ex.printStackTrace();
	    }
		 
	}  
	    return memcachedClient;  
	}  
}
