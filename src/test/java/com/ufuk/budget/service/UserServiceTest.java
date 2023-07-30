package com.ufuk.budget.service;
import com.ufuk.budget.entity.User;
import com.ufuk.budget.exception.NotFoundException;
import com.ufuk.budget.model.dto.UserDto;
import com.ufuk.budget.model.request.CreateUserRequest;
import com.ufuk.budget.model.request.UpdateUserRequest;
import com.ufuk.budget.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {


    private UserRepository userRepository;


    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);

    }

    @Test
    public void testGetUserById_ExistingUser() {
        // Arrange
        Integer userId = 1;
        String userName = "John Doe";
        String userEmail = "john.doe@example.com";
        double userTotalBudget = 1000.0;
        User user = new User();
        user.setId(userId);
        user.setEmail(userEmail);
        user.setName(userName);
        user.setTotalBudget(userTotalBudget);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        UserDto result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals(userName, result.getName());
        assertEquals(userEmail, result.getEmail());
        assertEquals(userTotalBudget, result.getTotalBudget());
    }

    @Test
    public void testGetUserById_NonExistingUser() {
        // Arrange
        Integer userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        UserDto result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getName());
        assertNull(result.getEmail());
        assertEquals(null, result.getTotalBudget());
    }

    @Test
    public void testFindUserById_ExistingUser() {
        // Arrange
        Integer userId = 1;
        String userName = "Jane Smith";
        String userEmail = "jane.smith@example.com";
        double userTotalBudget = 1500.0;
        User user = new User();
        user.setTotalBudget(userTotalBudget);
        user.setId(userId);
        user.setEmail(userEmail);
        user.setName(userName);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = userService.findUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals(userName, result.getName());
        assertEquals(userEmail, result.getEmail());
        assertEquals(userTotalBudget, result.getTotalBudget());
    }

    @Test
    public void testFindUserById_NonExistingUser() {
        // Arrange
        Integer userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalAccessError.class, () -> userService.findUserById(userId));
    }

    @Test
    public void testCreateUser() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest();
        request.setName("Alice");
        request.setEmail("alice@example.com");
        request.setPassword("password");

        // Act
        userService.createUser(request);

        // Assert
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testDeleteUserById() {
        // Arrange
        Integer userId = 1;

        // Act
        userService.deleteUserById(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testUpdateUser_ExistingUser() {
        // Arrange
        Integer userId = 1;
        String userName = "Mark Johnson";
        String userEmail = "mark.johnson@example.com";
        double userTotalBudget = 2000.0;

        UpdateUserRequest request = new UpdateUserRequest();
        request.setUserId(userId);
        request.setName(userName);
        request.setEmail(userEmail);
        request.setPassword("new_password");
        request.setTotalBudget(userTotalBudget);

        User existingUser = new User();
        existingUser.setTotalBudget(1000.0);
        existingUser.setId(userId);
        existingUser.setName("Old Name");
        existingUser.setEmail("old.email@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // Act
        userService.updateUser(request);

        // Assert
        verify(userRepository, times(1)).save(existingUser);
        assertEquals(userId, existingUser.getId());
        assertEquals(userName, existingUser.getName());
        assertEquals(userEmail, existingUser.getEmail());
        assertEquals(userTotalBudget, existingUser.getTotalBudget());
    }

    @Test
    public void testUpdateUser_NonExistingUser() {
        // Arrange
        Integer userId = 1;
        UpdateUserRequest request = new UpdateUserRequest();
        request.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> userService.updateUser(request));
    }

}