package com.restapi.springbootrestapi.repository.impl;

import com.restapi.springbootrestapi.entity.Post;
import com.restapi.springbootrestapi.utils.PageDTO;
import com.restapi.springbootrestapi.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    private final EntityManager entityManager;
    @Override
    public PageDTO findAllWithCustomPage(int size, int page, String direction, String properties, String content, String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> count = cb.createQuery(Long.class);

        count.select(cb.count(count.from(Post.class)));

        Long totalElelments = entityManager.createQuery(count).getSingleResult();

        CriteriaQuery<Post> getPostQuery = cb.createQuery(Post.class);

        Root<Post> from = getPostQuery.from(Post.class);

        CriteriaQuery<Post> select = getPostQuery.select(from);

        List<Predicate> predicates = new ArrayList<>();

        if(!ObjectUtils.isEmpty(content)){
            predicates.add(cb.like(from.get("content"),"%" + content + "%"));
        }

        if(!ObjectUtils.isEmpty(title)){
            predicates.add(cb.like(from.get("title"),"%" + title + "%"));
        }

        select.select(from).where(predicates.toArray(new Predicate[]{}));

        if(direction.equalsIgnoreCase("desc") && !ObjectUtils.isEmpty(properties)){
            getPostQuery.orderBy(cb.desc(from.get(properties)));
        }
        else if(direction.equalsIgnoreCase("asc") && !ObjectUtils.isEmpty(properties)) {
            getPostQuery.orderBy(cb.asc(from.get(properties)));
        }

        TypedQuery<Post> typedQuery = entityManager.createQuery(select);

        int offSet = (page - 1) * size;
        typedQuery.setFirstResult(offSet);
        typedQuery.setMaxResults(size);

        PageDTO<Post> pageDTO = PageUtils.calculatePage(size, page, typedQuery.getResultList().size());
        pageDTO.setData(typedQuery.getResultList());

        return pageDTO;
    }
}
