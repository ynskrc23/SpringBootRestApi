package com.restapi.springbootrestapi.utils.response;

import lombok.Data;
import java.util.Set;
@Data
public class PostResponseDTO {
    private Long id;

    private String title;

    private String description;

    private Integer maxiumOfComments;

    private String content;

    private String phoneNumber;

    private Set<CommentResponseDTO> comments;
}
