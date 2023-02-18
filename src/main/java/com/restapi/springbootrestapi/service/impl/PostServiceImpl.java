package com.restapi.springbootrestapi.service.impl;

import com.restapi.springbootrestapi.entity.Post;
import com.restapi.springbootrestapi.errors.ResourceNotFoundException;
import com.restapi.springbootrestapi.repository.PostRepository;
import com.restapi.springbootrestapi.service.PostService;
import com.restapi.springbootrestapi.utils.request.PostDTO;
import com.restapi.springbootrestapi.utils.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.restapi.springbootrestapi.service.impl.CommentServiceImpl.mapperToCommentDTO;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    @Override
    public List<PostResponseDTO> getAll() {
        return postRepository.findAll().stream().map(p-> mapperToPostDTO(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<PostResponseDTO> findById(Long id) {
        postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found id:"+id));

        return Optional.of(mapperToPostDTO(postRepository.findById(id).get()));
    }

    @Override
    public PostResponseDTO save(PostDTO dto) {
        Post p = new Post();
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        p.setMaxiumOfComments(dto.getMaxiumOfComments());
        p.setContent(dto.getContent());

        Post saved = postRepository.save(p);
        return mapperToPostDTO(saved);
    }

    @Override
    public PostResponseDTO update(PostDTO dto, Long id) {
        Post p = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found id:"+id));
        p.setDescription(dto.getDescription());
        p.setTitle(dto.getTitle());
        p.setContent(dto.getContent());
        return mapperToPostDTO(p);
    }

    @Override
    public String delete(Long id) {
        postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Post not found id:"+id));

        postRepository.deleteById(id);
        return "Delete Success";
    }

    private PostResponseDTO mapperToPostDTO(Post entity){
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setMaxiumOfComments(entity.getMaxiumOfComments());
        dto.setContent(entity.getContent());

        if(entity.getComments() != null && entity.getComments().size() > 0){
            dto.setComments(entity.getComments().stream().map(c ->mapperToCommentDTO(c)).collect(Collectors.toSet()));
        }
        return dto;
    }
}
