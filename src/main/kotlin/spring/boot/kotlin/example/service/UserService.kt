package spring.boot.kotlin.example.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import spring.boot.kotlin.example.db.entity.Car
import spring.boot.kotlin.example.db.entity.User
import spring.boot.kotlin.example.db.mapper.UserMapper
import spring.boot.kotlin.example.dto.TransferCarDto

@Service
class UserService(
        @Autowired private val userMapper: UserMapper,
        @Autowired private val carService: CarService
) {

    @Cacheable("userCache")
    fun getAll(): List<User> = userMapper.findAll()

    @Cacheable("userCache.byId", key = "#id")
    fun getById(id: Long): User = userMapper.findById(id) ?: throw IllegalArgumentException("User with id_user=$id doesn't exists.")

    fun countCars(id: Long): Long = userMapper.countCars(id)

    @Transactional
    @Caching(evict = [
        CacheEvict("userCache", allEntries = true),
        CacheEvict("userCache.byId", key = "#user.id_user")])
    fun update(user: User): User {
        if(user.id_user == null) throw NullPointerException("id_user can't be null.")
        userMapper.update(user)
        return getById(user.id_user)
    }

    @Transactional
    @Caching(evict = [
        CacheEvict("userCache", allEntries = true),
        CacheEvict("userCache.byId", key="#id")])
    fun delete(id: Long) {
        getById(id)
        userMapper.delete(id)
    }

    @Transactional
    @Caching(evict = [
        CacheEvict("userCache", allEntries = true),
        CacheEvict("userCache.byId", key = "#id_user")])
    fun buyCar(id_user: Long, id_car: Long): Car {
        if(isFree(id_car)) {
            userMapper.buyCar(id_user, id_car)
        }
        return carService.getById(id_car)
    }

    @Caching(evict = [
        CacheEvict("userCache", allEntries = true),
        CacheEvict("userCache.byId", key = "#id_user")])
    fun sellCar(id_user: Long, id_car: Long) {
        if(!inProperty(id_user, id_car)) throw IllegalArgumentException("User with id=$id_user don't own car with id=$id_car.")
        userMapper.sellCar(id_user, id_car)
    }

    @Transactional
    fun transferCar(transferCarDto: TransferCarDto) {
        sellCar(transferCarDto.id_seller, transferCarDto.id_car)
        buyCar(transferCarDto.id_buyer, transferCarDto.id_car)
    }

    private fun isFree(id_car: Long): Boolean = !userMapper.isCarTaken(id_car)

    private fun inProperty(id_user: Long, id_car: Long): Boolean = userMapper.inProperty(id_user, id_car)

}