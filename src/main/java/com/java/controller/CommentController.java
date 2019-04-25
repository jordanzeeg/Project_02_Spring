package com.java.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.java.dto.Comment;
import com.java.dto.Friend;
import com.java.service.CommentService;


@Controller
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	CommentService service;

	//@GetMapping TODO: - GetAllComments

	//@GetMapping("/getcommentsbypost{id}") TODO: - getCommentsByPostId


	//@GetMapping("/get/byid{id}") TODO: - getCommentById

	//@PostMapping("/save") TODO: - saveComment


	//@PostMapping("/update") TODO: - updateComment


	//@PostMapping("/delete") TODO: - deleteComment

}
