package uk.co.wowcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import uk.co.wowcher.exception.ApplicationException;
import uk.co.wowcher.model.ImageGalleryFile;
import uk.co.wowcher.service.ImageGalleryService;


@Controller
@RequestMapping("imageGallery")
public class ImageGalleryController {

	@Autowired
	ImageGalleryService imageGalleryService;
	
    @RequestMapping(value="/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public @ResponseBody ResponseEntity<?> upload(MultipartFile file,
    		@RequestParam(value = "useFilenameAsDefault") Boolean useFilenameAsDefault,
    		@RequestParam(value = "altTag") String altTag,
    		@RequestParam(value = "caption") String caption) {
    	ImageGalleryFile imageGalleryFile = new ImageGalleryFile();
		imageGalleryFile.setFileName(file.getOriginalFilename());
        imageGalleryFile.setFileSize(file.getSize());
        imageGalleryFile.setImageFormat(file.getContentType());
        imageGalleryFile.setUseFilenameAsDefault(useFilenameAsDefault);
        imageGalleryFile.setAltTag(altTag);
        imageGalleryFile.setCaption(caption);
        
        ResponseEntity<?> response = null;
        try {
        	imageGalleryFile = imageGalleryService.saveImageToCurrentUserGallery(imageGalleryFile);
        	response = new ResponseEntity<ImageGalleryFile>(imageGalleryFile, HttpStatus.OK);
        } catch (ApplicationException exception) {
        	response = new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception exception) {
        	response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return response;
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