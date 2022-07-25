package pl.kkubiak.concise.controllers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kkubiak.concise.services.LRUCacheService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

@WebMvcTest(value = CacheController.class)
class CacheControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private LRUCacheService lruCacheService;

    @Test
    void addValue_success() throws Exception {
        doNothing().when(lruCacheService).putValue(anyString(), anyString());

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/api/v1/cache/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content("{\"key\": \"1\", \"value\": \"Test_1\"}");
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void addValue_failed() throws Exception {
        doNothing().when(lruCacheService).putValue(anyString(), anyString());

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/api/v1/cache/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content("{\"value\": \"Test_1\"}");
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getValue_success() throws Exception {

        Mockito.when(lruCacheService.getValueByKey(anyString())).thenReturn("Test_1");

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/api/v1/cache/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8");
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"key\":\"1\",\"value\":\"Test_1\"}"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void getValue_failed() throws Exception {
        Mockito.when(lruCacheService.getValueByKey(anyString())).thenReturn("-1");

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/api/v1/cache/1/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8");
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"key\":\"1\",\"value\":\"-1\"}"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void invalidateCache_Test() throws Exception {
        doNothing().when(lruCacheService).invalidateCache();

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/api/v1/cache/invalidate/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8");
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateCapacity_success() throws Exception {
        doNothing().when(lruCacheService).updateCapacity(anyInt());

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/api/v1/cache/capacity/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content("{\"capacity\":1}");
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateCapacity_failed() throws Exception {
        doNothing().when(lruCacheService).updateCapacity(anyInt());

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/api/v1/cache/capacity/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content("");
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
}