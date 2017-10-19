package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import com.cooksys.ftd.assignments.collections.model.WageSlave;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

//	HashMap<Capitalist, HashSet<Capitalist>> people = new HashMap<>();
//	HashSet<Capitalist> people = new HashSet<>();
	private Set<Capitalist> people = new HashSet<>();
	/**
	 * Adds a given element to the hierarchy.
	 * <p>
	 * If the given element is already present in the hierarchy, do not add it and
	 * return false
	 * <p>
	 * If the given element has a parent and the parent is not part of the
	 * hierarchy, add the parent and then add the given element
	 * <p>
	 * If the given element has no parent but is a Parent itself, add it to the
	 * hierarchy
	 * <p>
	 * If the given element has no parent and is not a Parent itself, do not add it
	 * and return false
	 *
	 * @param capitalist
	 *            the element to add to the hierarchy
	 * @return true if the element was added successfully, false otherwise
	 */
	@Override
	public boolean add(Capitalist capitalist) {
		if (capitalist == null || !capitalist.hasParent() && capitalist instanceof WageSlave)
        	return false;	
		add(capitalist.getParent());
    	return people.add(capitalist);
    }

	/**
	 * @param capitalist
	 *            the element to search for
	 * @return true if the element has been added to the hierarchy, false otherwise
	 */
	@Override
	public boolean has(Capitalist capitalist) {
//		for(Capitalist person : people.keySet()) {
//			if(person.equals(capitalist))
//				return true;
//		}
//		return false;
		for(Capitalist person : people) {
			if (person.equals(capitalist))
				return true;
		}
		return false;
	}

	/**
	 * @return all elements in the hierarchy, or an empty set if no elements have
	 *         been added to the hierarchy
	 */
	@Override
	public Set<Capitalist> getElements() {
		Set<Capitalist> mySet = new HashSet<>(people);
		return mySet;
	}

	/**
	 * @return all parent elements in the hierarchy, or an empty set if no parents
	 *         have been added to the hierarchy
	 */
	@Override
	public Set<FatCat> getParents() {
		Set<FatCat> temp = new HashSet<>();
		for (Capitalist person : people) {
			if(person instanceof FatCat)
				temp.add((FatCat)person);
		}
		return temp;
	}

	/**
	 * @param fatCat
	 *            the parent whose children need to be returned
	 * @return all elements in the hierarchy that have the given parent as a direct
	 *         parent, or an empty set if the parent is not present in the hierarchy
	 *         or if there are no children for the given parent
	 */
	@Override
	public Set<Capitalist> getChildren(FatCat fatCat) {
		Set<Capitalist> temp = new HashSet<>();
		if(!(has(fatCat)))
			return temp;
		for (Capitalist person : people) 
			if (person.hasParent() && person.getParent().equals(fatCat))
				temp.add(person);
		return temp;
	}

	/**
	 * @return a map in which the keys represent the parent elements in the
	 *         hierarchy, and the each value is a set of the direct children of the
	 *         associate parent, or an empty map if the hierarchy is empty.
	 */
	@Override
	public Map<FatCat, Set<Capitalist>> getHierarchy() {
		Map<FatCat, Set<Capitalist>> toReturn = new HashMap<>();
		for(Capitalist person : people)
			if (person instanceof FatCat) 
			toReturn.put((FatCat)person, getChildren((FatCat)person));
		return toReturn;
	}

	/**
	 * @param capitalist
	 * @return the parent chain of the given element, starting with its direct
	 *         parent, then its parent's parent, etc, or an empty list if the given
	 *         element has no parent or if its parent is not in the hierarchy
	 */
	@Override
	public List<FatCat> getParentChain(Capitalist capitalist) {
		List<FatCat> temp = new LinkedList<>();
		if(capitalist == null || !(has(capitalist.getParent())))
		return temp;
		while(capitalist.hasParent()) {
			for(Capitalist person : people) {
				if(capitalist.hasParent() && (capitalist.getParent().equals(person))) {
					temp.add((FatCat)person);
					capitalist = capitalist.getParent();
				}
			}
		}
		return temp;
	}
}
