package com.kafka.atiperaproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record ErrorResponse(String code, String message) {}

