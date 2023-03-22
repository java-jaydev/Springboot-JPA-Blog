package com.aaron.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.aaron.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

    public final String TAG = "BoardController: ";
    Logger logger = LoggerFactory.getLogger(BoardController.class);

    // @AuthenticationPrincipal PrincipalDetail principal
	@GetMapping({"", "/"})
	public String index() {
		return "index";
	}
	
	// USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
}
