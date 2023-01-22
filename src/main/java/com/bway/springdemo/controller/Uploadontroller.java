package com.bway.springdemo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Uploadontroller {
	@GetMapping("/upload")
	public String getForm() {
		return "UploadForm";
	}
	@PostMapping("/upload")
	public String saveImage(@RequestParam MultipartFile image, Model model) {
		
		if(!image.isEmpty()) {
			
			try {
				Files.copy(image.getInputStream(), Path.of("src/main/resources/static/images/"+image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
				model.addAttribute("message", "upload success");
				return "UploadForm";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("message", "upload Failed");

		return "UploadForm";
	}

}
