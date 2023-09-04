package edu.hackeru.evgenyzakalinsky.restour.repository;

import edu.hackeru.evgenyzakalinsky.restour.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment, Long> {
    List<Comment> findCommentsByPackId(long packageId);
    List<Comment> findCommentsByUserEmail(String userEmail);
}
