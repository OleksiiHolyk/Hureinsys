package ua.com.hureinsys.dao.user;

import com.mongodb.WriteResult;
import ua.com.hureinsys.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.hureinsys.util.EntityPage;
import ua.com.hureinsys.util.FilterOptions;
import ua.com.hureinsys.util.MongoTemplateOperations;

import javax.annotation.PostConstruct;

/**
 * Created by Oleksii on 22.12.2015.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.createCollection(User.class);
        }
    }

    @Override
    public void createUser(User user) {
        if (!mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.createCollection(User.class);
        }
        mongoTemplate.insert(user);
    }

    @Override
    public User findUserById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User findUserAndUpdate(User user) {
        return MongoTemplateOperations.updateFieldsAndReturnUpdatedObj(user);
    }

/*    @Override
    public void updateUser(User user) {
        mongoTemplate.save(user);
    }*/

    @Override
    public int deleteUserById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        WriteResult result = mongoTemplate.remove(query, User.class);
        return result.getN();
    }

    @Override
    public boolean userByIdExists(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.exists(query, User.class);
    }

    @Override
    public boolean userByEmailExists(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.exists(query, User.class);
    }

    @Override
    public EntityPage<User> findAllUsers(FilterOptions filterOptions) {
        Query query = new Query();
        query.skip(filterOptions.getSkip());
        query.limit(filterOptions.getLimit());
        return new EntityPage<>(mongoTemplate.count(query, User.class),
                mongoTemplate.find(query, User.class));
    }

    @Override
    public User findUserByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User findUserByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, User.class);
    }
}
