package edu.hackeru.evgenyzakalinsky.restour.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/docs")
public class DocsController {

    @GetMapping
    ResponseEntity<Object> getDocs() {

        return ResponseEntity.ok(Map.of("message", "docs"));
    }
}
