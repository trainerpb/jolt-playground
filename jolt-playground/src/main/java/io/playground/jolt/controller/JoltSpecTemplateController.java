package io.playground.jolt.controller;

import io.playground.jolt.model.JoltSpecTemplate;
import io.playground.jolt.repository.JoltSpecTemplateRepository;
import io.playground.jolt.service.JoltSpecCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/jolt-spec-templates")
@RequiredArgsConstructor
public class JoltSpecTemplateController {


    private final JoltSpecCrudService crudService;


    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("templates", crudService.getAllJoltSpecTemplates());
        model.addAttribute("template", new JoltSpecTemplate());
        return "jolt-spec-templates/crud";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("template") JoltSpecTemplate template) {
        crudService.save(template);
        return "redirect:/jolt-spec-templates";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        JoltSpecTemplate template = crudService.getJoltSpecTemplate(id);
        model.addAttribute("templates", crudService.getAllJoltSpecTemplates());
        model.addAttribute("template", template);
        return "jolt-spec-templates/crud";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        crudService.deleteJoltSpecTemplate(id);
        return "redirect:/jolt-spec-templates";
    }
}
