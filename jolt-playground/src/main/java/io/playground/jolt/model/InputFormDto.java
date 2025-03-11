package io.playground.jolt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InputFormDto {

	private String input="";
	private String dsl="";
	private String output="";
}
