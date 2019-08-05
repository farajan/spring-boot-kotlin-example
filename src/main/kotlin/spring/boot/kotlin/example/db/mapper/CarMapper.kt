package spring.boot.kotlin.example.db.mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository
import spring.boot.kotlin.example.db.entity.Car

@Mapper
@Repository
interface CarMapper {

    fun findAll(): List<Car>

    fun findById(id: Long): Car

    fun create(car: Car)

    fun update(car: Car)

    fun delete(id: Long)

}