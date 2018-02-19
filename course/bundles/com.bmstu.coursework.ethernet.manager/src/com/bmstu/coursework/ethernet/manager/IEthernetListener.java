/**
 * Copyright (C) 2017, 1C
 */
package com.bmstu.coursework.ethernet.manager;

import com.bmstu.coursework.ethernet.manager.model.EthernetAdapterEvent;

/**
 *
 * Instance of this class can listen {@link IEthernetManager} events.
 *
 * @author Aleksei Petrov
 *
 */
public interface IEthernetListener {

	/**
	 *
	 * Adapter changed event.
	 *
	 * @param - change event. Contains added, removed and changed adapters infos. Can't be <code>null</code>.
	 */
	void adapterChanged(EthernetAdapterEvent event);

}
