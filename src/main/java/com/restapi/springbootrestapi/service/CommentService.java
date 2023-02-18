package com.restapi.springbootrestapi.service;

import com.restapi.springbootrestapi.entity.dto.CommentDTO;
import com.restapi.springbootrestapi.utils.response.CommentResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    List<CommentResponseDTO> getAll();

    Optional<CommentResponseDTO> findById(Long id);

    CommentResponseDTO save(CommentDTO dto);

    CommentResponseDTO update(CommentDTO dto, Long id);

    String delete(Long id);
}
