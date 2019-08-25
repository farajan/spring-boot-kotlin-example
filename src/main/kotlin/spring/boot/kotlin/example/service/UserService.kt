package spring.boot.kotlin.example.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import spring.boot.kotlin.example.db.entity.User
import spring.boot.kotlin.example.db.mapper.UserMapper
import spring.boot.kotlin.example.payload.TransferCar

@Service
@Transactional
class UserService(@Autowired private val userMapper: UserMapper) {

    @Cacheable("userCache")
    fun getAll(): List<User> {
        print("finding...")
        return userMapper.findAll()
    }

    @Cacheable("userCache.byId", key = "#id")
    fun getById(id: Long): User = userMapper.findById(id) ?: throw IllegalArgumentException("User with id_user=$id doesn't exists.")

    @CacheEvict("userCache", allEntries = true)
    fun create(user: User): User {
        if(user.firstName == null
                || user.lastName == null
                || user.email == null
                || user.password == null
        ) throw IllegalArgumentException("Some of following mandatory parameters are null, see: " +
                "firstName=${user.firstName}, " +
                "lastName=${user.lastName}, " +
                "email=${user.email}, " +
                "password=${user.password}, " +
                "birthday=${user.birthday}")
        if(alreadyExists(user)) throw IllegalArgumentException("User with this email already exists.")
        userMapper.create(user)
        return user
    }

    @Caching(evict = [
        CacheEvict("userCache", allEntries = true),
        CacheEvict("userCache.byId", key = "#user.id_user"),
        CacheEvict("carCache", allEntries = true),
        CacheEvict("carCache.byId", key = "#car.id_car"),
        CacheEvict("carCache.freeCars", allEntries = true)])
    fun update(user: User): User {
        if(user.id_user == null) throw IllegalArgumentException("id_user can't be null.")
        userMapper.update(user)
        return getById(user.id_user)
    }

    @Caching(evict = [
        CacheEvict("userCache", allEntries = true),
        CacheEvict("userCache.byId", key="#id"),
        CacheEvict("carCache", allEntries = true),
        CacheEvict("carCache.byId", key = "#car.id_car"),
        CacheEvict("carCache.freeCars", allEntries = true)])
    fun delete(id: Long) {
        getById(id)
        userMapper.delete(id)
    }

    @Caching(evict = [
        CacheEvict("userCache", allEntries = true),
        CacheEvict("userCache.byId", key = "#id_user"),
        CacheEvict("carCache", allEntries = true),
        CacheEvict("carCache.byId", key = "#car.id_car"),
        CacheEvict("carCache.freeCars", allEntries = true)])
    fun buyCar(id_user: Long, id_car: Long) {
        if(isFree(id_car)) throw IllegalArgumentException("Car with id=$id_car already belongs somebody.")
        userMapper.buyCar(id_user, id_car)
    }

    @Caching(evict = [
        CacheEvict("userCache", allEntries = true),
        CacheEvict("userCache.byId", key = "#id_user"),
        CacheEvict("carCache", allEntries = true),
        CacheEvict("carCache.byId", key = "#car.id_car"),
        CacheEvict("carCache.freeCars", allEntries = true)])
    fun sellCar(id_user: Long, id_car: Long) {
        if(!inProperty(id_user, id_car)) throw IllegalArgumentException("User with id=$id_user don't own car with id=$id_car.")
        userMapper.sellCar(id_user, id_car)
    }

    fun transferCar(transferCar: TransferCar) {
        sellCar(transferCar.id_seller, transferCar.id_car)
        buyCar(transferCar.id_buyer, transferCar.id_car)
    }

    private fun alreadyExists(user: User): Boolean = userMapper.alreadyExists(user.email!!)

    private fun isFree(id_car: Long): Boolean = userMapper.isCarFree(id_car)

    private fun inProperty(id_user: Long, id_car: Long): Boolean = userMapper.inProperty(id_user, id_car)

}