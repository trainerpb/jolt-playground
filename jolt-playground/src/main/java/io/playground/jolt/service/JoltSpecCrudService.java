package io.playground.jolt.service;

import io.playground.jolt.model.JoltSpecTemplate;
import io.playground.jolt.repository.JoltSpecTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class JoltSpecCrudService {

    private final JoltSpecTemplateRepository joltSpecTemplateRepository;

    /**
     * Creates a new JoltSpecTemplate.
     *
     * @param name        the name of the template
     * @param description the description of the template
     * @param content     the content of the template
     * @param createdBy   the user who created the template
     * @return the created JoltSpecTemplate
     */
    public JoltSpecTemplate createJoltSpecTemplate(String name, String description, String content, String createdBy) {

        JoltSpecTemplate joltSpecTemplate = JoltSpecTemplate.builder()
                .name(name)
                .description(description)
                .content(content)
                .createdBy(createdBy)
                .modifiedBy(createdBy) // Initially set modifiedBy to the same as createdBy
                .createdAt(OffsetDateTime.now())
                .modifiedAt(OffsetDateTime.now())
                .build();
        return joltSpecTemplateRepository.save(joltSpecTemplate);

    }

    /**
     * Updates an existing JoltSpecTemplate.
     *
     * @param id          the ID of the template to update
     * @param name        the new name of the template
     * @param description the new description of the template
     * @param content     the new content of the template
     * @param modifiedBy  the user who modified the template
     * @return the updated JoltSpecTemplate
     */
    public JoltSpecTemplate updateJoltSpecTemplate(Long id, String name, String description, String content, String modifiedBy) {
        JoltSpecTemplate joltSpecTemplate = joltSpecTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JoltSpecTemplate not found with id: " + id));
        joltSpecTemplate.setName(name);
        joltSpecTemplate.setDescription(description);
        joltSpecTemplate.setContent(content);
        joltSpecTemplate.setModifiedBy(modifiedBy);
        joltSpecTemplate.setModifiedAt(OffsetDateTime.now());
        return joltSpecTemplateRepository.save(joltSpecTemplate);


    }

    /**
     * Deletes a JoltSpecTemplate by its ID.
     *
     * @param id the ID of the template to delete
     */
    public void deleteJoltSpecTemplate(Long id) {

        joltSpecTemplateRepository.deleteById(id);
        log.info("Deleted JoltSpecTemplate with id: {}", id);
    }

    /**
     * Fetches a JoltSpecTemplate by its ID.
     *
     * @param id the ID of the template to fetch
     * @return the JoltSpecTemplate with the specified ID
     */
    public JoltSpecTemplate getJoltSpecTemplate(Long id) {
        JoltSpecTemplate joltSpecTemplate = joltSpecTemplateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JoltSpecTemplate not found with id: " + id));
        log.info("Fetched JoltSpecTemplate: {}", joltSpecTemplate);
        return joltSpecTemplate;

    }

    /**
     * Fetches all JoltSpecTemplates.
     *
     * @return a list of all JoltSpecTemplates
     */
    public Iterable<JoltSpecTemplate> getAllJoltSpecTemplates() {
        return joltSpecTemplateRepository.findAll();
    }

    /**
     * Saves a JoltSpecTemplate.
     *
     * @param template the JoltSpecTemplate to save
     * @return the saved JoltSpecTemplate
     */
    public JoltSpecTemplate save(JoltSpecTemplate template) {
        return  joltSpecTemplateRepository.save(template);
    }
}
