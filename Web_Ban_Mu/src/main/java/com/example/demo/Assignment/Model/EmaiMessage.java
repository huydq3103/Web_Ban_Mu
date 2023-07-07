package com.example.demo.Assignment.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmaiMessage {
    private String to;
    private String subject;
    private String message;
}
