package org.example;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

public class DataMonitor implements Watcher, AsyncCallback.StatCallback {
    public boolean dead;
    ZooKeeper zk;
    String znode;
    DataMonitorListener executor;
    boolean prevExists;
    int prevChildren;

    public DataMonitor(ZooKeeper zk, String znode, DataMonitorListener executor) {
        this.zk = zk;
        this.znode = znode;
        this.executor = executor;
        zk.exists(znode, true, this, null);
        this.prevExists = false;
        this.dead = false;
    }

    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        boolean exists;
        switch (rc) {
            case KeeperException.Code.Ok:
                exists = true;
                break;
            case KeeperException.Code.NoNode:
                exists = false;
                break;
            case KeeperException.Code.SessionExpired:
            case KeeperException.Code.NoAuth:
                dead = true;
                return;
            default:
                zk.exists(znode, true, this, null);
                return;
        }

        if(exists != prevExists) {
            executor.state_change(exists);
            prevExists = exists;
        }
        if(exists) {
            zk.getChildren(znode, true, new AsyncCallback.Children2Callback() {
                @Override
                public void processResult(int rc, String path, Object ctx, java.util.List<String> children, Stat stat) {
                    if(rc == KeeperException.Code.Ok) {
                        executor.number_of_children_change(children.size());
                    }
                }
            }, null);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        String path = event.getPath();
        if (event.getType() == Event.EventType.None) {
            switch (event.getState()) {
                case SyncConnected:
                    break;
                case Expired:
                    dead = true;
                    break;
            }
        } else {
            if (path != null && path.equals(znode)) {
                zk.exists(znode, true, this, null);
            }
        }
    }
}
