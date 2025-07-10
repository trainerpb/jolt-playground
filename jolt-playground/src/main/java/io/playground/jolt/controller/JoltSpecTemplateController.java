package io.playground.jolt.controller;

import io.playground.jolt.model.JoltSpecTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

import io.playground.jolt.repository.JoltSpecTemplateRepository;

@RestController
@RequestMapping("/api/jolt-spec-templates")
public class JoltSpecTemplateController {

    @Autowired
    private JoltSpecTemplateRepository repository;

    @GetMapping
    public List<JoltSpecTemplate> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JoltSpecTemplate> getById(@PathVariable Long id) {
        Optional<JoltSpecTemplate> template = repository.findById(id);
        return template.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public JoltSpecTemplate create(@RequestBody JoltSpecTemplate template) {
        return repository.save(template);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JoltSpecTemplate> update(@PathVariable Long id, @RequestBody JoltSpecTemplate updated) {
        return repository.findById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(repository.save(updated));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
