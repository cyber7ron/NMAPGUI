package com.uniovi.nmapgui;


import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class NMapGuiApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;

    @Test
    public void basicTest() throws Exception {
    	assertNotNull("mockMvc couldn't be initiated", mockMvc);
        this.mockMvc.perform(get("/nmap")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Port Scan")));
    }
    @Test
    public void executeTest() throws Exception {
    	basicTest();
    	assertNotNull("mockMvc couldn't be initiated", mockMvc);
        this.mockMvc.perform(get("/nmap-exe?code={code}", "scanme.nmap.org")).andExpect(status().isOk())
                .andExpect(content().string(containsString("nmap scanme.nmap.org")));
    }	
    @Test
    public void updateTest() throws Exception {
    	executeTest();
    	assertNotNull("mockMvc couldn't be initiated", mockMvc);
    	this.mockMvc.perform(get("/nmap/update", false)).andExpect(status().isOk())
                .andExpect(content().string(containsString("<div ")));
    	Thread.sleep(5000);
    	this.mockMvc.perform(get("/nmap/update", true)).andExpect(status().isOk())
        .andExpect(content().string(containsString("<div ")));
    }
    @Test
    public void finishedQueueTest() throws Exception {
    	executeTest();
    	assertNotNull("mockMvc couldn't be initiated", mockMvc);
    	Thread.sleep(60000);
    	this.mockMvc.perform(get("/nmap/finishedQueued")).andExpect(status().isOk())
        .andExpect(content().string(containsString("true")));

    }
    @Test
    public void downloadTest() throws Exception {
    	basicTest();
    	assertNotNull("mockMvc couldn't be initiated", mockMvc);
    	this.mockMvc.perform(get("/nmap/download/{filename}", "test.xml")).andExpect(status().isNotFound());
    }
    
    @Test
    public void localAppTest(){
    	System.setProperty("java.awt.headless", "false"); 
    	assertNotNull("mockMvc couldn't be initiated", mockMvc);
    	new NMapGuiLoader().main(new String[]{});
    }
    

}

