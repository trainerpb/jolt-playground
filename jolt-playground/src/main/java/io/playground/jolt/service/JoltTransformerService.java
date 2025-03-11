package io.playground.jolt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JoltTransformerService {

	public String transform(String inputJson, String dsl) {
		log.debug("transform() :: received inputJson: {}", inputJson);
		log.debug("transform() :: received dsl: {}", dsl);
		Object jsonToObject = JsonUtils.jsonToObject(inputJson);
		List<Object> specsList = JsonUtils.jsonToList(dsl);
		Chainr chainr = Chainr.fromSpec(specsList);
		Object transformedObject = chainr.transform(jsonToObject);
		log.debug("tranform() :: output json :{} ", transformedObject);
		return JsonUtils.toJsonString(transformedObject);
	}

}
