package com.restapi.springbootrestapi.controller;

import com.restapi.springbootrestapi.service.PostService;
import com.restapi.springbootrestapi.utils.ValidateObject;
import com.restapi.springbootrestapi.utils.request.PostDTO;
import com.restapi.springbootrestapi.utils.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllPost(){
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody PostDTO dto){
        Map<String, String> errorValidator = ValidateObject.validatePostDTO(dto);
        if(!ObjectUtils.isEmpty(errorValidator)){
            return new ResponseEntity<>(errorValidator, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(postService.save(dto), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestParam("idToUpdate") Long id, @RequestBody PostDTO dto){
        return new ResponseEntity<>(postService.update(dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam("idToDelete") Long id){
        return new ResponseEntity<>(postService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable("id") Long id){
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }
}
