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

import com.java.dto.Friend;
import com.java.dto.CommentLike;
import com.java.service.CommentLikeService;
import com.java.service.FriendService;

@Controller
@RequestMapping("/commentlikes")
public class CommentLikeController {
	@Autowired
	CommentLikeService service;

	//@GetMapping getCommentLikes

	//@GetMapping("/get/byid{id}") getCommentLikeById

	//@GetMapping("/get/bycommentid{commentId}") getCommentLikeByCommentId

	//@PostMapping("/save") saveCommentLike

	//@PostMapping("/update") updateCommentLike

	//@PostMapping("/delete") deleteCommentLike
}

