package net.afloodlightcontroller.setflow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.projectfloodlight.openflow.protocol.OFFlowAdd;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFType;
import org.projectfloodlight.openflow.protocol.action.OFAction;
import org.projectfloodlight.openflow.protocol.instruction.OFInstructionApplyActions;
import org.projectfloodlight.openflow.protocol.instruction.OFInstructions;
import org.projectfloodlight.openflow.protocol.match.MatchField;
import org.projectfloodlight.openflow.protocol.ver13.OFFactoryVer13;
import org.projectfloodlight.openflow.types.DatapathId;
import org.projectfloodlight.openflow.types.OFBufferId;
import org.projectfloodlight.openflow.types.OFPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.accessibility.internal.resources.accessibility;

import net.afloodlightcontroller.mactracker.MACTracker;
import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IFloodlightProviderService;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.IListener.Command;
import net.floodlightcontroller.core.internal.FloodlightProvider;
import net.floodlightcontroller.core.internal.IOFSwitchService;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;
import net.floodlightcontroller.core.types.SwitchMessagePair;
import net.floodlightcontroller.restserver.IRestApiService;

public class SetFlow implements IOFMessageListener, IFloodlightModule {
	protected IFloodlightProviderService floodlightProvider;
	protected Logger logger;

	@Override
	public String getName() {
		return "SetFlow";
	}

	@Override
	public boolean isCallbackOrderingPrereq(OFType type, String name) {
		return false;
	}

	@Override
	public boolean isCallbackOrderingPostreq(OFType type, String name) {
		return false;
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleServices() {
		return null;
	}

	@Override
	public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
		return null;
	}

	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		Collection<Class<? extends IFloodlightService>> l = new ArrayList<Class<? extends IFloodlightService>>();
	    l.add(IFloodlightProviderService.class);
	    l.add(IRestApiService.class);
	    return l;
	}

	@Override
	public void init(FloodlightModuleContext context) throws FloodlightModuleException {
		floodlightProvider = 
				context.getServiceImpl(IFloodlightProviderService.class);
		logger = LoggerFactory.getLogger(SetFlow.class);
	}

	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {
		floodlightProvider.addOFMessageListener(OFType.PACKET_IN, this);
	}
	
	@Override
	public net.floodlightcontroller.core.IListener.Command receive(IOFSwitch sw, OFMessage msg,
			FloodlightContext cntx) {
		switch(msg.getType()) {
        case PACKET_IN:
			sw = switchService.getSwitch(DatapathId.of(1));
			setflowtable(sw, 1, 2);
			setflowtable(sw, 2, 1);
            break;
       default:
           break;
    }
		
    return Command.CONTINUE;
	}
	
	IOFSwitchService switchService;
	void setflowtable(IOFSwitch sw,int in_Port,int out_Port)
	{
		ArrayList<OFAction> actions = new ArrayList<OFAction>();
		actions.add(sw.getOFFactory().actions().buildOutput() // builder pattern used throughout
		  .setPort(OFPort.of(out_Port)) // raw types replaced with objects for type-checking and readability
		  .build()); // list of immutable OFAction objects
		OFFlowAdd flow = sw.getOFFactory().buildFlowAdd()
		  .setMatch(sw.getOFFactory().buildMatch()
		  .setExact(MatchField.IN_PORT, OFPort.of(in_Port))
		  .build()) // immutable Match object
		  .setActions(actions)
		  .setOutPort(OFPort.ZERO)
		  .setBufferId(OFBufferId.NO_BUFFER)
		  .setPriority(1)
		  .build(); // immutable OFFlowMod; no lengths to set; no wildcards to set
		sw.write(flow);
	}

}
