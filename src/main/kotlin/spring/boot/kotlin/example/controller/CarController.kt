package spring.boot.kotlin.example.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spring.boot.kotlin.example.service.CarService

@RestController
@RequestMapping("/car")
class CarController(@Autowired private val carService: CarService) {
}