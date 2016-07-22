package es.urjc.code.imageuploader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
public class ImagesController {

	private static final Path FILES_FOLDER = Paths.get("files");

	private List<Image> images = new ArrayList<>();

	@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
	public Image handleFileUpload(@RequestParam String description, @RequestParam MultipartFile file) throws IOException {

		if (file.isEmpty()) {
			throw new RuntimeException("The file is empty");
		}

		if (!Files.exists(FILES_FOLDER)) {
			Files.createDirectories(FILES_FOLDER);
		}

		String fileName = "image-" + images.size() + ".jpg";
		File uploadedFile = new File(FILES_FOLDER.toAbsolutePath().toString(), fileName);
		file.transferTo(uploadedFile);

		Image image = new Image(description, fileName);

		images.add(image);

		return image;
	}

	@RequestMapping("/images")
	public List<Image> getImages() {
		return images;
	}

	//NOTE: The url format "/images/{fileName:.+}" avoid Spring MVC remove file extension.
	
	@RequestMapping("/images/{fileName:.+}")
	public void handleFileDownload(@PathVariable String fileName, HttpServletResponse res)
			throws FileNotFoundException, IOException {
		
		Path image = FILES_FOLDER.resolve(fileName);

		if (Files.exists(image)) {
			res.setContentType("image/jpeg");
			res.setContentLength((int) image.toFile().length());
			FileCopyUtils.copy(Files.newInputStream(image), res.getOutputStream());
			
		} else {
			res.sendError(404, "File" + fileName + "(" + image.toAbsolutePath() + ") does not exist");
		}
	}

}