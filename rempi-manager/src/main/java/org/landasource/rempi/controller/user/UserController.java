package org.landasource.rempi.controller.user;

import javax.validation.Valid;

import org.landasource.rempi.model.User;
import org.landasource.rempi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@RequestMapping({ "", "/" })
	public String index(final ModelMap modelMap) {

		final Iterable<User> users = userRepo.findAll();

		modelMap.put("users", users);

		return "user/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(final ModelMap modelMap) {

		modelMap.put("user", new UserForm());
		return "user/add";
	}

	@Transactional
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String save(@Valid final UserForm form, final BindingResult result, final ModelMap modelMap) {

		modelMap.put("result", result);
		modelMap.put("user", form);

		if (result.hasErrors()) {

			return "user/add";
		}

		final User user2 = userRepo.findByUsername(form.getUsername());
		if (null != user2) {
			result.reject("username-duplicate", "User exists");

			return "user/add";
		}

		final User user = new User();
		user.setFullName(form.getFullName());
		user.setUsername(form.getUsername());

		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String hashedPassword = passwordEncoder.encode(form.getPassword());
		user.setPasswordHash(hashedPassword);

		userRepo.save(user);

		return "redirect:/user";
	}
}
