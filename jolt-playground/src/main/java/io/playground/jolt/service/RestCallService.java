package io.playground.jolt.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class RestCallService {

	public ResponseEntity<String> callService(String fullUrlString, String httpMethodString,
			Map<String, List<String>> httpHeaders, String httpRequestPayloadBody, Object... uriVars) throws Exception {
		HttpMethod httpMethod = HttpMethod.valueOf(httpMethodString);

		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> httpHeaderMultiValueMap = new LinkedMultiValueMap<>(httpHeaders);

		HttpHeaders headers = new HttpHeaders(httpHeaderMultiValueMap);
		HttpEntity<String> httpRequestEntity = StringUtils.hasText(httpRequestPayloadBody)
				? new HttpEntity<String>(httpRequestPayloadBody, headers)
				: new HttpEntity<>(headers);
		return restTemplate.exchange(fullUrlString, httpMethod, httpRequestEntity, String.class, uriVars);
	}
}
