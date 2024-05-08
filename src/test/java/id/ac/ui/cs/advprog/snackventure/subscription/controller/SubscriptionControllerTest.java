package id.ac.ui.cs.advprog.snackventure.subscription.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.snackventure.subscription.enums.DeliveryFrequency;
import id.ac.ui.cs.advprog.snackventure.subscription.model.Subscription;
import id.ac.ui.cs.advprog.snackventure.subscription.service.SubscriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class SubscriptionControllerTest {

    @InjectMocks
    private SubscriptionController subscriptionController;

    @Mock
    private SubscriptionService subscriptionService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController).build();
    }

    @Test
    public void testCreateSubscription() throws Exception {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionBoxId("1f36d862-6e0b-47a3-aa17-e02ec74a0246");
        subscription.setFrequency(DeliveryFrequency.MONTHLY);
        subscription.setCustomerId("cebc06bc-3e35-44dc-b95a-2c77a40010f0");

        when(subscriptionService.createSubscription(anyString(), anyString(), anyString())).thenReturn(subscription);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("frequency", "MTH");
        requestBody.put("customerId", "cebc06bc-3e35-44dc-b95a-2c77a40010f0");

        MvcResult mvcResult = mockMvc.perform(post("/subscription-box/1f36d862-6e0b-47a3-aa17-e02ec74a0246/subscribe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetAllSubscriptions() throws Exception {
        List<Subscription> subscriptions = new ArrayList<>();
        Subscription subscription = new Subscription();
        subscription.setSubscriptionBoxId("1f36d862-6e0b-47a3-aa17-e02ec74a0246");
        subscription.setFrequency(DeliveryFrequency.MONTHLY);
        subscription.setCustomerId("cebc06bc-3e35-44dc-b95a-2c77a40010f0");
        subscriptions.add(subscription);

        when(subscriptionService.getAllSubscriptions()).thenReturn(subscriptions);

        MvcResult mvcResult = mockMvc.perform(get("/subscriptions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testGetSubscriptionById() throws Exception {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionBoxId("1f36d862-6e0b-47a3-aa17-e02ec74a0246");
        subscription.setFrequency(DeliveryFrequency.MONTHLY);
        subscription.setCustomerId("cebc06bc-3e35-44dc-b95a-2c77a40010f0");

        when(subscriptionService.findById(anyString())).thenReturn(subscription);

        MvcResult mvcResult = mockMvc.perform(get("/subscriptions/1f36d862-6e0b-47a3-aa17-e02ec74a0246")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateApprovalStatus() throws Exception {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionBoxId("1f36d862-6e0b-47a3-aa17-e02ec74a0246");
        subscription.setFrequency(DeliveryFrequency.MONTHLY);
        subscription.setCustomerId("cebc06bc-3e35-44dc-b95a-2c77a40010f0");

        when(subscriptionService.updateApprovalStatus(anyString(), anyString())).thenReturn(subscription);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", "APPROVED");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/subscriptions/1f36d862-6e0b-47a3-aa17-e02ec74a0246/change-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testCancelSubscription() throws Exception {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionBoxId("1f36d862-6e0b-47a3-aa17-e02ec74a0246");
        subscription.setFrequency(DeliveryFrequency.MONTHLY);
        subscription.setCustomerId("cebc06bc-3e35-44dc-b95a-2c77a40010f0");

        when(subscriptionService.cancelSubscription(anyString())).thenReturn(subscription);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/subscriptions/1f36d862-6e0b-47a3-aa17-e02ec74a0246/unsubscribe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result

        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}