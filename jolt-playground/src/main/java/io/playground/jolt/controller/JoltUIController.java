package io.playground.jolt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.playground.jolt.model.InputFormDto;
import io.playground.jolt.service.JoltTransformerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/jolt-ui")

@Slf4j
@RequiredArgsConstructor
public class JoltUIController {

	private static final String VIEWNAME_INPUT_FORM = "inputForm";

	private static final String INPUT_FORM_DTO = "dto";

	private final JoltTransformerService joltTransformerService;

	@PostMapping
	public ModelAndView tranform(@ModelAttribute(name=INPUT_FORM_DTO) InputFormDto inputFormDto) {
		var inputJson = inputFormDto.getInput();
		var dsl = inputFormDto.getDsl();
		ModelAndView modelAndView = new ModelAndView(VIEWNAME_INPUT_FORM);
		try {
			var output = joltTransformerService.transform(inputJson, dsl);
			inputFormDto.setOutput(output);
		} catch (Exception e) {
			log.error("transform() :: exception occurred ", e);
			modelAndView.addObject("success", false);
			modelAndView.addObject("error", e.getMessage());
		}
		return modelAndView;
	}

	@GetMapping
	public ModelAndView userInputForm() {
		ModelAndView modelAndView = new ModelAndView(VIEWNAME_INPUT_FORM);
		modelAndView.addObject(INPUT_FORM_DTO, new InputFormDto());
		return modelAndView;
	}

}
