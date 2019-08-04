package spring.boot.kotlin.example.db.entity

data class Car(

        val id_car: Long = -1,

        val brand: String? = null,

        val mode: String? = null,

        val color: String? = null,

        val horsepower: Int? = null,

        val price: Int? = null,

        val mileage: Int? = null,

        val picture: String? = null

)