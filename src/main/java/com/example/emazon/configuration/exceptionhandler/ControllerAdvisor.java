package com.example.emazon.configuration.exceptionhandler;

import com.example.emazon.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.example.emazon.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.example.emazon.adapters.driven.jpa.mysql.exception.InvalidSortDirectionException;
import com.example.emazon.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.example.emazon.configuration.Constants;
import com.example.emazon.domain.exception.*;
import com.example.emazon.domain.util.PaginationConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.swing.plaf.PanelUI;
import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    private ResponseEntity<ExceptionResponse> buildResponse(String message, HttpStatus status) {
        return ResponseEntity
                .status(status)
                .body(new ExceptionResponse(status, message, LocalDateTime.now()));
    }

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException e) {
        String errorMessage = String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, e.getMessage());
        return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException e) {
        return buildResponse(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        String errorMessage = String.format(Constants.ENTITY_ALREADY_EXISTS_EXCEPTION_MESSAGE, e.getEntityName(), e.getFieldName(), e.getValue());
        return buildResponse(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException e) {
        String message = e.getMessage() == null ? e.getClass().getSimpleName() : e.getMessage();
        return buildResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidSortDirectionException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidSortDirectionException() {
        String errorMessage = Constants.INVALID_SORT_DIRECTION_EXCEPTION_MESSAGE;
        return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidNumberPageException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidNumberPageException() {
        String errorMessage = PaginationConstants.INVALID_NUMBER_PAGE_EXCEPTION_MESSAGE;
        return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSizePageException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidSizePageException() {
        String errorMessage = PaginationConstants.INVALID_SIZE_PAGE_EXCEPTION_MESSAGE;
        return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaxCharAllowedException.class)
    public ResponseEntity<ExceptionResponse > handleMaxCharAllowedException(MaxCharAllowedException e) {
        String errorMessage = String.format(Constants.MAX_CHAR_ALLOWED_EXCEPTION_MESSAGE, e.getMessage());
        return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NegativeNotAllowedException.class)
    public ResponseEntity<ExceptionResponse> handleNegativeNotAllowedException(NegativeNotAllowedException e) {
        String errorMessage = String.format(Constants.NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE, e.getMessage());
        return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException(ElementNotFoundException e) {
        String errorMessage = String.format(Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE,e.getEntity(), e.getField(), e.getValue());
        return buildResponse(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        String errorMessage = Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE;
        return buildResponse(errorMessage, HttpStatus.NOT_FOUND);
    }
}