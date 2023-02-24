package com.restapi.springbootrestapi.repository;

import com.restapi.springbootrestapi.entity.Comment;
import com.restapi.springbootrestapi.utils.response.CommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAll();
    List<Comment> findAllComments();
    Comment findByCommentId(Long Id);
    List<Comment> findWithLike(String name);
    @Query("select c from Comment c where c.name like %?1%")
    List<Comment> findWithLikeJPA(String name);
    List<Comment> findAllWithNativeQuery();
    Comment findByIdWithNativeQuery(Long Id);
    @Query(value = "select c.* from Comment c where c.comment_id = ?1", nativeQuery = true)
    Comment findByIdWithJPA(Long Id);
    @Query(value="select c.comment_id as commentId, c.name as commentName, c.email as commentEmail, c.body as commentBody" + " from comment c ", nativeQuery = true)
    List<CommentProjection> findAllWithProjection();
}
