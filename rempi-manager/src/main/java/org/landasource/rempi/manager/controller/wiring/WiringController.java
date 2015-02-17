package org.landasource.rempi.manager.controller.wiring;

import java.util.Map.Entry;
import java.util.Set;

import org.landasource.rempi.common.GpioPin;
import org.landasource.rempi.manager.core.controller.CrudController;
import org.landasource.rempi.manager.model.Wiring;
import org.landasource.rempi.manager.repo.WiringRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Zsolti
 *
 */
@Controller
@RequestMapping("/wiring")
public class WiringController extends CrudController<Wiring, WiringForm> {

	@Autowired
	private WiringRepo wiringRepo;

	@Override
	protected CrudRepository<Wiring, Long> getRepo() {
		return wiringRepo;
	}

	@Override
	protected String getControllerName() {
		return "wiring";
	}

	@Override
	protected void validateFormAtSave(final WiringForm form, final BindingResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void validateFormAtUpdate(final Long id, final WiringForm form, final BindingResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void fillForm(final WiringForm form, final Wiring model) {
		form.setName(model.getName());

		final Set<Entry<GpioPin, String>> entrySet = model.getPinTable().entrySet();
		for (final Entry<GpioPin, String> entry : entrySet) {
			form.getPinTable().put(entry.getKey().name(), entry.getValue());
		}
	}

	@Override
	protected void fillModel(final WiringForm form, final Wiring model) {
		model.setName(form.getName());

		final Set<Entry<String, String>> entrySet = form.getPinTable().entrySet();
		for (final Entry<String, String> entry : entrySet) {
			model.getPinTable().put(GpioPin.valueOf(entry.getKey()), entry.getValue());
		}
	}

	@Override
	protected void addFormParams(final ModelMap modelMap) {
		modelMap.addAttribute("gpioPins", GpioPin.values());
	}

}
