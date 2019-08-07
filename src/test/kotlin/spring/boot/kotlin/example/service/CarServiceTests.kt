package spring.boot.kotlin.example.service

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import spring.boot.kotlin.example.db.entity.Car
import spring.boot.kotlin.example.db.mapper.CarMapper

@RunWith(MockitoJUnitRunner::class)
class CarServiceTests {


    @InjectMocks
    lateinit var carService: CarService

    @Mock
    lateinit var carMapper: CarMapper

    @Test
    fun getAll_shouldPass() {
        val car1 = Car(1L, "brand", "model", "color", 0, 1, 2)
        val car2 = Car(2L, "brand", "model", "color", 0, 1, 2)
        val car3 = Car(3L, "brand", "model", "color", 0, 1, 2)
        val cars: List<Car> = listOf(car1, car2, car3)

        `when`(carMapper.findAll()).thenReturn(cars)

        carService.getAll()

        verify(carMapper, Mockito.times(1)).findAll()

        assertThat("Size is not equal to 3", carService.getAll().size, Is.`is`(3))
    }

    @Test
    fun getById_shouldPass() {
        Mockito.`when`(carMapper.findById(1L)).thenReturn(Car(1L, "brand", "model", "color", 0, 1, 2))

        val car = carService.getById(1L)

        Assert.assertEquals("brand", car.brand)
        Assert.assertEquals("model", car.model)
        Assert.assertEquals("color", car.color)
        Assert.assertEquals(0, car.horsepower)
        Assert.assertEquals(1, car.price)
        Assert.assertEquals(2, car.mileage)
    }

    @Test
    fun create_shouldPass() {
        val car = Car(1L, "brand", "model", "color", 0, 1, 2)
        carService.create(car)
        verify(carMapper, Mockito.times(1)).create(car)
    }

    @Test
    fun update_shouldPass() {
        val car = Car(1L, "brand", "model", "color", 0, 1, 2)
        `when`(carMapper.findById(1L)).thenReturn(car)

        carService.update(car)

        verify(carMapper, Mockito.times(1)).update(car)
    }

    @Test
    fun delete_shouldPass() {
        val car = Car(1L, "brand", "model", "color", 0, 1, 2)
        `when`(carMapper.findById(1L)).thenReturn(car)

        carService.delete(1L)

        verify(carMapper, Mockito.times(1)).delete(1L)
    }

}