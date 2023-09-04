package edu.hackeru.evgenyzakalinsky.restour.service;

import edu.hackeru.evgenyzakalinsky.restour.dto.CommentRequestDto;
import edu.hackeru.evgenyzakalinsky.restour.dto.CommentResponseDto;
import edu.hackeru.evgenyzakalinsky.restour.entity.Comment;
import edu.hackeru.evgenyzakalinsky.restour.error.BadRequestException;
import edu.hackeru.evgenyzakalinsky.restour.error.PackageNotFoundException;
import edu.hackeru.evgenyzakalinsky.restour.repository.CommentRepository;
import edu.hackeru.evgenyzakalinsky.restour.repository.PackageRepository;
import edu.hackeru.evgenyzakalinsky.restour.repository.RoleRepository;
import edu.hackeru.evgenyzakalinsky.restour.repository.UserRepository;
import jakarta.transaction.Transactional;
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
    private final RoleRepository roleRepository;

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
    @Transactional
    public CommentResponseDto updateComment(long commentId, CommentRequestDto dto, Authentication authentication) {

        var commentFromDb = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new PackageNotFoundException(
                        "Comment", commentId
                ));

        userHasPermissionToEditComment(authentication, commentFromDb);

        commentFromDb.setComment(dto.getComment());

        var saved = commentRepository.save(commentFromDb);
        return modelMapper.map(saved, CommentResponseDto.class);
    }

    private void userHasPermissionToEditComment(Authentication authentication, Comment commentFromDb) {
        var user = commentFromDb.getUser();

        var adminRole = roleRepository.findByRoleNameIgnoreCase("ROLE_ADMIN").orElseThrow();

        if (!user.getRoles().contains(adminRole)
                && !user.getEmail().equalsIgnoreCase(authentication.getName())) {
            throw new BadRequestException("user", "Comment must belong the editing  user");
        }
    }

    @Override
    @Transactional
    public CommentResponseDto deleteComment(long commentId, Authentication authentication) {

        var deletedComment = commentRepository
                .findById(commentId)
                .orElseThrow(() -> new PackageNotFoundException(
                        "Comment", commentId
                ));

        userHasPermissionToEditComment(authentication, deletedComment);

        commentRepository.deleteById(commentId);
        return modelMapper.map(deletedComment, CommentResponseDto.class);
    }

    @Override
    public List<CommentResponseDto> getCommentByUserEmail(String userEmail) {

        var comments = commentRepository.findCommentsByUserEmail(userEmail);

        return comments
                .stream()
                .map(c -> modelMapper.map(c, CommentResponseDto.class))
                .toList();
    }
}
