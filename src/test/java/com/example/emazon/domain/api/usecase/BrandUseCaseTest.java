package com.example.emazon.domain.api.usecase;

import com.example.emazon.configuration.Constants;
import com.example.emazon.configuration.exceptionhandler.ControllerAdvisor;
import com.example.emazon.domain.exception.EmptyFieldException;
import com.example.emazon.domain.exception.EntityAlreadyExistsException;
import com.example.emazon.domain.exception.MaxCharAllowedException;
import com.example.emazon.domain.model.Brand;
import com.example.emazon.domain.spi.IBrandPersistentPort;
import com.example.emazon.domain.util.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@WebMvcTest(BrandUseCase.class)
@Import(ControllerAdvisor.class)
class BrandUseCaseTest {

    @MockBean
    private IBrandPersistentPort brandPersistentPort;


    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandUseCase = new BrandUseCase(brandPersistentPort);
    }

    @Test
    void testSaveBrandIsOk() {
        // Arrange
        Brand brand = new Brand(1, "Samsung", "Samsung Electronics Co., Ltd");
        when(brandPersistentPort.existsByName(brand.getName())).thenReturn(false);
        when(brandPersistentPort.saveBrand(brand)).thenReturn(brand);

        // Act
        Brand result = brandUseCase.saveBrand(brand);

        // Assert
        assertEquals(brand, result);

        System.out.println("brand: " + brand.getName());
        System.out.println("result: " + result.getName());
    }

    @Test
    void testSaveBrandAlreadyExists() {
        // Arrange
        Brand brand = new Brand(1, "Samsung", "Samsung Electronics Co., Ltd");
        when(brandPersistentPort.existsByName(brand.getName())).thenReturn(true);

        // Act and Assert
        Exception exception = assertThrows(EntityAlreadyExistsException.class, () -> {
            brandUseCase.saveBrand(brand);
        });

        String expectedMessage = String.format(Constants.ENTITY_ALREADY_EXISTS_EXCEPTION_MESSAGE, "Brand", DomainConstants.Field.NAME, brand.getName());


        assertEquals(expectedMessage, exception.getMessage());
    }



    @Test
    void testGetBrandByNameIsOk() {
        // Arrange
        Brand brand = new Brand(1, "Samsung", "Samsung Electronics Co., Ltd");
        when(brandPersistentPort.getBrandByName(brand.getName())).thenReturn(brand);

        // Act
        Brand result = brandUseCase.getBrandByName(brand.getName());

        // Assert
        assertEquals(brand, result);

        System.out.println("brand: " + brand.getName());
        System.out.println("result: " + result.getName());

    }

    @Test
    void testGetBrandByNameIsNull() {
        // Arrange
        String nullName = null;

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class,
                () -> brandUseCase.getBrandByName(nullName));

        assertEquals(DomainConstants.Field.NAME.toString(), exception.getMessage());
        verify(brandPersistentPort, never()).getBrandByName(any());

    }

    @Test
    void getBrandByName_WithEmptyName_ThrowsEmptyFieldException() {
        // Arrange
        String emptyName = "";

        // Act & Assert
        EmptyFieldException exception = assertThrows(EmptyFieldException.class,
                () -> brandUseCase.getBrandByName(emptyName));

        assertEquals(DomainConstants.Field.NAME.toString(), exception.getMessage());
        verify(brandPersistentPort, never()).getBrandByName(any());
    }

}