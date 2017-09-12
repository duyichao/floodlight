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

	//�����ж���ʹ�õı�����
	//��Ҫ���� OF ��Ϣ������ע�� IfloodlightProviderService
	protected IFloodlightProviderService floodlightProvider;
	//�� set �ṹ������Ӧʱ��
	//protected Set<Long> responseTime;
	protected float[] responseTime = new float[3];
	//logger ����¼��־
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

	//�������ӵ�ģ�����ϵͳ��ͨ���޸� getModuleDependecies()������ module loader����Ҫ������
	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		Collection<Class<? extends IFloodlightService>> l = 
				new ArrayList<Class<? extends IFloodlightService>>();
		l.add(IFloodlightProviderService.class);
		return l;
	}

	//������ʼ��������������������ʼ�����ݽṹ�� 
	@Override
	public void init(FloodlightModuleContext context) throws FloodlightModuleException {
		floodlightProvider = 
				context.getServiceImpl(IFloodlightProviderService.class);
		logger = (Logger) LoggerFactory.getLogger(ResponseTime.class);
	}

	//�� startUp �����У�ע���PACKET_IN��Ϣ�Ĵ���
	@Override
	public void startUp(FloodlightModuleContext context) throws FloodlightModuleException {

	}

	@Override
	public net.floodlightcontroller.core.IListener.Command receive(IOFSwitch sw, OFMessage msg,
			FloodlightContext cntx) {
		return null;
	}

}
