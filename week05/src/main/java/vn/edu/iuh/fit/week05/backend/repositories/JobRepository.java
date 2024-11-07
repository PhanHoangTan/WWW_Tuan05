package vn.edu.iuh.fit.week05.backend.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.iuh.fit.week05.backend.models.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("SELECT j FROM Job j WHERE " +
            "LOWER(j.jobName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(j.jobDesc) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(j.company.compName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Job> searchJobs(String keyword, Pageable pageable);
}