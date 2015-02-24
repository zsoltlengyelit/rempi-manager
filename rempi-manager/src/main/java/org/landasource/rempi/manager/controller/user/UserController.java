package org.landasource.rempi.manager.controller.user;

import org.landasource.rempi.manager.core.controller.CrudController;
import org.landasource.rempi.manager.core.security.PasswordUtil;
import org.landasource.rempi.manager.model.User;
import org.landasource.rempi.manager.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/user")
public class UserController extends CrudController<User, UserForm> {

	@Autowired
	private UserRepo userRepo;

	@Override
	protected boolean isAdminController() {
		return true;
	}

	@Override
	protected CrudRepository<User, Long> getRepo() {
		return userRepo;
	}

	@Override
	protected String getControllerName() {
		return "user";
	}

	@Override
	protected void validateFormAtSave(final UserForm form, final BindingResult result) {
		final User user2 = userRepo.findByUsername(form.getUsername());
		if (null != user2) {
			result.reject("username-duplicate", "User exists");
		}

		if (StringUtils.isEmpty(form.getPassword())) {
			result.rejectValue("password", "empty-pass", "Required");
		}
	}

	@Override
	protected void validateFormAtUpdate(final Long id, final UserForm form, final BindingResult result) {

		final User user2 = userRepo.findByUsername(form.getUsername());
		if (null != user2 && !user2.getId().equals(id)) {
			result.reject("username-duplicate", "User exists");
		}

	}

	@Override
	protected void fillForm(final UserForm form, final User model) {
		form.setFullName(model.getFullName());
		form.setUsername(model.getUsername());
		form.setEnabled(model.isEnabled());
	}

	@Override
	protected void fillModel(final UserForm form, final User model) {
		model.setFullName(form.getFullName());
		model.setUsername(form.getUsername());
		model.setEnabled(form.isEnabled());
		if (!StringUtils.isEmpty(form.getPassword())) {
			model.setPassword(PasswordUtil.makeHash(form.getPassword()));
		}

	}
}
