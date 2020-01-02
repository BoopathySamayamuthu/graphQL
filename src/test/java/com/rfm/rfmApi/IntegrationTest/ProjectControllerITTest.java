package com.rfm.rfmApi.IntegrationTest;

import com.rfm.rfmApi.RevenueForecastManagerApiApplication;
import com.rfm.rfmApi.controllers.ProjectController;
import com.rfm.rfmApi.entities.Project;
import com.rfm.rfmApi.repositories.ProjectRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= RevenueForecastManagerApiApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class ProjectControllerITTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectController projectController;

    @Test
    public void retrieveAllProjectsTest() throws Exception {

        //Instruct Mock MVC to make a GET Request at the controller end point
        RequestBuilder builder = MockMvcRequestBuilders.get("/projects/getProjects").accept(MediaType.APPLICATION_JSON);

        //Capture the Actual Response from Mock MVC
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();
        int responseCode = mvcResult.getResponse().getStatus();

        //Assert for successfull response
        System.out.println("****** MY STATUS CODE *******"+responseCode);
        System.out.println("****** MY CONTENT AS STRING *******"+mvcResult.getResponse().getContentAsString());
        assertTrue(responseCode == 200);

    }


    @Test
    public void saveProjectTest() throws Exception {

        String mockRequest = "{\n" +
                "    \"rfmProjectId\": \"1000021973LMTQ00000081\",\n" +
                "    \"esaProjectId\": \"1000021973\",\n" +
                "    \"esaProjectName\": \"LM QA Support\",\n" +
                "    \"accountName\": \"Liberty Mutual Insurance Company\",\n" +
                "    \"sowProjectId\": \"LMTQ00000081\",\n" +
                "    \"sowProjectName\": \"GRS NI QA\",\n" +
                "    \"projectActiveStatus\": 1,\n" +
                "    \"department\": \"Claims\",\n" +
                "    \"projectType\": \"TM\",\n" +
                "    \"sbu\": \"GRS\"\n" +
                "  }";

        // Make a POST Request
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/projects/addProject")
                .accept(MediaType.APPLICATION_JSON)
                .content(mockRequest)
                .contentType(MediaType.APPLICATION_JSON);

        //Capture the Actual Response from Mock MVC
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        //Assert Response contains Expected Value and the Data is writtent  the DB

        assertThat(mvcResult.getResponse().getContentAsString(), containsString("1000021973LMTQ00000081"));

    }

    @Test
    public void bulkProjectUploadTest() throws Exception {

        String mockRequest = "[\n" +
                " {\n" +
                "    \"rfmProjectId\": \"1000021973LMTQ00000289\",\n" +
                "    \"esaProjectId\": 1000021973,\n" +
                "    \"esaProjectName\": \"LM QA Support\",\n" +
                "    \"accountName\": \"Liberty Mutual Insurance Company\",\n" +
                "    \"sowProjectId\": \"LMTQ00000289\",\n" +
                "    \"sowProjectName\": \"GRM US CSW\",\n" +
                "    \"projectActiveStatus\": 1,\n" +
                "    \"department\": \"Agent and Partners\",\n" +
                "    \"projectType\": \"TM\",\n" +
                "    \"sbu\": \"GRM\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"rfmProjectId\": \"1000021973LMTQ00000436\",\n" +
                "    \"esaProjectId\": 1000021973,\n" +
                "    \"esaProjectName\": \"LM QA Support\",\n" +
                "    \"accountName\": \"Liberty Mutual Insurance Company\",\n" +
                "    \"sowProjectId\": \"LMTQ00000436\",\n" +
                "    \"sowProjectName\": \"GRM US BLT Quality Engineers\",\n" +
                "    \"projectActiveStatus\": 1,\n" +
                "    \"department\": \"Policy Admin\",\n" +
                "    \"projectType\": \"TM\",\n" +
                "    \"sbu\": \"GRM\"\n" +
                "  }\n" +
                "]";

        // Make a POST Request
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/projects/bulkUpload")
                .accept(MediaType.APPLICATION_JSON)
                .content(mockRequest)
                .contentType(MediaType.APPLICATION_JSON);

        //Capture the Actual Response from Mock MVC
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        //Assert Actual Vs the Expected Response

        assertThat(mvcResult.getResponse().getContentAsString(), containsString("1000021973LMTQ00000289"));
        assertThat(mvcResult.getResponse().getContentAsString(), containsString("1000021973LMTQ00000436"));



    }

}
