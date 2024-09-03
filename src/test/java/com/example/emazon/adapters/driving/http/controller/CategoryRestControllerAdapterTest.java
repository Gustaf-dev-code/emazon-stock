package com.example.emazon.adapters.driving.http.controller;

import com.example.emazon.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.example.emazon.adapters.driving.http.dto.request.AddCategoryRequest;
import com.example.emazon.adapters.driving.http.dto.response.CategoryResponse;
import com.example.emazon.adapters.driving.http.mapper.ICategoryRequestMapper;
import com.example.emazon.adapters.driving.http.mapper.ICategoryResponseMapper;
import com.example.emazon.configuration.Constants;
import com.example.emazon.configuration.exceptionhandler.ControllerAdvisor;
import com.example.emazon.configuration.exceptionhandler.ExceptionResponse;
import com.example.emazon.domain.api.ICategoryServicePort;
import com.example.emazon.domain.exception.EmptyFieldException;
import com.example.emazon.domain.exception.MaxCharAllowedException;
import com.example.emazon.domain.model.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryRestControllerAdapter.class)// Configura el entorno de prueba solo para el controlador CategoryRestControllerAdapter.
@Import(ControllerAdvisor.class)
class CategoryRestControllerAdapterTest {

    @Autowired//Inyecta automáticamente las dependencias necesarias
    private MockMvc mockMvc;//es una clase que permite realizar solicitudes HTTP simuladas dentro del contexto de la prueba.

    @MockBean //Crea instancias simuladas (mock) de las interfaces que se inyectan en el controlador.
    private ICategoryServicePort categoryServicePort;

    @MockBean
    private ICategoryRequestMapper categoryRequestMapper;

    @MockBean
    private ICategoryResponseMapper categoryResponseMapper;

    @Autowired
    private ObjectMapper objectMapper;//se utiliza para convertir objetos Java a JSON y viceversa.

    @Test
    void createCategoryIsOK() throws Exception {
        AddCategoryRequest addCategoryRequest = AddCategoryRequest.builder()
                .name("Electronics")
                .description("Devices and gadgets")
                .build();

        Category category = new Category(null, "Electronics", "Various electronic items");
        CategoryResponse categoryResponse = new CategoryResponse(1, "Electronics", "Various electronic items");

        when(categoryRequestMapper.addRequestToCategory(any(AddCategoryRequest.class))).thenReturn(category);
        when(categoryServicePort.save(any(Category.class))).thenReturn(category);
        when(categoryResponseMapper.toCategoryResponse(any(Category.class))).thenReturn(categoryResponse);

        /* mockMvc.perform():
        Realiza la simulación de una solicitud HTTP POST al endpoint /category/.
        Esta solicitud contiene el cuerpo JSON de AddCategoryRequest convertido usando ObjectMapper.*/
        mockMvc.perform(post("/category/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addCategoryRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Electronics"))
                .andExpect(jsonPath("$.description").value("Various electronic items"));

        /* andExpect(status().isCreated()): Verifica que el estado HTTP de la respuesta sea 201 (CREATED).*/

        verify(categoryRequestMapper).addRequestToCategory(any(AddCategoryRequest.class));
        verify(categoryServicePort).save(any(Category.class));
        verify(categoryResponseMapper).toCategoryResponse(any(Category.class));
    }

