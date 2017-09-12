package net.afloodlightcontroller.pktinhistory;

import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import net.floodlightcontroller.restserver.RestletRoutable;

public class PktInHistoryWebRoutable implements RestletRoutable {

	@Override
	public Restlet getRestlet(Context context) {
		// TODO �Զ����ɵķ������
		Router router = new Router(context);
        router.attach("/history/json", PktInHistoryResource.class);
        return router;
	}

	@Override
	public String basePath() {
		// TODO �Զ����ɵķ������
		return "/wm/pktinhistory";
	}

}
