package com.hubertart.playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubertart.playground.PageController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PageController.class)
public class PageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testIndexEndpoint() throws Exception {
        this.mvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));
    }

    @Test
    public void testMathPIEndpoint() throws Exception {
        this.mvc.perform(get("/math/pi").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testMathCalculateAddition4And6Eq10() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=add&x=4&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 6 = 10"));
    }

    @Test
    public void testMathCalculateMultiply4And6Eq24() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=multiply&x=4&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 * 6 = 24"));
    }

    @Test
    public void testMathCalculateSubtract4And6EqNeg2() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=subtract&x=4&y=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 - 6 = -2"));
    }

    @Test
    public void testMathCalculateDivide30And5Eq6() throws Exception {
        this.mvc.perform(get("/math/calculate?operation=divide&x=30&y=5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("30 / 5 = 6"));
    }

    @Test
    public void testMathCalculate30And5Eq35() throws Exception {
        this.mvc.perform(get("/math/calculate?x=30&y=5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("30 + 5 = 35"));
    }

    @Test
    public void testMathSumAdd4And5And6Eq15() throws Exception {
        this.mvc.perform(post("/math/sum?n=4&n=5&n=6").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("4 + 5 + 6 = 15"));
    }

    @Test
    public void testMathVolumeOf3x4x5RectangularCuboid() throws Exception {
        this.mvc.perform(post("/math/volume/3/4/5").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangular cuboid is 60"));
    }

    @Test
    public void testMathAreaOf4RadCircle() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "circle")
                .param("radius", "4");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));
    }

    @Test
    public void testMathAreaOf4x7Rectangle() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("width", "4")
                .param("height", "7");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));
    }

    @Test
    public void testGetSingleFlightTicket() throws Exception {
        this.mvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departs", is("2020-12-09 21:34")))
                .andExpect(jsonPath("$.tickets[0].price", is(200)));
    }

    @Test
    public void testGetTwoFlightTickets() throws Exception {
        this.mvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departs", is("2020-12-09 21:34")))
                .andExpect(jsonPath("$[0].tickets[0].price", is(200)))
                .andExpect(jsonPath("$[1].departs", is("2020-12-09 21:34")))
                .andExpect(jsonPath("$[1].tickets[0].price", is(10)));
    }

    @Test
    public void testInsertTwoFlightTicketsAndGetTotalStringInsert() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("  {\n" +
                        "    \"tickets\": [\n" +
                        "      {\n" +
                        "        \"passenger\": {\n" +
                        "          \"firstName\": \"Some name\",\n" +
                        "          \"lastName\": \"Some other name\"\n" +
                        "        },\n" +
                        "        \"price\": 200\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"passenger\": {\n" +
                        "          \"firstName\": \"Name B\",\n" +
                        "          \"lastName\": \"Name C\"\n" +
                        "        },\n" +
                        "        \"price\": 150\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\n    \"result\": 350\n}"));
    }

    @Test
    public void testInsertTwoFlightTicketsAndGetTotalHashMapInsert() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        HashMap<String, Object> passenger1 = new HashMap<String, Object>() {
            {
                put("firstName", "Some name");
                put("lastName", "Some other name");
            }
        };
        HashMap<String, Object> passenger2 = new HashMap<String, Object>() {
            {
                put("firstName", "Name B");
                put("lastName", "Name C");
            }
        };
        HashMap<String, Object> ticket1 = new HashMap<String, Object>() {
            {
                put("passenger", passenger1);
                put("price", 200);
            }
        };
        HashMap<String, Object> ticket2 = new HashMap<String, Object>() {
            {
                put("passenger", passenger2);
                put("price", 150);
            }
        };
        HashMap<String, Object> tickets = new HashMap<String, Object>() {
            {
                put("tickets", Arrays.asList(ticket1, ticket2));
            }
        };

        String json = objectMapper.writeValueAsString(tickets);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\n    \"result\": 350\n}"));
    }


    @Test
    public void testInsertTwoFlightTicketsAndGetTotalFileInsert() throws Exception {
        String json = getJSON("/tickets.json");
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\n    \"result\": 350\n}"));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));
    }


}