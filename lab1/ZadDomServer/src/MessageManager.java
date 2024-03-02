import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageManager extends Thread {
    private final Queue<Message> newMessages = new ArrayDeque<>();
    private final List<NewMessageObserver> newMessageObservers = new ArrayList<>();
    private final Lock newMessageObserversLock = new ReentrantLock(true);
    private final Lock messageQueueLock = new ReentrantLock(true);
    private final Condition waitForNewMessages = messageQueueLock.newCondition();
    public MessageManager(){

    }

    public void addToNewMessageObservers(NewMessageObserver newOne){
        newMessageObserversLock.lock();
            newMessageObservers.add(newOne);
        newMessageObserversLock.unlock();
    }

    public void addMessage(Message newOne){
        messageQueueLock.lock();
            newMessages.add(newOne);
            waitForNewMessages.signal();
        messageQueueLock.unlock();
        System.out.println("Client " + newOne.getAuthorId() + " sent a message: " + new String(newOne.getBytes()));

    }

    @Override
    public void run(){
        messageQueueLock.lock();
            while(true) {
                while (newMessages.isEmpty()) {
                    try {
                        waitForNewMessages.await();
                    } catch (InterruptedException ignored) {

                    }
                }
                newMessageObserversLock.lock();
                while(!newMessages.isEmpty()) {
                    Message messageToSend = newMessages.remove();
                    for (NewMessageObserver observer : newMessageObservers) {
                        observer.newMessageNotify(messageToSend);
                    }
                }
                newMessageObserversLock.unlock();
            }

        //messageQueueLock.unlock();
    }

    public void removeObserver(NewMessageObserver toRemove){
        newMessageObserversLock.lock();
        newMessageObservers.remove(toRemove);
        newMessageObserversLock.unlock();
    }
}
