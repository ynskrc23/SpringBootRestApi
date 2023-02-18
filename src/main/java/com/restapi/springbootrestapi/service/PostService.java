package com.restapi.springbootrestapi.service;

import com.restapi.springbootrestapi.utils.request.PostDTO;
import com.restapi.springbootrestapi.utils.response.PostResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<PostResponseDTO> getAll();

    Optional<PostResponseDTO> findById(Long id);

    PostResponseDTO save(PostDTO dto);

    PostResponseDTO update(PostDTO dto, Long id);

    String delete(Long id);
}
