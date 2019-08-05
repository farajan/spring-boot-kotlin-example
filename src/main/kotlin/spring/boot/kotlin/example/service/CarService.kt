package spring.boot.kotlin.example.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import spring.boot.kotlin.example.db.entity.Car
import spring.boot.kotlin.example.db.mapper.CarMapper

@Service
class CarService(@Autowired private val carMapper: CarMapper) {

    fun getAll(): List<Car> = carMapper.findAll()

    fun getById(id: Long): Car = carMapper.findById(id) ?: throw IllegalArgumentException("Car with id_car=$id doesn't exists.")

    fun create(car: Car): Car {
        if(car.brand == null
                || car.model == null
                || car.color == null
                || car.horsepower == null
                || car.price == null
                || car.mileage == null
        ) throw IllegalArgumentException("Some not nullable parameter is null.")
        carMapper.create(car)
        return car
    }

    fun update(car: Car): Car {
        if(car.id_car == null) throw IllegalArgumentException("id_car can't be null.")
        carMapper.update(car)
        return getById(car.id_car)
    }

    fun delete(id: Long) {
        getById(id)
        carMapper.delete(id)
    }

}