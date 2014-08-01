package uk.co.wowcher.service;

import uk.co.wowcher.model.ImageGalleryFile;

public interface ImageGalleryService {

	ImageGalleryFile saveImageToCurrentUserGallery(ImageGalleryFile file);
	
}