    @Test
    void createCategoryWithExistingNameReturnsConflict() throws Exception {
        // Arrange
        AddCategoryRequest addCategoryRequest = AddCategoryRequest.builder()
                .name("Ropa")
                .description("Todo tipo de ropa")
                .build();

        // Simula el comportamiento del servicio para lanzar una excepción cuando se intenta guardar una categoría con un nombre existente.
        when(categoryServicePort.save(any()))
                .thenThrow(new CategoryAlreadyExistsException(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE));

        // Act
        MvcResult result = mockMvc.perform(post("/category/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addCategoryRequest)))
                .andExpect(status().isConflict()) // Verifica que el estado HTTP sea 409 (CONFLICT)
                .andReturn();

        // Deserialize the response content to the ExceptionResponse object
        String content = result.getResponse().getContentAsString();
        ExceptionResponse response = objectMapper.readValue(content, ExceptionResponse.class);

        // Assert
        assertEquals(HttpStatus.CONFLICT.value(), response.getStatusCode().value());
        assertEquals(Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE, response.getMessage());
    }

    @Test
    void createCategoryWithLongNameReturnsBadRequest() throws Exception {
        // Arrange
        AddCategoryRequest addCategoryRequest = AddCategoryRequest.builder()
                .name("This is a very long name that exceeds the maximum number of characters allowed")
                .description("Description")
                .build();

        when(categoryServicePort.save(any()))
                .thenThrow(new MaxCharAllowedException("NAME"));
        // Act
        MvcResult result = mockMvc.perform(post("/category/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addCategoryRequest)))
                .andExpect(status().isBadRequest()) // Verifica que el estado HTTP sea 400 (BAD REQUEST)
                .andReturn();

        // Deserialize the response content to the ExceptionResponse object
        String content = result.getResponse().getContentAsString();
        ExceptionResponse response = objectMapper.readValue(content, ExceptionResponse.class);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
        String expectedMessage = String.format(Constants.MAX_CHAR_ALLOWED_EXCEPTION_MESSAGE, "NAME");
        assertEquals(expectedMessage, response.getMessage());
    }

    @Test
    void createCategoryWithLongDescriptionReturnsBadRequest() throws Exception {
        // Arrange
        AddCategoryRequest addCategoryRequest = AddCategoryRequest.builder()
                .name("Maquinaria")
                .description("Descriptionssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
                .build();

        when(categoryServicePort.save(any()))
                .thenThrow(new MaxCharAllowedException("DESCRIPTION"));
        // Act
        MvcResult result = mockMvc.perform(post("/category/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addCategoryRequest)))
                .andExpect(status().isBadRequest()) // Verifica que el estado HTTP sea 400 (BAD REQUEST)
                .andReturn();

        // Deserialize the response content to the ExceptionResponse object
        String content = result.getResponse().getContentAsString();
        ExceptionResponse response = objectMapper.readValue(content, ExceptionResponse.class);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
        String expectedMessage = String.format(Constants.MAX_CHAR_ALLOWED_EXCEPTION_MESSAGE, "DESCRIPTION");
        assertEquals(expectedMessage, response.getMessage());
    }

    @Test
    void createCategoryWithoutNameReturnsBadRequest() throws Exception {
        // Arrange
        AddCategoryRequest addCategoryRequest = AddCategoryRequest.builder()
                .name("")
                .description("Descriptionssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss")
                .build();

        when(categoryServicePort.save(any()))
                .thenThrow(new EmptyFieldException("NAME"));
        // Act
        MvcResult result = mockMvc.perform(post("/category/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addCategoryRequest)))
                .andExpect(status().isBadRequest()) // Verifica que el estado HTTP sea 400 (BAD REQUEST)
                .andReturn();

        // Deserialize the response content to the ExceptionResponse object
        String content = result.getResponse().getContentAsString();
        ExceptionResponse response = objectMapper.readValue(content, ExceptionResponse.class);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
        String expectedMessage = String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, "NAME");
        assertEquals(expectedMessage, response.getMessage());
        System.out.println(expectedMessage);
        System.out.println(response.getMessage());
    }

    @Test
    void createCategoryWithoutDescriptionReturnsBadRequest() throws Exception {
        // Arrange
        AddCategoryRequest addCategoryRequest = AddCategoryRequest.builder()
                .name("Maquinaria")
                .description("")
                .build();

        when(categoryServicePort.save(any()))
                .thenThrow(new EmptyFieldException("DESCRIPTION"));
        // Act
        MvcResult result = mockMvc.perform(post("/category/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addCategoryRequest)))
                .andExpect(status().isBadRequest()) // Verifica que el estado HTTP sea 400 (BAD REQUEST)
                .andReturn();

        // Deserialize the response content to the ExceptionResponse object
        String content = result.getResponse().getContentAsString();
        ExceptionResponse response = objectMapper.readValue(content, ExceptionResponse.class);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
        String expectedMessage = String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, "DESCRIPTION");
        assertEquals(expectedMessage, response.getMessage());
        System.out.println(expectedMessage);
        System.out.println(response.getMessage());
    }

}