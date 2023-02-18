package com.restapi.springbootrestapi.entity.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private String name;

    private String email;

    private String body;

    private Long postId;
}
