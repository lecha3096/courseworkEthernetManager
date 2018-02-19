/**
 * Copyright (C) 2017, 1C
 */
package com.bmstu.coursework.ethernet.manager.impl;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

import com.bmstu.coursework.ethernet.manager.IEthernetListener;
import com.bmstu.coursework.ethernet.manager.IEthernetManager;
import com.bmstu.coursework.ethernet.manager.model.EthernetAdapterEvent;
import com.bmstu.coursework.ethernet.manager.model.EthernetAdapterInfo;
import com.bmstu.coursework.ethernet.manager.model.OldNewInfoPair;

/**
 *
 * Default implementation of {@link IEthernetManager}.
 *
 * @author Aleksei Petrov
 *
 */
@Component(immediate = true)
public class EthernetManager implements IEthernetManager {

	private static final int AWAIT_TERMINATION_TIME = 10;

	private Collection<IEthernetListener> listeners;
	private Collection<EthernetAdapterInfo> infos;
	private ExecutorService executorService;
	private boolean isActive;

	/**
	 * Constuctor.
	 */
	public EthernetManager() {
		listeners = new HashSet<>();
		infos = new HashSet<>();
	}

	@Override
	public Collection<EthernetAdapterInfo> getCurrentAdaptersInfo() {
		return infos;
	}

	@Activate
	public void activate(Map<String, Object> properties)
	{
		System.out.println("Ethernet manager activated");

		isActive = true;
		initData();

		executorService = Executors.newFixedThreadPool(1);
		executorService.execute(new AdapterListener());
	}

	@Deactivate
	public void deactivate()
	{
		try {
			isActive = false;
			executorService.awaitTermination(AWAIT_TERMINATION_TIME, TimeUnit.SECONDS);
			listeners.clear();
			infos.clear();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
	public void bindListener(IEthernetListener listener)
	{
		listeners.add(listener);
	}

	public void unbindListener(IEthernetListener listener)
	{
		listeners.remove(listener);
	}

	private void throwEvent(EthernetAdapterEvent event)
	{
		listeners.stream().forEach(listener -> {
			listener.adapterChanged(event);
		});
	}

	private void initData() {
		infos.addAll(createInfos());
	}

	private Collection<EthernetAdapterInfo> createInfos()
	{
		Collection<EthernetAdapterInfo> infos = new HashSet<>();
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			Collections.list(networkInterfaces).forEach(networkInterface -> infos.add(createInfo(networkInterface)));
		}
		catch (SocketException e) {
			e.printStackTrace();
		}

		return infos;
	}

	private EthernetAdapterInfo createInfo(NetworkInterface networkInterface)
	{
		return new EthernetAdapterInfo(networkInterface.getDisplayName(), networkInterface.getName(), getAddresses(networkInterface));
	}

	private Collection<String> getAddresses(NetworkInterface networkInterface) {
		Collection<String> adresses = new ArrayList<>();
		Collections.list(networkInterface.getInetAddresses()).forEach(address -> adresses.add(address.toString()));

		return adresses;
	}

	private class AdapterListener implements Runnable {
		private static final int SLEEP_TIME = 1000;

		@Override
		public void run() {
			while (isActive) {
				try {
					EthernetAdapterEvent event = new EthernetAdapterEvent();
					Collection<EthernetAdapterInfo> newInfos = createInfos();
					Collection<EthernetAdapterInfo> changedAdapters = new ArrayList<>();
					newInfos.stream().forEach(newInfo -> {
						if (!infos.contains(newInfo))
						{
							EthernetAdapterInfo oldInfo = getInfoByName(newInfo.getName());
							if (oldInfo != null)
							{
								event.getChanged().add(new OldNewInfoPair(oldInfo, newInfo));
								changedAdapters.add(oldInfo);
							}
							else
							{
								event.getAdded().add(newInfo);
							}
						}
					});
					infos.stream().forEach(oldInfo -> {
						if (!newInfos.contains(oldInfo) && !changedAdapters.contains(oldInfo))
						{
							event.getRemoved().add(oldInfo);
						}
					});

					if (!event.getAdded().isEmpty() || !event.getRemoved().isEmpty() || !event.getChanged().isEmpty())
					{
						throwEvent(event);
					}

					infos = newInfos;
					Thread.sleep(SLEEP_TIME);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		private EthernetAdapterInfo getInfoByName(String name) {
			for (EthernetAdapterInfo info : infos)
			{
				if (name.equals(info.getName()))
				{
					return info;
				}
			}

			return null;
		}
	}
}
