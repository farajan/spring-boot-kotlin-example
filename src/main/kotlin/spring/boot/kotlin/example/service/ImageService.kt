package spring.boot.kotlin.example.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.time.LocalDateTime
import java.util.Random

@Service
class ImageService {

    @Value("\${myapp.image.path}")
    private val path: String? = null

    private val max = 1000000000

    private val min = 10000000


    fun upload(file: MultipartFile): String? {
        val name = getFileName()
        file.transferTo(File(path + name))
        return name
    }

    private fun getFileName(): String {
        val date = LocalDateTime.now().toString().replace(":", "-")
        val r = Random()
        val randomNumber = r.nextInt(max - min + 1) + min

        return "$date-$randomNumber.png"
    }


}