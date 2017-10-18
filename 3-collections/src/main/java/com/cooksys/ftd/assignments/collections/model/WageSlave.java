package com.cooksys.ftd.assignments.collections.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class WageSlave implements Capitalist {
	
	String _name;
	int _salary;
	FatCat _owner;
	
    public WageSlave(String name, int salary) {
        this._name = name;
        this._salary = salary;
    }

    public WageSlave(String name, int salary, FatCat owner) {
        this._name = name;
        this._salary = salary;
        this._owner = owner;
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
        return (_owner != null);
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
    	if(!(obj instanceof WageSlave))
    		return false;
    	WageSlave o = (WageSlave) obj;
    	return((o._name == this._name) && (o._owner == this._owner) && (o._salary == this._salary));
    }
    
}
