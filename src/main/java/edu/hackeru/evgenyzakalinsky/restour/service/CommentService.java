package edu.hackeru.evgenyzakalinsky.restour.service;

import edu.hackeru.evgenyzakalinsky.restour.dto.CommentRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.CommentResponseDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CommentService {
    CommentResponseDto createComment(long packageId, CommentRequestDto dto, Authentication authentication);
    List<CommentResponseDto> getCommentsByPackageId(long packageId);
    CommentResponseDto updateComment(long commentId, CommentRequestDto dto, Authentication authentication);
    CommentResponseDto deleteComment(long commentId, Authentication authentication);
    List<CommentResponseDto> getCommentByUserEmail(String userEmail);
}
