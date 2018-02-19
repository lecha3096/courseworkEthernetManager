/**
 * Copyright (C) 2017, 1C
 */
package com.bmstu.coursework.ethernet.manager.model;

import java.util.Collection;
import java.util.Objects;

/**
 *
 * Instance of this class represents ethernet adapter info container.
 *
 * @author Aleksei Petrov
 *
 */
public class EthernetAdapterInfo {

	private String displayName;
	private String name;
	private Collection<String> addresses;

	/**
	 *
	 * Constructor.
	 *
	 * @param displayName - display name. Can't be <code>null</code>.
	 * @param name - name. Can't be <code>null</code>.
	 * @param address - addresses. Can't be <code>null</code>.
	 */
	public EthernetAdapterInfo(String displayName, String name, Collection<String> addresses) {
		this.displayName = displayName;
		this.name = name;
		this.addresses = addresses;
	}

	/**
	 *
	 * Returns display name.
	 *
	 * @return display name. Can't return <code>null</code>.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 *
	 * Returns name.
	 *
	 * @return name. Can't return <code>null</code>.
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * Returns addresses.
	 *
	 * @return addresses. Can't return <code>null</code>.
	 */
	public Collection<String> getAddresses() {
		return addresses;
	}

	/**
	 *
	 * Sets display name.
	 *
	 * @param displayName - new adapter display name. Can't be <code>null</code>.
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 *
	 * Sets name.
	 *
	 * @param name - new adapter name. Can't be <code>null</code>.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * Sets addresses.
	 *
	 * @param addresses - new adapter addresses. Can't be <code>null</code>.
	 */
	public void setAddresses(Collection<String> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		String result = "DisplayName: " + getDisplayName() + "\n";
		result += "Name: " + getName() + "\n";
		result += "InetAddresses: " + getAddresses() + "\n";

		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(displayName, name, addresses);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		else if (object == this) {
			return true;
		}

		if (object instanceof EthernetAdapterInfo) {
			EthernetAdapterInfo otherInfo = (EthernetAdapterInfo)object;

			return otherInfo.getDisplayName().equals(getDisplayName()) && otherInfo.getName().equals(getName()) && otherInfo.getAddresses().equals(getAddresses());
		}

		return false;
	}
}
