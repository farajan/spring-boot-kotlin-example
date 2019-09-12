package spring.boot.kotlin.example.service

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import spring.boot.kotlin.example.db.entity.User
import spring.boot.kotlin.example.db.mapper.UserMapper
import java.time.LocalDateTime

@RunWith(MockitoJUnitRunner::class)
class UserServiceTests {

    @InjectMocks
    lateinit var userService: UserService

    @Mock
    lateinit var userMapper: UserMapper

    @Mock
    lateinit var carService: CarService

    @Test
    fun getAll_shouldPass() {
        val user1 = User(1L, "firstName", "lastName", "email", "password", LocalDateTime.now())
        val user2 = User(2L, "firstName", "lastName", "email", "password", LocalDateTime.now())
        val user3 = User(3L, "firstName", "lastName", "email", "password", LocalDateTime.now())
        val users: List<User> = listOf(user1, user2, user3)

        `when`(userMapper.findAll()).thenReturn(users)

        userService.getAll()

        verify(userMapper, times(1)).findAll()

        assertThat("Size is not equal to 3", userService.getAll().size, `is`(3))
    }

    @Test
    fun getById_shouldPass() {
        val time = LocalDateTime.now()
        `when`(userMapper.findById(1L)).thenReturn(User(1L, "firstName", "lastName", "email", "password", time))

        val user = userService.getById(1L)

        assertEquals("firstName", user.firstName)
        assertEquals("lastName", user.lastName)
        assertEquals("email", user.email)
        assertEquals("password", user.password)
        assertEquals(time, user.birthday)
    }

    @Test
    fun countCars_shouldPass() {
        `when`(userMapper.countCars(1L)).thenReturn(5)

        val carCount: Int = userService.countCars(1L).toInt()

        verify(userMapper, times(1)).countCars(1L)
        assertThat("Size is not equal to 5",  carCount, `is`(5))
    }

    @Test
    fun update_shouldPass() {
        val user = User(1L, "firstName", "lastName", "email", "password", LocalDateTime.now())
        `when`(userMapper.findById(1L)).thenReturn(user)

        userService.update(user)

        verify(userMapper, times(1)).update(user)
    }

    @Test
    fun delete_shouldPass() {
        val user = User(1L, "firstName", "lastName", "email", "password", LocalDateTime.now())
        `when`(userMapper.findById(1L)).thenReturn(user)

        userService.delete(1L)

        verify(userMapper, times(1)).delete(1L)
    }

    @Test
    fun buyCar_shouldPass() {
        userService.buyCar(1L, 1L)
        verify(userMapper, times(1)).buyCar(1L, 1L)
    }

    @Test
    fun sellCar_shouldPass() {
        `when`(userMapper.inProperty(1L, 1L)).thenReturn(true)
        userService.sellCar(1L, 1L)
        verify(userMapper, times(1)).sellCar(1L,1L)
    }

    @Test
    fun transferCar_shouldPass() {
        `when`(userMapper.inProperty(1L, 3L)).thenReturn(true)
        userService.sellCar(1L, 3L)
        userService.buyCar(2L, 3L)

        verify(userMapper, times(1)).sellCar(1L, 3L)
        verify(userMapper, times(1)).buyCar(2L, 3L)
    }

}