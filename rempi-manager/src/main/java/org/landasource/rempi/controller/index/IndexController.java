package org.landasource.rempi.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index/index";
	}

	@RequestMapping("/error")
	public String error() {
		return "error";
	}

}
