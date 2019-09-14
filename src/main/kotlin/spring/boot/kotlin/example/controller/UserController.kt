package spring.boot.kotlin.example.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import spring.boot.kotlin.example.db.entity.Car
import spring.boot.kotlin.example.db.entity.User
import spring.boot.kotlin.example.dto.TransferCarDto
import spring.boot.kotlin.example.dto.UserDto
import spring.boot.kotlin.example.service.UserService
import java.util.stream.Collectors
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping("/user")
class UserController(@Autowired private val userService: UserService) {

    @GetMapping("/getAll")
    fun getAll(): List<UserDto> = userService.getAll().stream().map(UserDto.Companion::create).collect(Collectors.toList())

    @GetMapping("/getById/{id}")
    fun getById(@PathVariable id: Long): UserDto = UserDto.create(userService.getById(id))

    @GetMapping("/countCars/{id}")
    fun getUserCars(@PathVariable id: Long): Long = userService.countCars(id)

    @PostMapping("/update")
    fun update(@RequestBody user: User): User = userService.update(user)

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) = userService.delete(id)

    @PostMapping("/{id_user}/buyCar/{id_car}")
    fun buyCar(@PathVariable id_user: Long, @PathVariable id_car: Long): Car = userService.buyCar(id_user, id_car)

    @PostMapping("/{id_user}/sellCar/{id_car}")
    fun sellCar(@PathVariable id_user: Long, @PathVariable id_car: Long) = userService.sellCar(id_user, id_car)

    @PostMapping("/transferCar")
    fun transferCar(@RequestBody transferCarDto: TransferCarDto) = userService.transferCar(transferCarDto)

    @PostMapping("/upload")
    fun upload(@RequestBody file: MultipartFile) {
        println(file.originalFilename)
    }

}