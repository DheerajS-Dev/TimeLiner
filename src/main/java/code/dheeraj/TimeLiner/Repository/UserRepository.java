package code.dheeraj.TimeLiner.Repository;

import code.dheeraj.TimeLiner.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String name);

    Long deleteByUserName(String name);

}
