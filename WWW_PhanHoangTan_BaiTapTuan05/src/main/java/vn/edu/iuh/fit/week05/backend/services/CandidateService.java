package vn.edu.iuh.fit.week05.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.week05.backend.models.Candidate;
import vn.edu.iuh.fit.week05.backend.models.CandidateSkill;
import vn.edu.iuh.fit.week05.backend.models.Skill;
import vn.edu.iuh.fit.week05.backend.repositories.CandidateRepository;
import vn.edu.iuh.fit.week05.backend.repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.week05.backend.repositories.SkillRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private SkillRepository skillRepository;

    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy,
                                   String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);//findFirst.../findTop...
    }

    public Page<Candidate> searchCandidates(String keyword, int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        return candidateRepository.searchCandidates(keyword, pageable);
    }



    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    public List<Skill> getSkillsByCandidateId(Long candidateId) {
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findByIdCanId(candidateId);
        return candidateSkills.stream().map(CandidateSkill::getSkill).collect(Collectors.toList());
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public List<CandidateSkill> getCandidateSkillsByCandidateId(Long id) {
        return candidateSkillRepository.findByIdCanId(id);
    }
}