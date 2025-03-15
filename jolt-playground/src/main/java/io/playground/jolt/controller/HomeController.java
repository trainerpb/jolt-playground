package io.playground.jolt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")

@Slf4j
@RequiredArgsConstructor
public class HomeController {

	private static final String VIEWNAME_HOME = "home.html";

	@GetMapping
	public String home() {
		return VIEWNAME_HOME;
	}
}
