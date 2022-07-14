package com.claimsmanagement.claimsmanagement;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class ClaimsManagementApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetClaimById() throws Exception{
        mockMvc.perform(get("/http://localhost/claim/1")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void testGetAllClaims() throws Exception{
        mockMvc.perform(get("/http://localhost/claim")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void testGetAllClaimsByMember() throws Exception{
        mockMvc.perform(get("/http://localhost/claim/user/1/view")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }
    @Test
    public void testUpdateClaim() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/http://localhost/claim/1")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

}
