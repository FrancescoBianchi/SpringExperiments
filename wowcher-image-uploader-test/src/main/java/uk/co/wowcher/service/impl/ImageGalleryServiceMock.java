package uk.co.wowcher.service.impl;

import org.springframework.stereotype.Service;

import uk.co.wowcher.exception.ApplicationException;
import uk.co.wowcher.model.ImageGalleryFile;
import uk.co.wowcher.service.ImageGalleryService;

/**
 * Provides a dummy implementation for {@link ImageGalleryService} 
 * 
 * @author francesco.bianchi
 */
@Service
public class ImageGalleryServiceMock implements ImageGalleryService {

	static Long counter = 0L;
	
	/* (non-Javadoc)
	 * @see uk.co.wowcher.service.ImageGalleryService#saveImageToCurrentUserGallery(uk.co.wowcher.model.ImageGalleryFile)
	 */
	public ImageGalleryFile saveImageToCurrentUserGallery(ImageGalleryFile file) throws ApplicationException {
		file.setId(getNextSequence());
		return file;
	}
	
	private synchronized Long getNextSequence() {
		return counter++;
	}
	
}