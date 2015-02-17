package org.landasource.rempi.manager.api.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Illegal query", value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

}
