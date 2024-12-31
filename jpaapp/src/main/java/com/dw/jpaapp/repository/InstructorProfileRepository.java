package com.dw.jpaapp.repository;

import com.dw.jpaapp.dto.InstructorGithubDTO;
import com.dw.jpaapp.model.InstructorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstructorProfileRepository extends JpaRepository<InstructorProfile, Long> {
    // 과제5-5. 전체 강사의 강사ID, 강사이름, github url을 조회
    // DTO를 직접 매핑하는 방법
    @Query("SELECT new com.dw.jpaapp.dto.InstructorGithubDTO(i.id, i.name, ip.githubUrl) " +
            "FROM Instructor i JOIN InstructorProfile ip ON i.id = ip.instructor.id")
    List<InstructorGithubDTO> getInstructorGithub();
}
