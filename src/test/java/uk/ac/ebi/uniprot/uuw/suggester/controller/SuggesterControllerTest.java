package uk.ac.ebi.uniprot.uuw.suggester.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uk.ac.ebi.uniprot.uuw.suggester.SuggesterREST;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * Created 18/07/18
 *
 * @author Edd
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SuggesterREST.class)
@WebAppConfiguration
public class SuggesterControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void canRetrieveSuggestionsOkay() throws Exception {
        mockMvc.perform(get("/suggester")
                                .param("dict", "taxonomy")
                                .param("query", "some text"));
    }

    @Test
    public void requestMustIncludeDictAndQueryParams() {

    }
}