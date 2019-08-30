package spring.boot.kotlin.example.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ExceptionController {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalArgumentException::class])
    fun handleConflict(e: Exception, response: HttpServletResponse) =
            response.sendError(HttpStatus.CONFLICT.value(), e.message)

}