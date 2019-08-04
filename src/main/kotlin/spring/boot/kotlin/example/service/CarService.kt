package spring.boot.kotlin.example.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import spring.boot.kotlin.example.db.mapper.CarMapper

@Service
class CarService(@Autowired private val carMapper: CarMapper) {
}