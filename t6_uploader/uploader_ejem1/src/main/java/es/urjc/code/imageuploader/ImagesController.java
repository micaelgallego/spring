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

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImagesController {

	private static final Path FILES_FOLDER = Paths.get("files22");

	private List<String> images = new ArrayList<>();

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/image/upload", method = RequestMethod.POST)
	public ModelAndView handleFileUpload(@RequestParam("imageTitle") String imageTitle,
			@RequestParam("file") MultipartFile file) {

		String fileName = "image-" + images.size() + ".jpg";

		if (!file.isEmpty()) {

			try {

				if (!Files.exists(FILES_FOLDER)) {
					Files.createDirectories(FILES_FOLDER);
				}

				File uploadedFile = new File(FILES_FOLDER.toAbsolutePath().toString(), fileName);
				file.transferTo(uploadedFile);

				images.add(imageTitle);

				return new ModelAndView("index").addObject("imageTitles", images);

			} catch (Exception e) {
				e.printStackTrace();
				return new ModelAndView("index").addObject("fileName", fileName).addObject("error",
						e.getClass().getName() + ":" + e.getMessage());
			}

		} else {
			return new ModelAndView("index").addObject("error", "The file is empty");
		}
	}

	// NOTE: The url format "/images/{fileName:.+}" avoid Spring MVC remove file
	// extension.

	@RequestMapping("/image/{fileName:.+}")
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