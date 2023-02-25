package com.restapi.springbootrestapi.repository.impl;

import com.restapi.springbootrestapi.utils.PageDTO;

public interface PostRepositoryCustom {
    PageDTO findAllWithCustomPage(int size, int page, String direction, String properties, String content, String title);
}
