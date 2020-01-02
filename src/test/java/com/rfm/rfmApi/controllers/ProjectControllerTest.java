package com.rfm.rfmApi.controllers;

import com.rfm.rfmApi.RevenueForecastManagerApiApplication;
import com.rfm.rfmApi.entities.Project;
import com.rfm.rfmApi.repositories.ProjectRepository;
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

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RevenueForecastManagerApiApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectController projectController;

    @Test
    public void retrieveAllProjectsTest() throws Exception {

        //Setup Test Data
        List<Project> projectlist = Collections.singletonList(new Project(
                "1000021973LMTQ00000081",
                "1000021973",
                "LM QA Support",
                "Liberty Mutual Insurance Company",
                "LMTQ00000081",
                "GRS NI QA",
                true,
                "Claims",
                "TM",
                "GRS"
        ));

        //Expected Response Content
        String findAllProjectsResponse = "[{\n" +
                "        \"createdAt\": null,\n" +
                "        \"updatedAt\": null,\n" +
                "        \"rfmProjectId\": \"1000021973LMTQ00000081\",\n" +
                "        \"esaProjectId\": \"1000021973\",\n" +
                "        \"esaProjectName\": \"LM QA Support\",\n" +
                "        \"accountName\": \"Liberty Mutual Insurance Company\",\n" +
                "        \"sowProjectId\": \"LMTQ00000081\",\n" +
                "        \"sowProjectName\": \"GRS NI QA\",\n" +
                "        \"projectActiveStatus\": true,\n" +
                "        \"department\": \"Claims\",\n" +
                "        \"projectType\": \"TM\",\n" +
                "        \"sbu\": \"GRS\"\n" +
                "    }\n" +
                "]";

        //Instruct MockMVC to return the test data when Method under test is initiated
        Mockito.when(projectRepository.findAll()).thenReturn(projectlist);

        //Instruct Mock MVC to make a GET Request at the controller end point
        RequestBuilder builder = MockMvcRequestBuilders.get("/projects/getProjects").accept(MediaType.APPLICATION_JSON);

        //Capture the Actual Response from Mock MVC
        MvcResult mvcResult = mockMvc.perform(builder).andReturn();

        //Assert Actual Vs the Expected Response
        assertEquals(findAllProjectsResponse, mvcResult.getResponse().getContentAsString(), false);

    }


    @Test
    public void saveProjectTest() throws Exception {
        /*Project mockProject = null;

        ObjectMapper mapper = new ObjectMapper();*/
        Project mockProject = new Project(
                "1000021973LMTQ00000081", "1000021973",
                "LM QA Support", "Liberty Mutual Insurance Company",
                "LMTQ00000081", "GRS NI QA",
                true, "Claims",
                "TM", "GRS");

        //   mockProject = mapper.readValue(new File(this.getClass().getClassLoader().getResource("src/test/resources/jsonfiles/" + "singleProjectRequest.json").getFile()), Project.class);

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

        String expectedRespose = "{\"createdAt\":null,\"updatedAt\":null,\"rfmProjectId\": \"1000021973LMTQ00000081\",\"esaProjectId\":\"1000021973\",\"esaProjectName\":\"LM QA Support\",\"accountName\":\"Liberty Mutual Insurance Company\",\"sowProjectId\":\"LMTQ00000081\",\"sowProjectName\":\"GRS NI QA\",\"projectActiveStatus\":true,\"department\":\"Claims\",\"projectType\":\"TM\",\"sbu\":\"GRS\"}";

        // Set the Mock response on ntrigger of method under test
        Mockito.when(projectRepository.save(mockProject)).thenReturn(mockProject);

        // Make a POST Request
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/projects/addProject")
                .accept(MediaType.APPLICATION_JSON)
                .content(mockRequest)
                .contentType(MediaType.APPLICATION_JSON);

        //Capture the Actual Response from Mock MVC
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        //Assert Actual Vs the Expected Response

        assertEquals(expectedRespose, mvcResult.getResponse().getContentAsString(), false);


    }

    @Test
    public void bulkProjectUploadTest() throws Exception {

        Project mockFirstProject = new Project(
                "1000021973LMTQ00000289", "1000021973",
                "LM QA Support", "Liberty Mutual Insurance Company",
                "LMTQ00000289", "GRS US CSW",
                true, "Agent and Partners",
                "TM", "GRS");

        Project mockSecondProject = new Project(
                "1000021973LMTQ00000436", "1000249493",
                "LM QA Support", "Liberty Mutual Insurance Company",
                "LMTQ00000436", "GRM US BLT Quality Engineers",
                true, "SMO",
                "TM", "GRM");

        List<Project> projectlist = new ArrayList<Project>();

        projectlist.add(mockFirstProject);
        projectlist.add(mockSecondProject);


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

        String expectedRespose =
                " [\n" +
                        "    {\n" +
                        "        \"createdAt\": null,\n" +
                        "        \"updatedAt\": null,\n" +
                        "        \"rfmProjectId\": \"1000021973LMTQ00000289\",\n" +
                        "        \"esaProjectId\": \"1000021973\",\n" +
                        "        \"esaProjectName\": \"LM QA Support\",\n" +
                        "        \"accountName\": \"Liberty Mutual Insurance Company\",\n" +
                        "        \"sowProjectId\": \"LMTQ00000289\",\n" +
                        "        \"sowProjectName\": \"GRM US CSW\",\n" +
                        "        \"projectActiveStatus\": true,\n" +
                        "        \"department\": \"Agent and Partners\",\n" +
                        "        \"projectType\": \"TM\",\n" +
                        "        \"sbu\": \"GRM\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"createdAt\": null,\n" +
                        "        \"updatedAt\": null,\n" +
                        "        \"rfmProjectId\": \"1000021973LMTQ00000436\",\n" +
                        "        \"esaProjectId\": \"1000021973\",\n" +
                        "        \"esaProjectName\": \"LM QA Support\",\n" +
                        "        \"accountName\": \"Liberty Mutual Insurance Company\",\n" +
                        "        \"sowProjectId\": \"LMTQ00000436\",\n" +
                        "        \"sowProjectName\": \"GRM US BLT Quality Engineers\",\n" +
                        "        \"projectActiveStatus\": true,\n" +
                        "        \"department\": \"Policy Admin\",\n" +
                        "        \"projectType\": \"TM\",\n" +
                        "        \"sbu\": \"GRM\"\n" +
                        "    }\n" +
                        "]";

        // Set the Mock response on ntrigger of method under test
        Mockito.when(projectController.createBatch(projectlist)).thenReturn(projectlist);


        // Make a POST Request
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/projects/bulkUpload")
                .accept(MediaType.APPLICATION_JSON)
                .content(mockRequest)
                .contentType(MediaType.APPLICATION_JSON);

        //Capture the Actual Response from Mock MVC
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();


        System.out.println(mvcResult.getResponse().getContentAsString());
        //Assert Actual Vs the Expected Response
//        System.out.println("**************************"+mvcResult.getResponse().getContentAsString());
        assertEquals(expectedRespose, mvcResult.getResponse().getContentAsString(), false);


    }


}
