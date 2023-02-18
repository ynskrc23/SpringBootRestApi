package com.restapi.springbootrestapi.service.impl;

import com.restapi.springbootrestapi.entity.Comment;
import com.restapi.springbootrestapi.entity.Post;
import com.restapi.springbootrestapi.entity.dto.CommentDTO;
import com.restapi.springbootrestapi.errors.ResourceNotFoundException;
import com.restapi.springbootrestapi.repository.CommentRepository;
import com.restapi.springbootrestapi.repository.PostRepository;
import com.restapi.springbootrestapi.service.CommentService;
import com.restapi.springbootrestapi.utils.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public List<CommentResponseDTO> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(c -> mapperToCommentDTO(c)).collect(Collectors.toList());
    }

    @Override
    public Optional<CommentResponseDTO> findById(Long id) {
        commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found id:"+id));
        Comment comment =  commentRepository.findById(id).get();
        return Optional.of(mapperToCommentDTO(comment));
    }

    @Override
    public CommentResponseDTO save(CommentDTO dto) {
        Post p = postRepository.findById(dto.getPostId())
                .orElseThrow(()-> new ResourceNotFoundException("Post not found id:"+dto.getPostId()));

        Comment cmt = new Comment();
        cmt.setName(dto.getName());
        cmt.setEmail(dto.getEmail());
        cmt.setBody(dto.getBody());
        cmt.setPost(p);
        Comment savedComment = commentRepository.save(cmt);

        return mapperToCommentDTO(savedComment);
    }

    @Override
    public CommentResponseDTO update(CommentDTO dto, Long id) {
        commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found id:"+id));

        Post p = postRepository.findById(dto.getPostId())
                .orElseThrow(()-> new ResourceNotFoundException("Post not found id:"+dto.getPostId()));

        Comment comment = commentRepository.findById(id).get();
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        comment.setName(dto.getName());
        comment.setPost(p);
        return mapperToCommentDTO(commentRepository.save(comment));
    }

    @Override
    public String delete(Long id) {
        commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found id:"+id));

        commentRepository.deleteById(id);
        return "success";
    }

    public static CommentResponseDTO mapperToCommentDTO(Comment comment){
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        dto.setPost(comment.getPost());
        return dto;
    }
}
