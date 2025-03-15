package io.playground.jolt.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpRequesResponsetModel {
	private String error;
	private String fullUrlString;
	private String httpMethodString;
	private Map<String, List<String>> requestHeaders = new HashMap<>();
	private String httpRequestPayloadBody;

	private String status;
	private String httpResponsePayloadBody;
	private Map<String, List<String>> responseHeaders = new HashMap<>();
}
