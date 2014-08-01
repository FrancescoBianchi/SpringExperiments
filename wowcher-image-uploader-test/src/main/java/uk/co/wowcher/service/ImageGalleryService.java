package uk.co.wowcher.service;

import uk.co.wowcher.exception.ApplicationException;
import uk.co.wowcher.model.ImageGalleryFile;

/**
 * Exposes all the available operations to with a User Image Gallery and its contents.
 * <p>Every method assumes that:
 * <ul>
 * <li>Every user has his/her own Gallery (which can be empty)</li>
 * <li>A gallery belongs to one and only one user</li>
 * <li>We can determine which is the user bound to the current session and thread (e.g. via SpringSecurity)</li>
 * </ul> 
 * 
 * @author francesco.bianchi
 */
public interface ImageGalleryService {

	/**
	 * Saves the provided file in the Gallery bound to the user holding the current session 
	 * 
	 * @param file the image to save
	 * @return the saved file with the ID set with the assigned value 
	 * @throws ApplicationException if the file could not be saved.
	 */
	ImageGalleryFile saveImageToCurrentUserGallery(ImageGalleryFile file) throws ApplicationException;
	
}
