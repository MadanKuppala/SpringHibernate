package com.madan.hp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.madan.hp.model.Student;
import com.madan.hp.service.StudentService;


@Controller
@RequestMapping(value="student")
public class StudentController {
	
	@Autowired
	StudentService studService;
	
	@RequestMapping(value="StudList",method=RequestMethod.GET)
	public String studList(Model model) {
		List<Student> sList = studService.getStudentList();
		System.out.println("sList "+sList);
		model.addAttribute("sList" ,sList );
		return "StudentList";
		
		
	}
	@RequestMapping(value="saveStudent",method=RequestMethod.GET)
	public String savePage() {
		return "saveStudent";
	}
	
	@RequestMapping(value="submitDetails",method=RequestMethod.POST)
	public String submitDetails(Student student, Model model) {
		student=studService.submitDetails(student);
		return "redirect:/student/StudList";
		
	}

}
