package br.com.nicemc.proxy.storage.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoHandler {

    @Getter
    private MongoClient mongoClient;

    @Getter
    private MongoDatabase mongoDatabase;

    private String collectionName;

    public MongoHandler(String collectionName) {
        this.collectionName = collectionName;
    }

    public void create() {
        mongoClient = new MongoClient("localhost");
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoDatabase = mongoClient.getDatabase(collectionName).withCodecRegistry(pojoCodecRegistry);
    }

    public <T> MongoCollection<T> getCollection(String collection, Class<T> clazz){
        return mongoDatabase.getCollection(collection, clazz);
    }

    public void close() {
        mongoClient.close();
    }


}
