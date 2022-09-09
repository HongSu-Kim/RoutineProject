package com.soo.routine.controller;

import com.soo.routine.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

}
