package com.restapi.springbootrestapi.utils.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.springbootrestapi.entity.Post;
import lombok.Data;

@Data
public class CommentResponseDTO {
    private Long id;

    private String name;

    private String email;

    private String body;

    @JsonIgnore
    private Post post;
}
