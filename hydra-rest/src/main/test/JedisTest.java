import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Jedis连接测试
 *
 * @author 許彬.
 * @creater 2016-08-22 12:26
 */

public class JedisTest {

    @Test
    public void test() {
        Jedis jedis = new Jedis("192.168.202.121", 6379);

        jedis.set("a", "100");
        String a = jedis.get("a");
        System.out.println(a);

        jedis.close();

    }

    @Test
    public void test2() {
        JedisPool jedisPool = new JedisPool("192.168.202.121", 6379);

        Jedis jedis = jedisPool.getResource();

        String a = jedis.get("a");

        System.out.println(a);

        jedis.close();
    }


    @Test
    public void test3() {
        Set<HostAndPort> nodes = new HashSet<>();

        nodes.add(new HostAndPort("192.168.202.121", 7001));
        nodes.add(new HostAndPort("192.168.202.121", 7002));
        nodes.add(new HostAndPort("192.168.202.121", 7003));
        nodes.add(new HostAndPort("192.168.202.121", 7004));
        nodes.add(new HostAndPort("192.168.202.121", 7005));
        nodes.add(new HostAndPort("192.168.202.121", 7006));

        JedisCluster jedisCluster = new JedisCluster(nodes);

        jedisCluster.set("b", "200");

        String b = jedisCluster.get("b");

        System.out.println(b);
    }
}
