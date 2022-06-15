package de.cl.multipartfile.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;


import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

     //C:\Users\Public\Desktop
     String fileSeparator = FileSystems.getDefault().getSeparator();
     Path path = Paths.get("C:\\test\\");
//     String a = File.separator;

     String UPLOAD_DIR = System.getProperty("user.home") + fileSeparator + "OneDrive" + fileSeparator + "Desktop" + fileSeparator;
//   String UPLOAD_DIR = System.getenv("user.home") + fileSeparator + "OneDrive" + fileSeparator + "Desktop" + fileSeparator;


    @GetMapping("/")
    public String homepage() {
//        System.out.println(UPLOAD_DIR);
//        System.out.println(System.getenv());
//        System.out.println(System.getenv("JAVA_HOME"));
//        System.out.println(fileSeparator);
//        System.out.println(a);
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        // normalize the file path

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "redirect:/";
    }

}