package com.hou.dao;

//员工dao

import com.hou.pojo.Department;
import com.hou.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class EmployeeDao {

    //模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(1001,new Employee(1001,"shuaige","123456789@qq.com",1,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"shuaige","123456789@qq.com",0,new Department(101,"教学部")));
        employees.put(1003,new Employee(1003,"shuaige","123456789@qq.com",1,new Department(101,"教学部")));
        employees.put(1004,new Employee(1004,"shuaige","123456789@qq.com",0,new Department(101,"教学部")));
        employees.put(1005,new Employee(1005,"shuaige","123456789@qq.com",1,new Department(101,"教学部")));
    }

    //设置主键自增
    private static Integer initId = 1006;

    //增加一个员工
    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }

        //不太懂，跟着写吧，设置此雇员的部门是根据这个雇员的部门设置 不太懂
        //现在懂了，是因为前端只是传进来一个department的id，没有具体的部门名称，所以要给用户的部门名称赋值
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
    }

    //查询全部员工信息
    public Collection<Employee> getEmployees(){
        return employees.values();
    }

    //通过id查询员工
    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    //修改员工信息
    public void updateEmployee(Employee employee){
        //因为前端只有传过来的部门id，没有具体的部门名字所以就再次设置部门名称
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    //删除员工信息
    public void deleteEmployee(Integer id){
        employees.remove(id);
    }
}
