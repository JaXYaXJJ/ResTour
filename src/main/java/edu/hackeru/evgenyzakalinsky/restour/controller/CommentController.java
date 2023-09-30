package edu.hackeru.evgenyzakalinsky.restour.controller;

import edu.hackeru.evgenyzakalinsky.restour.dto.CommentRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.CommentResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@SecurityRequirement(
        name = "Bearer Authentication"
)
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/packages/{id}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable(name = "id") long packageId,
            @RequestBody CommentRequestDto dto,
            UriComponentsBuilder uriBuilder,
            Authentication authentication
            ) {
        var saved = commentService.createComment(
                packageId, dto, authentication
        );
        var uri = uriBuilder
                .path("/api/v1/packages/{id}/{comment_id")
                .buildAndExpand(packageId, saved.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(saved);
    }

    @GetMapping("packages/{id}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByPackageId(
            @PathVariable(name = "id") long packageId
    ) {
        return ResponseEntity.ok(commentService.getCommentsByPackageId(packageId));
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateCommentById(
            @PathVariable(name = "id") long commentId,
            @RequestBody CommentRequestDto dto,
            Authentication authentication
    ) {
        return ResponseEntity.ok(commentService.updateComment(commentId, dto, authentication));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> deleteCommentById(
            @PathVariable(name = "id") long commentId,
            Authentication authentication
    ) {
        return ResponseEntity.ok(commentService.deleteComment(commentId, authentication));
    }

    @GetMapping("{user}/comments")
    public ResponseEntity<List<CommentResponseDto>> getCommentByUser(
            @PathVariable(name = "user") String userEmail
    ) {
        return ResponseEntity.ok(commentService.getCommentByUserEmail(userEmail));
    }
}
