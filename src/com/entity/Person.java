package com.entity;

import android.graphics.Bitmap;

public class Person {
	private int id;
    private String name;
    private int age;
    private String sex;
    private String fileName;
    
	   

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
		
	    public Person()
	    {
	    }

	    public Person(String name, int age, String sex)
	    {
	        this.name = name;
	        this.age = age;
	        this.sex = sex;
	    }
	    public Person(int id,String name, int age, String sex,String fileName)
	    {   this.id=id;
	        this.name = name;
	        this.age = age;
	        this.sex = sex;
	        this.fileName=fileName;
	    }
}
