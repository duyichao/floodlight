/**
 * 
 */
package net.afloodlightcontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.projectfloodlight.openflow.protocol.OFMessage;
import org.projectfloodlight.openflow.protocol.OFType;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IFloodlightProviderService;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;

/**
 * @author duyic
 *
 */
public class ResponseTime implements IOFMessageListener, IFloodlightModule {

	//在类中定义使用的变量。
	//需要监听 OF 消息，所以注册 IfloodlightProviderService
	protected IFloodlightProviderService floodlightProvider;
	//用 set 结构来存响应时间
	//protected Set<Long> responseTime;
	protected float[] responseTime = new float[3];
	//logger 来记录日志
	protected static Logger logger;
	
	@Override
	public String getName() {
		return ResponseTime.class.getSimpleName();
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

	//把它连接到模块加载系统，通过修改 getModuleDependecies()，告诉 module loader，需要依赖它
	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		Collection<Class<? extends IFloodlightService>> l = 
				new ArrayList<Class<? extends IFloodlightService>>();
		l.add(IFloodlightProviderService.class);
		return l;
	}

	//创建初始化函数，加载依赖并初始化数据结构。 
	@Override
	public void init(FloodlightModuleContext context) throws FloodlightModuleException {
		floodlightProvider = 
				context.getServiceImpl(IFloodlightProviderService.class);
		logger = (Logger) LoggerFactory.getLogger(ResponseTime.class);
	}

	//在 startUp 方法中，注册对PACKET_IN消息的处理。
	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {

	}

	@Override
	public net.floodlightcontroller.core.IListener.Command receive(IOFSwitch sw, OFMessage msg,
			FloodlightContext cntx) {
		return null;
	}

}
