package spring.boot.kotlin.example.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import spring.boot.kotlin.example.db.entity.Car
import spring.boot.kotlin.example.service.CarService

@RestController
@RequestMapping("/car")
class CarController(@Autowired private val carService: CarService) {

    @GetMapping("/getAll")
    fun getAll(): List<Car> = carService.getAll()

    @GetMapping("/getById/{id}")
    fun getById(@PathVariable id: Long): Car = carService.getById(id)

    @GetMapping("/getFreeCars")
    fun getFreeCars(): List<Car> = carService.getFreeCars()

    @PostMapping("/create")
    fun create(@RequestBody car: Car): Car = carService.create(car)

    @PostMapping("/update")
    fun update(@RequestBody car: Car): Car = carService.update(car)

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) = carService.delete(id)

}