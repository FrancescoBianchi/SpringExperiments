package uk.co.wowcher.service.impl;

import org.springframework.stereotype.Service;

import uk.co.wowcher.model.ImageGalleryFile;
import uk.co.wowcher.service.ImageGalleryService;

@Service
public class ImageGalleryServiceMock implements ImageGalleryService {

	static Long counter = 0L;
	
	public ImageGalleryFile saveImageToCurrentUserGallery(ImageGalleryFile file) {
		file.setId(getNextSequence());
		return file;
	}
	
	private synchronized Long getNextSequence() {
		return counter++;
	}
	
}