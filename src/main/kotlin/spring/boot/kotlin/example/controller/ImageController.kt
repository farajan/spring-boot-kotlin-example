package spring.boot.kotlin.example.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import spring.boot.kotlin.example.service.ImageService

@RestController
@RequestMapping("/image")
class ImageController(@Autowired private val imageService: ImageService) {

    @PostMapping("/upload")
    fun upload(@RequestBody file: MultipartFile): String? = imageService.upload(file)

}