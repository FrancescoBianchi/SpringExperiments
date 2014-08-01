package uk.co.wowcher.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import uk.co.wowcher.config.Config;
import uk.co.wowcher.config.WebInitializer;
import uk.co.wowcher.config.WebMvcConfiguration;

/**
 * Test class for <code>ImageGalleryController</code>
 * 
 * @author francesco.bianchi
 */
@WebAppConfiguration
@ContextConfiguration(classes = {Config.class, WebInitializer.class, WebMvcConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class ImageGalleryControllerTest {

	@Autowired
    private WebApplicationContext webApplicationContext;
	
	private String urlToInvoke = "/imageGallery/upload";
	
    @Test
    public void wrongUploadUrl() throws Exception {
        MockMultipartFile multipartFile = getDummyFileToUpload();
        
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MediaType mediaType = new MediaType("multipart", "form-data");

        mockMvc.perform(
        		MockMvcRequestBuilders.fileUpload(urlToInvoke + "wrongUrlPart")
                .content(multipartFile.getBytes())
                .contentType(mediaType))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void uploadWithMissingParameters() throws Exception {
        MockMultipartFile multipartFile = getDummyFileToUpload();
        
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MediaType mediaType = new MediaType("multipart", "form-data");

        mockMvc.perform(
        		MockMvcRequestBuilders.fileUpload(urlToInvoke)
                .content(multipartFile.getBytes())
                .contentType(mediaType))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void uploadWithWrongMediaType() throws Exception {
        MockMultipartFile multipartFile = getDummyFileToUpload();
        
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MediaType mediaType = new MediaType("text", "json");

        mockMvc.perform(
        		MockMvcRequestBuilders.fileUpload(urlToInvoke)
                .content(multipartFile.getBytes())
                .contentType(mediaType))
                .andExpect(status().isUnsupportedMediaType());
    }
    
    private MockMultipartFile getDummyFileToUpload() {
    	return new MockMultipartFile("filename", "filename.jpg", "image/jpeg", "someRandomChars".getBytes());
    }
    
}