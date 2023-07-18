package edu.hackeru.evgenyzakalinsky.restour.controller;

import edu.hackeru.evgenyzakalinsky.restour.dto.CommentRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.CommentResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/packages/{id}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable(name = "id") long packageId,
            @RequestBody CommentRequestDto dto,
            UriComponentsBuilder uriBuilder
            ) {
        var saved = commentService.createComment(
                packageId, dto
        );
        var uri = uriBuilder
                .path("/api/v1/packages/{id}/{comment_id")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(saved);
    }

    @GetMapping("packages/{id}/comments")
    public  ResponseEntity<List<CommentResponseDto>> getCommentsByPackageId(
            @PathVariable(name = "id") long packageId
    ) {

        return ResponseEntity.ok(commentService.getCommentsByPackageId(packageId));
    }

    @PutMapping("/comments/{id}")
    public  ResponseEntity<CommentResponseDto> updateCommentById(
            @PathVariable(name = "id") long commentId,
            @RequestBody CommentRequestDto dto
    ) {

        return ResponseEntity.ok(commentService.updateComment(commentId, dto));
    }
}
