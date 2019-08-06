package spring.boot.kotlin.example.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import spring.boot.kotlin.example.db.entity.User
import spring.boot.kotlin.example.db.mapper.UserMapper
import spring.boot.kotlin.example.payload.TransferCar

@Service
@Transactional
class UserService(
        @Autowired private val userMapper: UserMapper,
        @Autowired private val carService: CarService
) {

    fun getAll(): List<User> = userMapper.findAll()

    fun getById(id: Long): User = userMapper.findById(id) ?: throw IllegalArgumentException("User with id_user=$id doesn't exists.")

    fun create(user: User): User {
        if(user.firstName == null
                || user.lastName == null
                || user.email == null
                || user.password == null
        ) throw IllegalArgumentException("Some of following mandatory parameters is null, see: " +
                "firstName=${user.firstName}, " +
                "lastName=${user.lastName}, " +
                "email=${user.email}, " +
                "password=${user.password}, " +
                "birthday=${user.birthday}")
        if(alreadyExists(user)) throw IllegalArgumentException("User with this email already exists.")
        userMapper.create(user)
        return user
    }

    private fun alreadyExists(user: User): Boolean = userMapper.alreadyExists(user.email!!)

    fun update(user: User): User {
        if(user.id_user == null) throw IllegalArgumentException("id_user can't be null.")
        userMapper.update(user)
        return getById(user.id_user)
    }

    fun delete(id: Long) {
        getById(id)
        userMapper.delete(id)
    }

    fun buyCar(id_user: Long, id_car: Long) {
        if(carService.isFree(id_car)) throw IllegalArgumentException("Car with id=$id_car already belongs somebody.")
        userMapper.buyCar(id_user, id_car)
    }

    fun sellCar(id_user: Long, id_car: Long) {
        if(!inProperty(id_user, id_car)) throw IllegalArgumentException("User with id=$id_user don't own car with id=$id_car.")
        userMapper.sellCar(id_user, id_car)
    }

    private fun inProperty(id_user: Long, id_car: Long): Boolean = userMapper.inProperty(id_user, id_car)

    fun transferCar(transferCar: TransferCar) {
        sellCar(transferCar.id_seller, transferCar.id_car)
        buyCar(transferCar.id_buyer, transferCar.id_car)
    }

}