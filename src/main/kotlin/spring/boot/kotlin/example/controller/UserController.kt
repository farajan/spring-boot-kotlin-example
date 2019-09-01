package spring.boot.kotlin.example.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import spring.boot.kotlin.example.db.entity.User
import spring.boot.kotlin.example.payload.TransferCar
import spring.boot.kotlin.example.service.UserService

@RestController
@RequestMapping("/user")
class UserController(@Autowired private val userService: UserService) {

    @GetMapping("/getAll")
    fun getAll(): List<User> = userService.getAll()

    @GetMapping("/getById/{id}")
    fun getById(@PathVariable id: Long): User = userService.getById(id)

    @GetMapping("/countCars/{id}")
    fun getUserCars(@PathVariable id: Long): Long = userService.countCars(id)

    @PostMapping("/update")
    fun update(@RequestBody user: User): User = userService.update(user)

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) = userService.delete(id)

    @PostMapping("/{id_user}/buyCar/{id_car}")
    fun buyCar(@PathVariable id_user: Long, @PathVariable id_car: Long) = userService.buyCar(id_user, id_car)

    @PostMapping("/{id_user}/sellCar/{id_car}")
    fun sellCar(@PathVariable id_user: Long, @PathVariable id_car: Long) = userService.sellCar(id_user, id_car)

    @PostMapping("/transferCar")
    fun transferCar(@RequestBody transferCar: TransferCar) = userService.transferCar(transferCar)

}