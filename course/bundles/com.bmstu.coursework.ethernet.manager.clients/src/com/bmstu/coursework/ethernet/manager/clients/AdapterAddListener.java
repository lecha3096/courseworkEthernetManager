/**
 *
 */
package com.bmstu.coursework.ethernet.manager.clients;

import org.osgi.service.component.annotations.Component;

import com.bmstu.coursework.ethernet.manager.IEthernetListener;
import com.bmstu.coursework.ethernet.manager.model.EthernetAdapterEvent;

/**
 *
 * Instance of this class represents add adapter listener.
 *
 * @author Aleksei Petrov
 *
 */
@Component
public class AdapterAddListener implements IEthernetListener {

	@Override
	public void adapterChanged(EthernetAdapterEvent event) {
		if (!event.getAdded().isEmpty()) {
			System.out.println("Added: ");
			System.out.println(event.getAdded());
		}
	}
}
