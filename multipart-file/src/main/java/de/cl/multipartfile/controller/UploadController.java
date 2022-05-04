package de.cl.multipartfile.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

@GetMapping("")
    public String showFileUploader() {
        return "index";
}
@PostMapping ("/save")
	public String fileUpload () throws IllegalStateException, IOException {
	
		MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());
		File file = new File("src/main/resources/targetFile.tmp");
		multipartFile.transferTo(file);

		return "index";
		}
	}

