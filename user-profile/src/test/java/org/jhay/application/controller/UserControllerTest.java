package org.jhay.application.controller;

import org.jhay.application.model.request.AddressRequest;
import org.jhay.application.model.response.AddressResponse;
import org.jhay.domain.api.model.request.VerifyAccountRequest;
import org.jhay.domain.api.model.response.SaveAccountResponse;
import org.jhay.domain.api.services.AccountApiService;
import org.jhay.domain.service.address.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountApiService accountApiService;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testSaveUserAccount() throws Exception {
        // Mock data
        Long userId = 123L;
        VerifyAccountRequest request = new VerifyAccountRequest();
        SaveAccountResponse saveAccountResponse = new SaveAccountResponse();

        // Mock service method
        when(accountApiService.saveUserAccount(anyLong(), any(VerifyAccountRequest.class)))
                .thenReturn(saveAccountResponse);

        // Perform the request
        MvcResult result = mockMvc.perform(post("/users/{userId}/save-account", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"field\":\"value\"}"))
                .andExpect(status().isCreated())
                .andReturn();

        // Verify the response
        String responseBody = result.getResponse().getContentAsString();
        // Add your assertions for the response body here
    }

    @Test
    public void testSaveUserAddress() throws Exception {
        // Mock data
        Long userId = 123L;
        AddressRequest request = new AddressRequest();
        AddressResponse addressResponse = new AddressResponse();

        // Mock service method
        when(addressService.saveUserAddress(anyLong(), any(AddressRequest.class)))
                .thenReturn(addressResponse);

        // Perform the request
        MvcResult result = mockMvc.perform(post("/users/{userId}/save-address", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"field\":\"value\"}"))
                .andExpect(status().isCreated())
                .andReturn();

        // Verify the response
        String responseBody = result.getResponse().getContentAsString();
        // Add your assertions for the response body here
    }

    @Test
    public void testGetUserAddress() throws Exception {
        // Mock data
        Long userId = 123L;
        AddressResponse addressResponse = new AddressResponse();

        // Mock service method
        when(addressService.getUserAddress(anyLong())).thenReturn(addressResponse);

        // Perform the request
        MvcResult result = mockMvc.perform(get("/users/{userId}/get-address", userId))
                .andExpect(status().isOk())
                .andReturn();

        // Verify the response
        String responseBody = result.getResponse().getContentAsString();
        // Add your assertions for the response body here
    }

    @Test
    public void testEditUserAddress() throws Exception {
        // Mock data
        Long userId = 123L;
        AddressRequest request = new AddressRequest();
        AddressResponse addressResponse = new AddressResponse();

        // Mock service method
        when(addressService.editUserAddress(anyLong(), any(AddressRequest.class)))
                .thenReturn(addressResponse);

        // Perform the request
        MvcResult result = mockMvc.perform(put("/users/{userId}/edit-address", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"field\":\"value\"}"))
                .andExpect(status().isOk())
                .andReturn();

        // Verify the response
        String responseBody = result.getResponse().getContentAsString();
        // Add your assertions for the response body here
    }
}
