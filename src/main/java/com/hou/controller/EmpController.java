package com.hou.controller;

import com.hou.dao.DepartmentDao;
import com.hou.dao.EmployeeDao;
import com.hou.pojo.Department;
import com.hou.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmpController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @RequestMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getEmployees();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    //get请求走这个添加方法
    @GetMapping("/emp")
    public String toAdd(Model model){
        //查询部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //跳转到表单页面
        return "emp/add";
    }

    //post请求走这个方法
    @PostMapping("/emp")
    public String add(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEdit(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.getEmployeeById(id);
        //查询部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        model.addAttribute("emp",employee);
        //转向编辑页面
        return "emp/edit";
    }

    @PostMapping("/emp/{id}")
    public String edit(@PathVariable("id") Integer id, Employee employee){
        //给employee设置id
        employee.setId(id);
        //进行dao层的修改操作
        employeeDao.updateEmployee(employee);
        return "redirect:/emps";
    }

    @RequestMapping("/delemp/{id}")
    public String delete(@PathVariable("id") Integer id){
        employeeDao.deleteEmployee(id);
        return "redirect:/emps";
    }
}
