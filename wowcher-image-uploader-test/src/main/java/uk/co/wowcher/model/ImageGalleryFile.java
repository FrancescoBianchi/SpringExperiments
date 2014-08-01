package uk.co.wowcher.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getImageFormat() {
		return imageFormat;
	}

	public void setImageFormat(String imageFormat) {
		this.imageFormat = imageFormat;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public Boolean getUseFilenameAsDefault() {
		return useFilenameAsDefault;
	}

	public void setUseFilenameAsDefault(Boolean useFilenameAsDefault) {
		this.useFilenameAsDefault = useFilenameAsDefault;
	}

	public String getAltTag() {
		return altTag;
	}

	public void setAltTag(String altTag) {
		this.altTag = altTag;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

}
