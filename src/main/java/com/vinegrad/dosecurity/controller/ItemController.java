package com.vinegrad.dosecurity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vinegrad.dosecurity.model.Item;
import com.vinegrad.dosecurity.service.ItemService;

@Controller
@SessionAttributes({"seeMenu", "menus", "menu", "items", "item" })
public class ItemController {

	@Autowired
	private ItemService service;

	@RequestMapping(value = "/addFood", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String addFood(@ModelAttribute("foodForm") Item item, BindingResult result, Model model) {
		List<String> menus = Arrays.asList("Starter", "Main", "Dessert", "Drink");
		model.addAttribute("menus", menus);
		return "addFood";
	}

	@RequestMapping(value = "/addFood", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String addFoodItem(@ModelAttribute("foodForm") Item item, BindingResult result) {
		service.addItem(new Item(item.getName(), item.getMenu().toLowerCase(), item.getPrice(), item.getDescription(), item.getImage()));
		return "index";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String showAll(Model model) {
		List<String> menus = Arrays.asList("Starter", "Main", "Dessert", "Drink");
		model.addAttribute("seeMenu", 1);
		model.addAttribute("menus", menus);
		return "index";
	}

	@RequestMapping(value = "/find/{menu}", method = RequestMethod.GET)
	public String showAllByMenu(@PathVariable String menu, Model model) {
		switch (menu) {
		case "register":
			return "redirect:/register";
		case "logout":
			return "redirect:/logout";
		case "hello":
			return "redirect:/hello";
		case "all":
			return "redirect:/all";
		case "addFood":
			return "redirect:/addFood";
		default:
			model.addAttribute("menu", menu);
			List<Item> items = service.findByMenu(menu);
			model.addAttribute("items", items);
			return "allMenu";
		}
	}

	@RequestMapping(value = "/find/delete/{itemId}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String deleteItem(@PathVariable Long itemId, Model model) {
		Optional<Item> item = service.findItemById(itemId);
		if (item != null) {
			String menu = item.get().getMenu();
			service.deleteItem(itemId);
			List<Item> items = service.findByMenu(menu);
			model.addAttribute("items", items);
		}
		return "redirect:/all";
	}
	
	@RequestMapping(value = "/find/update/{itemId}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String update(@PathVariable Long itemId, @ModelAttribute("updateFoodForm") Item item, BindingResult result, Model model) {
		Item oldItem = service.findItemById(itemId).get();
		model.addAttribute("item", oldItem);
		return "update";
	}
	
	@RequestMapping(value = "/find/update/{itemId}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String updateItem(@PathVariable Long itemId, @ModelAttribute("updateFoodForm") Item item, BindingResult result, Model model) {
		Item oldItem = service.findItemById(itemId).get();
		service.updateItem(oldItem, new Item(item.getName(), oldItem.getMenu(), item.getPrice(), item.getDescription(), item.getImage()));
		return "redirect:/all";
	}
	
}
