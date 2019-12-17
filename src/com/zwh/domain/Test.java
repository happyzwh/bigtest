package com.zwh.domain;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zwh.annotation.DataBase;
import com.zwh.annotation.ExtraColumn;


/**
 * TestId entity. @author MyEclipse Persistence Tools
 */
@Entity
@DataBase(name="DB1")
@Table(name="test")
public class Test  implements java.io.Serializable {


    // Fields    

     /**
	 * 
	 */
	private static final long serialVersionUID = 6176371456581585285L;
	private Integer id;
    private String name;
    private String des;
    private Integer age;
    private Date createtime;
    private Date updatetime;



    // Constructors

    /** default constructor */
    public Test() {
    }

   
    // Property accessors
    @Id @GeneratedValue
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

  //  @Column(name="name", unique=true, nullable=false, length=20)
    @Column(name="name")
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Column(name="des")
	public String getDes() {
		return des;
	}


	public void setDes(String des) {
		this.des = des;
	}

	@Column(name="age")
	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name="createtime")
	public Date getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name="updatetime")
	@ExtraColumn
	public Date getUpdatetime() {
		return updatetime;
	}


	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
    
}