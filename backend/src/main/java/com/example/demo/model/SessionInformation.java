package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SessionInformation {
	private String expiresAt;
	private String idToken;
}
