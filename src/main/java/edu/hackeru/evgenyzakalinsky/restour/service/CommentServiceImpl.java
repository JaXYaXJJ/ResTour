package edu.hackeru.evgenyzakalinsky.restour.service;

import edu.hackeru.evgenyzakalinsky.restour.dto.CommentRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.CommentResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.entity.Comment;
import edu.hackeru.evgenyzakalinsky.restour.error.PackageNotFoundException;
import edu.hackeru.evgenyzakalinsky.restour.repository.CommentRepository;
import edu.hackeru.evgenyzakalinsky.restour.repository.PackageRepository;
import edu.hackeru.evgenyzakalinsky.restour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PackageRepository packageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentResponseDto createComment(long packageId, CommentRequestDto dto, Authentication authentication) {

        var pack = packageRepository
                .findById(packageId)
                .orElseThrow(
                        () -> new PackageNotFoundException("Package", packageId)
                );

        var user = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow();
        var comment = modelMapper.map(dto, Comment.class);
        comment.setPack(pack);
        comment.setUser(user);

        var saved = commentRepository.save(comment);

        return modelMapper.map(saved, CommentResponseDto.class);
    }

    @Override
    public List<CommentResponseDto> getCommentsByPackageId(long packageId) {

        var comments = commentRepository.findCommentsByPackId(packageId);

        return comments
                .stream()
                .map(c -> modelMapper.map(c, CommentResponseDto.class))
                .toList();
    }

    @Override
    public CommentResponseDto updateComment(long commentId, CommentRequestDto dto) {

        var commentFromDb = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new PackageNotFoundException(
                        "Comment", commentId
                ));

        commentFromDb.setComment(dto.getComment());

        var saved = commentRepository.save(commentFromDb);

        return modelMapper.map(saved, CommentResponseDto.class);
    }

    @Override
    public CommentResponseDto deleteComment(long commentId) {

        var deletedComment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new PackageNotFoundException(
                        "Comment", commentId
                ));

        commentRepository.deleteById(commentId);

        return modelMapper.map(deletedComment, CommentResponseDto.class);
    }
}
