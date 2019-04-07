import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class JedisClusterTest {

	@Test
	public void test() throws IOException {
		// 设置集群节点
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.1.105", 7001));
		nodes.add(new HostAndPort("192.168.1.105", 7002));
		nodes.add(new HostAndPort("192.168.1.105", 7003));
		nodes.add(new HostAndPort("192.168.1.105", 7004));
		nodes.add(new HostAndPort("192.168.1.105", 7005));
		nodes.add(new HostAndPort("192.168.1.105", 7006));

		// 创建jedis集群对象
		JedisCluster jedisCluster = new JedisCluster(nodes);

		// 设置值
		jedisCluster.set("jedisCluster_msg", "redis-集群");

		// 获取键值
		String msg = jedisCluster.get("jedisCluster_msg");
		System.out.println(msg);

		// 关闭jedisCluster连接(在程序执行完之后,才能关闭,他的内部已经封装了连接池)
		jedisCluster.close();
	}

}