package com.lunacy.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

  @RequestMapping(value = "/main")
  public String main(){
    return "main";
  }
}
