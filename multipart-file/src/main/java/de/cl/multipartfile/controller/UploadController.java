package de.cl.multipartfile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UploadController {

	@GetMapping("")
    public String showUebungenList1(Model model) {
        return "index";
}
}
