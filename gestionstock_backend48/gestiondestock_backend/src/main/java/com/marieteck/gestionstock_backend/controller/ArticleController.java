package com.marieteck.gestionstock_backend.controller;

import com.marieteck.gestionstock_backend.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ArticleController {

    private ArticleService articleService;



}
