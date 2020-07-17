package com.learn.hibernate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "EMPLOYEES_2")
@DynamicUpdate
@DynamicInsert
public class Employee {
   @Override
	public String toString() {
		return "Employee [id=" + id + ", Empname=" + Empname + ", password=" + password + ", empDetail=" + empDetail
				+ "]";
	}

@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "EMP_ID")
   private long id;

   @Column(name = "EMP_NAME", nullable = false, unique = true)
   private String Empname;

   @Column(name = "PASSWORD")
   private String password;

   @OneToOne(cascade = CascadeType.ALL,mappedBy = "employee", fetch = FetchType.LAZY)
   private EmployeeDetail empDetail;

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmpname() {
		return Empname;
	}
	
	public void setEmpname(String empname) {
		Empname = empname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public EmployeeDetail getEmpDetail() {
		return empDetail;
	}
	
	public void setEmpDetail(EmployeeDetail empDetail) {
		this.empDetail = empDetail;
	}
}