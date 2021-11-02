package com.SimpleRest.SimpleRestAPI.controllers;

import com.jayway.jsonpath.JsonPath;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("Someone")
@FieldDefaults(level = AccessLevel.PRIVATE)
@TestPropertySource("/test-application.properties")
@Sql(value = {"/create-post-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-post-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    final String path = "C:\\Users\\ose19\\Desktop\\Images\\";

    @Test
    public void PostListTest() throws Exception {
        mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].head").value("firsttest"))
                .andExpect(jsonPath("$[0].text").value("firstmessage"))
                .andExpect(jsonPath("$[0].user.name").value("Someone"));
    }

    @Test
    public void CreatePostWithImages() throws Exception{
        MockHttpServletRequestBuilder multipart = multipart("/posts/create")
                .file(new MockMultipartFile("images",
                        "secfloor.png",
                        "image/png", Files.readAllBytes(Path.of(path + "secfloor.png"))))
                .file(new MockMultipartFile("images",
                        "floor.png",
                        "image/png", Files.readAllBytes(Path.of(path + "floor.png"))))
                .param("head", "testhead")
                .param("text", "testtext");

        mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(redirectedUrl("/posts/10"))
                .andExpect(status().isOk());

        MvcResult result = mockMvc.perform(get("/posts/10"))
                .andDo(print())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.head").value("testhead"))
                .andExpect(jsonPath("$.text").value("testtext"))
                .andReturn();

        JSONArray images = JsonPath.read(result.getResponse().getContentAsString(), "$.images");

        for(int i = 0; i < images.size(); i++){
            mockMvc.perform(get("/posts/10/images/" + JsonPath.read(images.get(i), "$.name")))
                    .andDo(print())
                    .andExpect(content().contentType(MediaType.IMAGE_PNG));
        }
    }

}
