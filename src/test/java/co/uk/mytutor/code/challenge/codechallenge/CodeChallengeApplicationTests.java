package co.uk.mytutor.code.challenge.codechallenge;

import co.uk.mytutor.code.challenge.codechallenge.controller.BookSellController;
import co.uk.mytutor.code.challenge.codechallenge.controller.BookShopController;
import co.uk.mytutor.code.challenge.codechallenge.json.BookRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Samuel Catalano
 * @since Feb 28, 2020
 */

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
class CodeChallengeApplicationTests {

    @Autowired
    private BookSellController bookSellController;

    @Autowired
    private BookShopController bookShopController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() {
        assertThat(this.bookSellController).isNotNull();
        assertThat(this.bookShopController).isNotNull();
    }

    @Test
    public void testSellBook() throws Exception {
        final BookRequest request = new BookRequest();
        request.setType("C");
        request.setQuantity(5);

        this.mockMvc.perform(post("/mytutor/book-sell/sell")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

    }

    @Test
    public void testSellBookOutOfStock() throws Exception {
        final BookRequest request = new BookRequest();
        request.setType("C");
        request.setQuantity(15);

        final String outOfStockQuantity = "{\"type\":\"E\",\"quantity\":15}";


        final RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/mytutor/book-sell/sell")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(outOfStockQuantity);

        final MvcResult result = this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andReturn();

        Assert.assertThat(result.getResponse().getContentAsString(), containsString("Sorry, we are out of stock!"));
    }

    @Test
    public void testSellBookWrongPath() throws Exception {
        final BookRequest request = new BookRequest();
        request.setType("C");
        request.setQuantity(5);

        this.mockMvc.perform(post("/to-sell")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testSellingReport() throws Exception {
        this.mockMvc.perform(get("/mytutor/book-shop/report"))
                .andExpect(status().isOk());

    }

    @Test
    public void testSellingReportWrongPath() throws Exception {
        this.mockMvc.perform(get("/getreport"))
                .andExpect(status().isNotFound());

    }
}