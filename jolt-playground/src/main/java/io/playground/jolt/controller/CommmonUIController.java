package io.playground.jolt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/common-ui")

@Slf4j
@RequiredArgsConstructor
public class CommmonUIController {

	private static final String VIEWNAME_JSON_TREE = "jsontree.html";

	@GetMapping
	public ModelAndView userInputForm() {
		ModelAndView modelAndView = new ModelAndView(VIEWNAME_JSON_TREE);

		return modelAndView;
	}

}
