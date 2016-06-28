package com.raysmond.controller;

import com.raysmond.domain.User;

import java.util.*;

import com.raysmond.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Raysmond<i@raysmond.com>
 */
@Controller
@RequestMapping("/users")
public class UsersController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping(value = "")
  public String allUsers(Model model) {
    model.addAttribute("users", userRepository.findAll());
    return "main/home";
  }

  @RequestMapping(value = "{id}")
  public String show(@PathVariable Long id, Model model) {
    model.addAttribute("user", userRepository.findOne(id));
    return "main/show";
  }

  @RequestMapping(value = "{id}", method = RequestMethod.POST)
  public String update(@PathVariable Long id, User user, RedirectAttributes redirectAttributes, Model model) {
    userRepository.save(user);
    redirectAttributes.addFlashAttribute("alert", "User " + id + " is updated successfully.");
    return "redirect:/users";
  }

  @RequestMapping(value = "{id}/edit")
  public String edit(@PathVariable Long id, Model model) {
    model.addAttribute("user", userRepository.findOne(id));
    return "main/edit";
  }

  @RequestMapping(value = "{id}/delete", method = RequestMethod.POST)
  public String delete(@PathVariable Long id) {
    userRepository.delete(id);
    return "redirect:/users";
  }
}
