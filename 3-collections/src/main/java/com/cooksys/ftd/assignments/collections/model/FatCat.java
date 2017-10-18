package com.cooksys.ftd.assignments.collections.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FatCat implements Capitalist {
	
	private String _name;
	private int _salary;
	private FatCat _owner;

    public FatCat(String name, int salary) {
        this._name = name;
        this._salary = salary;
    }

    public FatCat(String name, int salary, FatCat owner) {
        _name = name;
        _salary = salary;
        _owner = owner;
    }

    /**
     * @return the name of the capitalist
     */
    @Override
    public String getName() {
        return _name;
    }

    /**
     * @return the salary of the capitalist, in dollars
     */
    @Override
    public int getSalary() {
        return _salary;
    }

    /**
     * @return true if this element has a parent, or false otherwise
     */
    @Override
    public boolean hasParent() {
        return(_owner != null);
    }

    /**
     * @return the parent of this element, or null if this represents the top of a hierarchy
     */
    @Override
    public FatCat getParent() {
        return _owner;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(!(obj instanceof FatCat))
    		return false;
    	
    	FatCat o = (FatCat)obj;
    	
    	return ((this._name == o._name) && (this._salary == o._salary) && (this._owner == o._owner));
    }
}
