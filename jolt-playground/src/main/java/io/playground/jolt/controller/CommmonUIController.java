package io.playground.jolt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.playground.jolt.model.HttpRequesResponsetModel;
import io.playground.jolt.service.RestCallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/common-ui")

@Slf4j
@RequiredArgsConstructor
public class CommmonUIController {

	private static final String VIEWNAME_JSON_TREE = "jsontree.html";
	private static final String VIEWNAME_REST_UI = "restui.html";
	private static final String INPUT_FORM_DTO = "dto";

	private final RestCallService restCallService;

	@GetMapping("/jsontree")
	public ModelAndView userInputForm() {
		ModelAndView modelAndView = new ModelAndView(VIEWNAME_JSON_TREE);

		return modelAndView;
	}

	@GetMapping("rest-ui")
	public ModelAndView restUserInputForm() {
		ModelAndView modelAndView = new ModelAndView(VIEWNAME_REST_UI);
		modelAndView.addObject("dto", new HttpRequesResponsetModel());
		return modelAndView;
	}

	@PostMapping("/rest-ui")
	public String handleFormSubmission(@ModelAttribute(name = INPUT_FORM_DTO) HttpRequesResponsetModel httpModel) {
		try {
			Map<String,List<String>> headerMap=new HashMap<>();
			httpModel.getRequestHeaders().values().forEach(li->{
				headerMap.putIfAbsent(li.get(0), new ArrayList<>());
				headerMap.get(li.get(0)).add(li.get(1));
				
			});
			httpModel.setRequestHeaders(headerMap);
			var responeEntity = restCallService.callService(httpModel.getFullUrlString(),
					httpModel.getHttpMethodString(), httpModel.getRequestHeaders(),
					httpModel.getHttpRequestPayloadBody());
			var statusCode = responeEntity.getStatusCode();
			var responseBody = responeEntity.getBody();
			httpModel.setStatus(statusCode.toString());
			httpModel.setHttpResponsePayloadBody(responseBody);
			httpModel.setResponseHeaders(responeEntity.getHeaders().entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
			;
			httpModel.setError(null);
		} catch (Exception e) {
			log.error("handleFormSubmission :: Error --->",e);
			httpModel.setError(e.getMessage());
		}
		return VIEWNAME_REST_UI; // Return a result.html template to display the submitted data
	}
}
