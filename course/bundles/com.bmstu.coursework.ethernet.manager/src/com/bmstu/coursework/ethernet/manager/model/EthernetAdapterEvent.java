/**
 *
 */
package com.bmstu.coursework.ethernet.manager.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * Instance of this class represents ethernet adapter change event.
 * Contains removed, added and changed adapters.
 *
 * @author Aleksei Petrov
 *
 */
public class EthernetAdapterEvent {

	private Collection<EthernetAdapterInfo> added;
	private Collection<EthernetAdapterInfo> removed;
	private Collection<OldNewInfoPair> changed;

	/**
	 * Constructor.
	 */
	public EthernetAdapterEvent() {
		added = new ArrayList<>();
		removed = new ArrayList<>();
		changed = new ArrayList<>();
	}

	/**
	 *
	 * Returns added infos.
	 *
	 * @return added infos. Can't return <code>null</code>.
	 */
	public Collection<EthernetAdapterInfo> getAdded() {
		return added;
	}

	/**
	 *
	 * Returns removed infos.
	 *
	 * @return removed infos. Can't return <code>null</code>.
	 */
	public Collection<EthernetAdapterInfo> getRemoved() {
		return removed;
	}

	/**
	 *
	 * Returns changed infos.
	 *
	 * @return changed infos. Can't return <code>null</code>.
	 */
	public Collection<OldNewInfoPair> getChanged() {
		return changed;
	}
}
