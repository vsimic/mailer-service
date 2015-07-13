package rs.etf.vladan.simic.services;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class MailServiceTest {

    MockMvc mockMvc;

    @InjectMocks
    MailService service;

    @Before
    public void setup() {
	MockitoAnnotations.initMocks(this);
	this.mockMvc = MockMvcBuilders.standaloneSetup(service).build();
    }
    
    
    

}
