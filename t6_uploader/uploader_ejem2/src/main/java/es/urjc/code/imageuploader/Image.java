package es.urjc.code.imageuploader;

public class Image {

	private String description;
	private String fileName;

	public Image(String description, String fileName) {
		super();
		this.description = description;
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Image [description=" + description + ", fileName=" + fileName + "]";
	}

}
