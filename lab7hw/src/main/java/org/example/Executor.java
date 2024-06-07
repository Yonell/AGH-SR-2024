package org.example;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Executor implements Watcher, DataMonitorListener {
    ZooKeeper zk;
    DataMonitor dm;
    String filename;
    int number_of_children_change = 0;
    public static void main(String[] args) {
        try {

            new Executor(args[0]).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    Executor(String filename) throws IOException {
        zk = new ZooKeeper("127.0.0.1:2181", 3000, this);
        dm = new DataMonitor(zk, "/a", this);
        this.filename = filename;
    }

    @Override
    public void process(WatchedEvent event) {
        dm.process(event);
    }

    private void recursive_ls(String path) throws InterruptedException, KeeperException {
        System.out.println("ls: " + path);
        final boolean[] finished = {false};
        List<String> lista = zk.getChildren(path, true);
        for (String s : lista) {
            if (number_of_children_change > 0) {
                recursive_ls(path + "/" + s);
            }
        }
    }

    void run() throws InterruptedException, KeeperException {
        Scanner scanner = new Scanner(System.in);

        while (!dm.dead) {
            // read line with buffered reader
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            if (input.equals("ls")) {
                recursive_ls("/a");
            }
        }
    }

    @Override
    public void state_change(boolean exists) {
        System.out.println("state_change: " + exists);
        if (exists){
            number_of_children_change = 0;
        }
    }

    @Override
    public void number_of_children_change(int number_of_children) {
        System.out.println("number_of_children_change: " + number_of_children);
        number_of_children_change = number_of_children;
    }
}