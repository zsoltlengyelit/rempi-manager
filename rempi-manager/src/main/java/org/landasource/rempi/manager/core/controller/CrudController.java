package org.landasource.rempi.manager.core.controller;

import java.lang.reflect.ParameterizedType;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Zsolti
 *
 */
public abstract class CrudController<Model, Form> {

	protected abstract CrudRepository<Model, Long> getRepo();

	protected abstract String getControllerName();

	protected abstract void validateFormAtSave(final Form form, final BindingResult result);

	protected abstract void validateFormAtUpdate(final Long id, final Form form, final BindingResult result);

	protected abstract void fillForm(Form form, Model model);

	protected abstract void fillModel(Form form, Model model);

	protected void addFormParams(final ModelMap modelMap) {
	}

	@RequestMapping({ "", "/" })
	public String index(final ModelMap modelMap) {

		final Iterable<Model> items = getRepo().findAll();

		modelMap.put("items", items);
		addFormParams(modelMap);

		return getControllerName() + "/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(final ModelMap modelMap) {

		final Form form = createForm();

		modelMap.put("form", form);
		addFormParams(modelMap);
		return formView();
	}

	@Transactional
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String save(@Valid final Form form, final BindingResult result, final ModelMap modelMap) {

		modelMap.put("result", result);
		modelMap.put("form", form);
		addFormParams(modelMap);

		if (result.hasErrors()) {

			return formView();
		}

		validateFormAtSave(form, result);
		if (result.hasErrors()) {
			return formView();
		}

		final Model model = BeanUtils.instantiate(getModelClass());

		fillModel(form, model);

		getRepo().save(model);

		return redirectIndex();
	}

	@Transactional
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") final Long id, final ModelMap modelMap) {

		final Model model = getRepo().findOne(id);

		final Form form = createForm();

		fillForm(form, model);

		modelMap.addAttribute("form", form);
		addFormParams(modelMap);

		return formView();
	}

	private String formView() {
		return getControllerName() + "/form";
	}

	private String redirectIndex() {
		return "redirect:/" + getControllerName();
	}

	private Form createForm() {
		return BeanUtils.instantiate(getFormClass());
	}

	@Transactional
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") final Long id, @Validated final Form form, final BindingResult result, final ModelMap modelMap) {

		modelMap.put("result", result);
		modelMap.put("form", form);
		addFormParams(modelMap);

		if (result.hasErrors()) {
			return formView();
		}

		validateFormAtUpdate(id, form, result);
		if (result.hasErrors()) {
			return formView();
		}

		final Model model = getRepo().findOne(id);
		fillModel(form, model);

		getRepo().save(model);

		return redirectIndex();
	}

	@Transactional
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") final Long id) {

		getRepo().delete(id);
		return redirectIndex();
	}

	@SuppressWarnings("unchecked")
	protected Class<Model> getModelClass() {
		return (Class<Model>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	protected Class<Form> getFormClass() {
		return (Class<Form>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}

}
