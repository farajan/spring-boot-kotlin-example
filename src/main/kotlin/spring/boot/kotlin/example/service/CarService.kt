package spring.boot.kotlin.example.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import spring.boot.kotlin.example.db.entity.Car
import spring.boot.kotlin.example.db.mapper.CarMapper

@Service
@Transactional
class CarService(@Autowired private val carMapper: CarMapper) {

    @Cacheable("carCache")
    fun getAll(): List<Car> = carMapper.findAll()

    @Cacheable("carCache.byId", key = "#id")
    fun getById(id: Long): Car = carMapper.findById(id) ?: throw IllegalArgumentException("Car with id_car=$id doesn't exists.")

    @Cacheable("carCache.freeCars")
    fun getFreeCars(): List<Car> = carMapper.findFreeCars()

    @Caching(evict = [
        CacheEvict("carCache", allEntries = true),
        CacheEvict("carCache.freeCars", allEntries = true)])
    fun create(car: Car): Car {
        if(car.brand == null
                || car.model == null
                || car.color == null
                || car.horsepower == null
                || car.price == null
                || car.mileage == null
        ) throw IllegalArgumentException("Some of following mandatory parameters is null, see: " +
                "brand=${car.brand}, " +
                "model=${car.model}, " +
                "color=${car.color}, " +
                "horsepower=${car.horsepower}," +
                " price=${car.price}, " +
                "mileage=${car.mileage}")
        carMapper.create(car)
        return car
    }

    @Caching(evict = [
        CacheEvict("carCache", allEntries = true),
        CacheEvict("carCache.byId", key = "#car.id_car"),
        CacheEvict("carCache.freeCars", allEntries = true),
        CacheEvict("userCache", allEntries = true),
        CacheEvict("userCache.byId", allEntries = true)])
    fun update(car: Car): Car {
        if(car.id_car == null) throw IllegalArgumentException("id_car can't be null.")
        carMapper.update(car)
        return getById(car.id_car)
    }

    @Caching(evict = [
        CacheEvict("carCache", allEntries = true),
        CacheEvict("carCache.byId", key = "#car.id_car"),
        CacheEvict("carCache.freeCars", allEntries = true),
        CacheEvict("userCache",allEntries = true),
        CacheEvict("userCache.byId", allEntries = true)])
    fun delete(id: Long) {
        getById(id)
        carMapper.delete(id)
    }

}