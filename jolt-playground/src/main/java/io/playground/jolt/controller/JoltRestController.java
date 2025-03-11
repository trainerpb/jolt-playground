package io.playground.jolt.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.playground.jolt.model.InputFormDto;
import io.playground.jolt.service.JoltTransformerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/jolt-rest")

@Slf4j
@RequiredArgsConstructor
public class JoltRestController {

	private final JoltTransformerService joltTransformerService;

	@PostMapping
	public String tranform(@ModelAttribute InputFormDto inputFormDto) {
		var inputJson = inputFormDto.getInput();
		var dsl = inputFormDto.getDsl();

		try {
			var output = joltTransformerService.transform(inputJson, dsl);
			return output;
		} catch (Exception e) {
			log.error("transform() :: exception occurred ", e);
			return "Error occurred: " + e.getMessage();
		}

	}
}
