package net.afloodlightcontroller.responsetime;

import java.util.Collection;
import java.util.Map;

import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFType;

import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;

public class sendRequest implements IOFMessageListener, IFloodlightModule {

	@Override
	public String getName() {
		// TODO �Զ����ɵķ������
		return "sendRequest";
	}

	@Override
	public boolean isCallbackOrderingPrereq(OFType type, String name) {
		// TODO �Զ����ɵķ������
		return (name.equals("ResponseTime"));
	}

	@Override
	public boolean isCallbackOrderingPostreq(OFType type, String name) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleServices() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void init(FloodlightModuleContext context) throws FloodlightModuleException {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {
		// TODO �Զ����ɵķ������

	}

	@Override
	public net.floodlightcontroller.core.IListener.Command receive(IOFSwitch sw, OFMessage msg,
			FloodlightContext cntx) {
		// TODO �Զ����ɵķ������
		return null;
	}

}
