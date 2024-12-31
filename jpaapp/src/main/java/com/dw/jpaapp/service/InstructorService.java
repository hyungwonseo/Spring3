package com.dw.jpaapp.service;

import com.dw.jpaapp.dto.InstructorDTO;
import com.dw.jpaapp.dto.InstructorGithubDTO;
import com.dw.jpaapp.model.Course;
import com.dw.jpaapp.model.Instructor;
import com.dw.jpaapp.model.InstructorProfile;
import com.dw.jpaapp.repository.CourseRepository;
import com.dw.jpaapp.repository.InstructorProfileRepository;
import com.dw.jpaapp.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    InstructorProfileRepository instructorProfileRepository;

    public List<InstructorDTO> getAllInstructors() {
        return instructorRepository.findAll().stream()
                .map(Instructor::toDTO).toList();
    }

    // 과제5-3. Instructor의 id를 매개변수로 강사의 정보를 조회
    public InstructorDTO getInstructor(Long id) {
        return instructorRepository
                .findById(id)
                .map(Instructor::toDTO)
                .orElseThrow(()->new RuntimeException("없는 아이디"));
    }

    // 과제5-4. Instructor 정보를 새로 저장
    public InstructorDTO saveInstructor(InstructorDTO instructorDTO) {
        Instructor instructor = new Instructor();
        instructor.setName(instructorDTO.getName());
        instructor.setCareer(instructorDTO.getCareer());
        instructor.setCourseList(instructorDTO.getCourseIds().stream()
                .map(id->courseRepository.findById(id))
                .map(optional->optional.orElseThrow(()->new RuntimeException("No course")))
                .peek(course->course.setInstructor_fk(instructor))
                .toList()
        );
        return instructorRepository.save(instructor).toDTO();
    }

    // 과제5-5. 전체 강사의 강사ID, 강사이름, github url을 조회
    public List<Object[]> getInstructorGithub() {
        List<Object[]> objects = new ArrayList<>();
        for (InstructorProfile data : instructorProfileRepository.findAll()) {
            Object[] temp = new Object[3];
            temp[0] = data.getInstructor().getId();
            temp[1] = data.getInstructor().getName();
            temp[2] = data.getGithubUrl();
            objects.add(temp);
        }
        return objects;
    }
    // DTO를 이용하는 방법
    public List<InstructorGithubDTO> getInstructorGithub2() {
        return instructorProfileRepository.getInstructorGithub();
    }
}
