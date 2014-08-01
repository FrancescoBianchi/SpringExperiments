package uk.co.wowcher.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import uk.co.wowcher.model.ImageGalleryFile;


@Controller
@RequestMapping("imageGallery")
public class ImageGalleryController {

    Map<Long, ImageGalleryFile> files = new HashMap<Long, ImageGalleryFile>();
    Long counter = 0L;
    
    @RequestMapping(value="/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public @ResponseBody ImageGalleryFile upload(MultipartFile file,
    		@RequestParam(value = "useFilenameAsDefault") Boolean useFilenameAsDefault,
    		@RequestParam(value = "altTag") String altTag,
    		@RequestParam(value = "caption") String caption,
			HttpServletResponse response) {
    	ImageGalleryFile imageGalleryFile = null; 
    	
    	if (!file.isEmpty()) {
    		imageGalleryFile = new ImageGalleryFile();
    		counter++;
    		imageGalleryFile.setId(counter);
    		imageGalleryFile.setFileName(file.getOriginalFilename());
	        imageGalleryFile.setFileSize(file.getSize());
	        imageGalleryFile.setImageFormat(file.getContentType());
	        imageGalleryFile.setUseFilenameAsDefault(useFilenameAsDefault);
	        imageGalleryFile.setAltTag(altTag);
	        imageGalleryFile.setCaption(caption);
	        
	        files.put(imageGalleryFile.getId(), imageGalleryFile);
    	} else {
    		//FIXME
    	}
    	
    	return imageGalleryFile;
    }
    
//    /***************************************************
//     * URL: /api/imageGallery/get/{value}
//     * get(): get file as an attachment
//     * @param response : passed by the server
//     * @param value : value from the URL
//     * @return void
//     ****************************************************/
//    @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
//     public void get(HttpServletResponse response, @PathVariable String value){
//         FileUploadDescriptor requestedImage = files.get(Integer.parseInt(value));
//         try {      
//                response.setContentType(requestedImage.getFileType());
//                response.setHeader("Content-disposition", "attachment; filename=\""+requestedImage.getFileName()+"\"");
//                FileCopyUtils.copy(requestedImage.getBytes(), response.getOutputStream());
//         }catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//         }
//     }
    
}