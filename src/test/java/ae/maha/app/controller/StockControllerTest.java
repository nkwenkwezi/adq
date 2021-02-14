package ae.maha.app.controller;

import ae.maha.app.data.TestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
public class StockControllerTest extends AbstractControllerTest{
    private static final String BASE_URI = "/checkout";

    @Test
   public void shouldReturnTotalPrice() throws Exception {
        MvcResult mvcResult = getMockMvc().perform(MockMvcRequestBuilders.post(BASE_URI)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(TestData.getAllCodes()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("price"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("360"));

    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
