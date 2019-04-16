import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

public class Test {

    public static void main(String[] args)throws  Exception{


        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000000, new Watcher() {

            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent);
            }
        });
        byte[] bytelock=null;
        try {
//
//            zooKeeper.delete("/dogs",-1);
//
//            zooKeeper.create("/dog", "NONE".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

               bytelock = zooKeeper.getData("/dog/fuck", false, new Stat());

        }catch (Exception e){

            int x = 100;
        }


        if(bytelock ==null){

        }

    }
}
