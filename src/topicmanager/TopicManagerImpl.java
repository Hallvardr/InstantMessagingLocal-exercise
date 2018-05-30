package topicmanager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import publisher.Publisher;
import publisher.PublisherAdmin;
import publisher.PublisherImpl;
import subscriber.Subscriber;

public class TopicManagerImpl implements TopicManager {

    private Map<String,PublisherAdmin> topicMap;

    public TopicManagerImpl() {

        topicMap = new HashMap<String,PublisherAdmin>();
    }
    
    public boolean isTopic(String topic){

        return this.topicMap.containsKey(topic);
    }
    
    public Set<String> topics(){

        return this.topicMap.keySet();
    }
    
    public Publisher addPublisherToTopic(String topic){


        if (topicMap.containsKey(topic)) {
            topicMap.get(topic).incPublishers();
            return topicMap.get(topic);
        } else {
            topicMap.put(topic, new PublisherImpl(topic));
            return new PublisherImpl (topic);
        }
    }
    
    public int removePublisherFromTopic(String topic){

        PublisherImpl publisher = new PublisherImpl(topic);
        publisher.incPublishers();
        topicMap.put(topic, publisher);
        return publisher;
    }
    
    public boolean subscribe(String topic, Subscriber subscriber){

        if (topicMap.containsKey(topic)) {

            this.topicMap.get(topic).attachSubscriber(subscriber);
            return true;

        } else {
            return false;
        }
        
    }
    
    public boolean unsubscribe(String topic, Subscriber subscriber){

        if (topicMap.containsKey(topic)) {
            //unsubs = true;
            topicMap.get(topic).detachSubscriber(subscriber);
            return true;
        }
        else{
            return false;
        }
    }
}