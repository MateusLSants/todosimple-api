package br.com.dev.todosimple.todosimple.service.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DataBidingViolationException extends DataIntegrityViolationException {
    public DataBidingViolationException(String msg) {
        super(msg);
    }
}
