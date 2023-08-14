package edu.hackeru.evgenyzakalinsky.restour.service;

import edu.hackeru.evgenyzakalinsky.restour.dto.CommentRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto createComment(long packageId, CommentRequestDto dto);
    List<CommentResponseDto> getCommentsByPackageId(long packageId);
    CommentResponseDto updateComment(long commentId, CommentRequestDto dto);
    CommentResponseDto deleteComment(long commentId);
}
