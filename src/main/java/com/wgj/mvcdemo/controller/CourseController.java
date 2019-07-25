package com.wgj.mvcdemo.controller;

import com.wgj.mvcdemo.model.Course;
import com.wgj.mvcdemo.service.CourseService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @Author Guojian Wang
 * @Date 2019/7/25 13:38
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    private static Logger log = LoggerFactory.getLogger(CourseController.class);

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * 方法处理/course/view?courseId=123
     *
     * @param courseId
     * @param model
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewCourse(@RequestParam("courseId") Integer courseId, Model model) {
        log.debug("In viewCourse,courseId = {}", courseId);
        Course course = courseService.getCoursebyId(courseId);
        model.addAttribute(course);
        return "course_overview";
    }

    /**
     * http://localhost:8080/courses/{courseId}
     *
     * @param courseId
     * @param model
     * @return
     */
    @RequestMapping(value = "/view2/{courseId}", method = RequestMethod.GET)
    public String viewCourse2(@PathVariable("courseId") Integer courseId, Map<String, Object> model) {
        log.debug("In viewCourse2,courseId = {}", courseId);
        Course course = courseService.getCoursebyId(courseId);
        model.put("course", course);
        return "course_overview";
    }

    /**
     * http://localhost:8080/courses/view3?courseId=456
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "view3", method = RequestMethod.GET)
    public String viewCourse3(HttpServletRequest request) {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        log.debug("In viewCourse3,courseId = {}", courseId);
        Course course = courseService.getCoursebyId(courseId);
        request.setAttribute("course", course);
        return "course_overview";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
    public String createCourse() {
        return "course_admin/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String doSave(Course course) {
        log.debug("Info of Course");
        log.debug(ReflectionToStringBuilder.toString(course));
        // 在此进行业务操作，比如数据库持久化操作
        course.setCourseId(123);
        return "redirect:view2/" + course.getCourseId();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadPage() {
        return "course_admin/file";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String doUpLoadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            log.debug("Process file {}", file.getOriginalFilename());
            FileUtils.copyInputStreamToFile(file.getInputStream(),
                    new File("D:\\Program Files\\IDEA_workspace\\springmvc\\src\\main\\webapp\\",
                            System.currentTimeMillis() + file.getOriginalFilename()));
        }
        return "success";
    }

    /**
     * @param courseId
     * @return
     * @ResponseBody 将模型数据呈现为JSON数据格式
     */
    @RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
    public @ResponseBody
    Course getCourseInJson(@PathVariable Integer courseId) {
        return courseService.getCoursebyId(courseId);
    }

    @RequestMapping(value = "/jsontype/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<Course> getCourseInJson2(@PathVariable Integer courseId) {
        Course course = courseService.getCoursebyId(courseId);
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }
}
