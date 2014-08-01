package uk.co.wowcher.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Describes an image which is part of a User Image Gallery 
 * 
 * @author francesco.bianchi
 */
@JsonIgnoreProperties({ "bytes" })
public class ImageGalleryFile {

	private Long id;
	private String fileName;
	private Long fileSize;
	private String imageFormat;
	private Boolean useFilenameAsDefault;
	private String altTag;
	private String caption;
	
	private byte[] bytes;

	/**
	 * Getter for the ID of the file
	 * 
	 * @return the ID of the file
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for the ID of the file
	 * 
	 * @param id the value to set for the ID of the file
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Getter for the name of the file
	 * 
	 * @return the name of the file
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Setter for the name of the file
	 * 
	 * @param fileName the name of the file
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Getter for the size of the file
	 * 
	 * @return the size of the file (in bytes)
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/**
	 * Setter for the size of the file
	 * 
	 * @param fileSize the size of the file (in bytes)
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Getter for the MIME type describing the format of the image
	 * 
	 * @return the format of the image
	 */
	public String getImageFormat() {
		return imageFormat;
	}

	/**
	 * Setter for the MIME type code describing the format of the image
	 * 
	 * @param imageFormat the MIME type code for the format of the image
	 */
	public void setImageFormat(String imageFormat) {
		this.imageFormat = imageFormat;
	}

	/**
	 * Getter for content of the file
	 * 
	 * @return the content of the file
	 */
	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * Setter for the binary content of the image
	 * 
	 * @param bytes a binary representation of the content of the image
	 */
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	/**
	 * Tells if the name of the file must be used also for the alternative tag and the caption 
	 * 
	 * @return <i>true</i> if the name of the file must be used also the the alternative tag and the caption, <i>false</i> otherwise
	 */
	public Boolean getUseFilenameAsDefault() {
		return useFilenameAsDefault;
	}

	/**
	 * Setter for the flag telling if the name of the file should be user also for the alt tag and caption or not
	 * 
	 * @param useFilenameAsDefault <i>true</i> if the name of the file must be used also the the alternative tag and the caption, <i>false</i> otherwise
	 */
	public void setUseFilenameAsDefault(Boolean useFilenameAsDefault) {
		this.useFilenameAsDefault = useFilenameAsDefault;
	}

	/**
	 * Getter for the string to use as an alternative tag when rendering the image in a page
	 * 
	 * @return the string to use in the alt tag
	 */
	public String getAltTag() {
		return altTag;
	}

	/**
	 * Setter for the string to use as an alternative tag when rendering the image in a page
	 * 
	 * @param altTag the string to use in the alt tag
	 */
	public void setAltTag(String altTag) {
		this.altTag = altTag;
	}

	/**
	 * Getter for the string to use as a title to describe the image 
	 * 
	 * @return the string to use as a title to describe the image
	 */
	public String getCaption() {
		return caption;
	}

	/**
	 * Setter for the string to use as a title to describe the image 
	 * 
	 * @param caption the string to use as a title to describe the image
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

}
